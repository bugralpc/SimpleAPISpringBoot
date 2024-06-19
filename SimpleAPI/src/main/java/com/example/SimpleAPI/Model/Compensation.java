package com.example.SimpleAPI.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Compensation
{
    @JsonProperty("Timestamp")
    private String timestamp;

    @JsonProperty("Employer")
    private String employer;

    @JsonProperty("Location")
    private String location;

    @JsonProperty("Job Title")
    private String jobTitle;

    @JsonProperty("Years at Employer")
    private String yearsAtEmployer;

    @JsonProperty("Years of Experience")
    private String yearsOfExperience;

    @JsonProperty("Annual Base Pay")
    private String annualBasePay;

    @JsonProperty("Signing Bonus")
    private String signingBonus;

    @JsonProperty("Annual Bonus")
    private String annualBonus;

    @JsonProperty("Annual Stock Value/Bonus")
    private String annualStockValueBonus;

    @JsonProperty("Gender")
    private String gender;

    @JsonProperty("Additional Comments")
    private String additionalComments;

    public String getTimestamp() {
        return timestamp;
    }

    public String getEmployer() {
        return employer;
    }

    public String getLocation() {
        return location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getYearsAtEmployer() {
        return yearsAtEmployer;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getAnnualBasePay() {
        return annualBasePay;
    }

    public String getSigningBonus() {
        return signingBonus;
    }

    public String getAnnualBonus() {
        return annualBonus;
    }

    public String getAnnualStockValueBonus() {
        return annualStockValueBonus;
    }

    public String getGender() {
        return gender;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }


}
