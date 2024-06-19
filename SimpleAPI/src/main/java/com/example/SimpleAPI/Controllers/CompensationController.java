package com.example.SimpleAPI.Controllers;

import com.example.SimpleAPI.Model.Compensation;
import com.example.SimpleAPI.Services.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api/compensations")
public class CompensationController
{
    private final CompensationService compensationService;

    // Autowired does Dependency Injection, different than .net
    @Autowired
    public CompensationController(CompensationService compensationService)
    {
        this.compensationService = compensationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Compensation>> GetAllData()
    {
        List<Compensation> allCompensations = compensationService.getAllCompensations();
        return ResponseEntity.ok(allCompensations);
    }

    @GetMapping("/test")
    public String Test()
    {
        return compensationService.testService();
    }

    @GetMapping("/compensation_data")
    public ResponseEntity<List<Compensation>> getFilteredCompensations(
            @RequestParam Map<String, String> allParams)
    {
        List<Compensation> filteredCompensations = compensationService.getFilteredCompensations(allParams);
        if (filteredCompensations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(filteredCompensations);
    }
}
