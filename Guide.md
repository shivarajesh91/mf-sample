# Spring Boot Task
* Use Spring boot version 3.0.X
* Use Spring Security version 6

**1. Define the data model: Define the data model for User Table with user name, password, role. Various roles -> (ADMIN, SUPER_ADMIN, USER)**
     
Create post API to save user in user table (user name, password(should not store in plain text) and role) and create JWT token and share it in save user API response.

**Only SUPER_ADMIN role can access the below api**

**2. Create GET API to store the data received from the given below Url into the database. Data should be stored in table.**

Url:- https://api.mfapi.in/mf —> GET Method

**(ADMIN and USER) role can access the below api**

**3. Create POST API with the given below Request format and Response format:-**

By giving scheme Id, all the data belonging to that particular scheme id with the filter (1M, 1W, 1Y, 5Y) should be displayed. Should follow the given request and response format.
Data will be there in the below given URL. And second time if request comes with same schemeID, need to get from cache.

URL for getting the data: **https://api.mfapi.in/mf/{schemeId}**

Example Url:- https://api.mfapi.in/mf/100077. —> GET Method

1M -> Last 1 Month data , 1W -> Last 1 week data , 1Y -> Last 1 Year data, 5Y -> Last 5 Year data

Request format:-
```
{
    "request": {
        "schemeId": 100077,
        "filter": "1M"
    }
}
```
Response format:-
```
{
    "response": {
        "fundHouse": "DSP Mutual Fund",
        "schemeCode": 100077,
        "schemeName": "DSP Bond Fund - IDCW",
        "data": [
            [
                "10-02-2023",
                "09-02-2023",
                "08-02-2023"
            ],
            [
                "11.52760",
                "11.52990",
                "11.53720"
            ]
        ] 
    } 
}
```