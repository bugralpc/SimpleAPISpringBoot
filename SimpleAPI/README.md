# READ ME
## Overview
This document provides an overview of the Compensation API developed using Spring Boot. It describes the core features of the API and includes example URLs for testing these features. The API allows users to fetch, filter, sort, and retrieve detailed compensation data.

## Features

1. Fetch All Compensation Data
Retrieve all compensation records available in the database.

- Example URL: 
    - http://localhost:8080/api/compensations/all

2. Filter Compensation Data
Filter compensation data based on specific criteria such as years of experience and annual base pay.

- Example URLs:
- Filter by years of experience:
    - http://localhost:8080/api/compensations/compensation_data?filter_yearsofexperience=12
- Filter by annual base pay range (gte - greater than or equal to, lte - less than or equal to):
    - http://localhost:8080/api/compensations/compensation_data?filter_annualbasepay_gte=500000&filter_annualbasepay_lte=600000
    - http://localhost:8080/api/compensations/compensation_data?filter_annualbasepay_gte=300000&sort_annualbasepay&sort_yearsofexperience

3. Sort Compensation Data
Sort compensation records based on parameters such as annual base pay and years of experience.

- Example URL: 
    - http://localhost:8080/api/compensations/compensation_data?sort_yearsofexperience    


4. Retrieve Detailed Compensation Information for Individual Entries
Access detailed compensation data for specific entries based on employer and annual base pay.

- Example URL: 
    - http://localhost:8080/api/compensations/compensation_data?field=Twitter,153000


## Postman Simulation Outputs
Postman was utilized to simulate the API calls and demonstrate the functionality of the Compensation API. Below are some HTTP request links and their corresponding simulation outputs.

### Fetch All Compensation Data

http://localhost:8080/api/compensations/all

![Compensations All Data](Simulation%20Outputs/compensations_all.PNG "Screenshot of All Compensations")

### Filter Compensation Data Based on Specific Criteria

http://localhost:8080/api/compensations/compensation_data?filter_yearsofexperience=12

![Compensations All Data](Simulation%20Outputs/filter_yearsofexperience_12.PNG "Screenshot of All Compensations")

### Sort Compensation Data Based on Parameters

http://localhost:8080/api/compensations/compensation_data?sort_yearsofexperience

![Compensations All Data](Simulation%20Outputs/sort_yearsofexperience.PNG "Screenshot of All Compensations")


### Retrieve Detailed Compensation Information for Individual Entries

http://localhost:8080/api/compensations/compensation_data?field=Twitter,153000

![Compensations All Data](Simulation%20Outputs/field_Twitter_153000.PNG "Screenshot of All Compensations")