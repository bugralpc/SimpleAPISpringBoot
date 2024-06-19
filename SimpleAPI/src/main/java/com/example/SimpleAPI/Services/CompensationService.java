package com.example.SimpleAPI.Services;

import com.example.SimpleAPI.Model.Compensation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CompensationService {
    private List<Compensation> compensations;

    @Autowired
    public CompensationService(@Value("${compensation.json.path}") String salarySurveyJsonFilePath) throws IOException {
        try {
            File file = new File(salarySurveyJsonFilePath);
            ObjectMapper mapper = new ObjectMapper();
            compensations = mapper.readValue(new FileInputStream(file), new TypeReference<List<Compensation>>() {});
        } catch (IOException e) {
            System.err.println("Error reading the compensation data: " + e.getMessage());
            compensations = List.of(); // Initialize to an empty list on failure
        }
    }

    public List<Compensation> getAllCompensations() {
        return compensations;
    }

    public List<Compensation> getFilteredCompensations(Map<String, String> allParams)
    {
        // Once you collect a stream it gets consumed and cannot be reused.
        // Stream API is used for functional-style operations similar to LINQ
        // filter => where in LINQ

        Map<String, String> filters = new HashMap<>();
        Map<String, String> sorts = new HashMap<>();
        List<String> fieldNames = new ArrayList<>();

        // Group parameters
        allParams.forEach((key, value) -> {
            if (key.startsWith("filter_")) {
                filters.put(key.substring(7), value); // Remove 'filter_' prefix
            } else if (key.startsWith("sort_")) {
                sorts.put(key.substring(5), value); // Remove 'sort_' prefix
            } else if (key.startsWith(("field"))) {
                fieldNames.addAll(Arrays.asList(value.split(",")));
            }
        });

        Stream<Compensation> filteredStream = compensations.stream();

        // Filtering
        for (Map.Entry<String, String> filter : filters.entrySet()) {
            String key = filter.getKey().toLowerCase();
            String value = filter.getValue();

            switch (key) {
                case "annualbasepay_gte", "annualbasepay_lte":

                    // Step 1: filter the valid annual base entities
                    Stream<Compensation> validAnnualBasePayStream = filteredStream.filter(c -> {
                        try {
                            if (c.getAnnualBasePay() != null) {
                                Double.parseDouble(c.getAnnualBasePay());
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            // Ignore entries that cannot be parsed
                        }
                        return false;
                    });

                    // Step 2: Filter for values greater than or equal to the specified value or vice verca
                    if ("annualbasepay_gte".equals(key)) {
                        filteredStream = validAnnualBasePayStream
                                .filter(c -> Double.parseDouble(c.getAnnualBasePay()) >= Double.parseDouble(value));
                    } else if ("annualbasepay_lte".equals(key)) {
                        filteredStream = validAnnualBasePayStream
                                .filter(c -> Double.parseDouble(c.getAnnualBasePay()) <= Double.parseDouble(value));
                    }
                    break;

                case "annualbasepay":
                    filteredStream = filteredStream.filter(c -> c.getAnnualBasePay().equals(value));
                    break;
                case "gender":
                    filteredStream = filteredStream.filter(c -> c.getGender().equals(value));
                    break;
                case "yearsofexperience":
                    filteredStream = filteredStream.filter(c -> c.getYearsOfExperience().equals(value));
                    break;
                // Add more cases as needed
            }
        }

        // Sorting
        if (!sorts.isEmpty()) {
            Comparator<Compensation> comparator = null;

            for (Map.Entry<String, String> sort : sorts.entrySet()) {
                String key = sort.getKey();
                switch (key) {
                    case "annualbasepay":
                        if (comparator == null) {
                            comparator = Comparator.comparingDouble(c -> {
                                try {
                                    return Double.parseDouble(c.getAnnualBasePay());
                                } catch (NumberFormatException e) {
                                    return 0.0;
                                }
                            });
                        } else {
                            comparator = comparator.thenComparingDouble(c -> {
                                try {
                                    return Double.parseDouble(c.getAnnualBasePay());
                                } catch (NumberFormatException e) {
                                    return 0.0;
                                }
                            });
                        }
                        break;
                    case "yearsofexperience":
                        if (comparator == null) {
                            comparator = Comparator.comparingInt(c -> {
                                try {
                                    return Integer.parseInt(c.getYearsOfExperience());
                                } catch (NumberFormatException e) {
                                    return 0;
                                }
                            });
                        } else {
                            comparator = comparator.thenComparingInt(c -> {
                                try {
                                    return Integer.parseInt(c.getYearsOfExperience());
                                } catch (NumberFormatException e) {
                                    return 0;
                                }
                            });
                        }
                        break;
                    // Add more cases as needed
                }
            }

            if (comparator != null) {
                filteredStream = filteredStream.sorted(comparator);
            }
        }

        // Fielding
        if (!fieldNames.isEmpty()) {
            String employer = fieldNames.get(0);
            String annualBaseSalary = fieldNames.get(1);

            return compensations.stream()
                    .filter(c -> c.getEmployer().equals(employer) && c.getAnnualBasePay().equals(annualBaseSalary))
                    .collect(Collectors.toList());
        }
        return filteredStream.collect(Collectors.toList());
    }

    public String testService()
    {
        return "Service is up and running!";
    }

    // Additional methods to manipulate the compensations data as needed
}
