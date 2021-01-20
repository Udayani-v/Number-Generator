# Number-Generator
Application to read employee data from flat file and save it to database

# List of APIs
POST - /api/employees?action=upload   - upload file with employee data

GET - /api/employees   - get all employees

GET - /api/employees/{id}  - get employee by id

PUT - /api/employees/   - update employee

DELETE - /api/employees/{id}  -  delete employee

GET - /api/employees/status   - get status of upload api

# Input
/src/main/resources/files - contains sample input files

Input_1.txt - Happy path scenario

Input_2.txt - Invalid file (Incorrect input format in one of the line) - The data is not saved in the database

Input_2.txt - file with 50000 entries

# Flow
Take file as an input. Read it as a stream. Parse each line. Once all the records are processed, the data is persisted into the database.
If an exception occurs in between, the data is not saved in the database


