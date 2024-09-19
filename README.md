# MF(Mutual Fund) Sample Project
MF Sample Project stores and cache the json response data from external urls based on user roles.

### Technologies 
* Java 17
* Maven 
* Spring boot version 3.0.2
* Spring Security version 6.0.1
* jwt version 0.12.5
* MySQL
* Redis Cache
* lombok

### External urls
* `https://api.mfapi.in/mf`
* `https://api.mfapi.in/mf/100077`

### Authentication and Authorization
Authentication and Authorization is performed on jwt token based on the role of users.

### Roles
1. SUPER_ADMIN
2. ADMIN
3. USER

#### SUPER_ADMIN
SUPER_ADMIN role is to store the json response data from external url(`https://api.mfapi.in/mf`) to Mysql database table.

#### ADMIN and USER
ADMIN and USER role is to get and respond to the json response data from external url(`https://api.mfapi.in/mf/100077`) based on filter for the first request.
further request get and response the json response data from cache based on the filter.
###### Filter:
* 1W -> Last 1 Week Data
* 1M -> Last 1 Month Data
* 1Y -> Last 1 Year Data
* 5Y -> Last 5 Years data
