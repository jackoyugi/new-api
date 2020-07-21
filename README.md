
# News Api.

#### An Orgnisation API App that gives users  access to news to a department and general news in an organisation.
#### By **Jack Oyugi**, July 2020;
## Description

Organisational Api that allows a user to query for all the users and the departments associated with them.


## Setup & Installation
 

* Clone the project into your machine from https://github.com/jackoyugi/news-api.



## Technologies used
* Java.
* Gradle(for unit testing).
* Postman.
* Spark.


## Behavior Driven Development
 | Behavior :                                | Input:                                                                     | Output: 
 | :---------------------------------------- | :--------------------------------------------------------------------------| :-------------         
 | Add news                                  | Input Data                                                                 | News is added to the news table    
 | Add a department                          | Input department data                                                      | The department info is added to the table of departments  
 | Add an Employee                           | Input Employee data in json                                                | Employee added to Employees Table    
 | Get all departments a user belongs to     | Make a get request on `employees/0/department` where '0' is the EmployeeId | All departments that an employee belongs to are displayed 

             
## Repo Link
https://github.com/jackoyugi/new-api


## Support and contact details
contact me @ jack@biofit.co.ke.

