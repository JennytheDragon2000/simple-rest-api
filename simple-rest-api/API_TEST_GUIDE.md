# API Testing Guide

## Prerequisites
1. Start the application: `./mvnw spring-boot:run`
2. Ensure MySQL database is running and accessible
3. Application will be available at: `http://localhost:8080`

## Test Sequence

### 1. Test Employee Management

#### Create Employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "networkId": "EMP001",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "country": "USA",
    "position": "Developer",
    "active": true
  }'
```

#### List All Employees
```bash
curl -X GET http://localhost:8080/api/employees
```

#### Get Employee by ID
```bash
curl -X GET http://localhost:8080/api/employees/EMP001
```

### 2. Test User Management

#### Create User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "age": 30
  }'
```

#### List All Users
```bash
curl -X GET http://localhost:8080/api/users
```

### 3. Test Shift Type Management

#### Create Shift Type (if endpoint exists)
```bash
curl -X POST http://localhost:8080/api/shift-types \
  -H "Content-Type: application/json" \
  -d '{
    "shiftId": "SHIFT001",
    "shiftCode": "DAY",
    "shiftName": "Day Shift",
    "shiftStartTime": "09:00:00",
    "shiftEndTime": "17:00:00",
    "shiftTimeZone": "UTC"
  }'
```

### 4. Test Shift Assignment

#### Create Shift Assignment
```bash
curl -X POST http://localhost:8080/api/shift-assignments \
  -H "Content-Type: application/json" \
  -d '{
    "networkId": "EMP001",
    "shiftId": "SHIFT001",
    "assignmentDate": "2025-07-07"
  }'
```

#### List All Shift Assignments
```bash
curl -X GET http://localhost:8080/api/shift-assignments
```

### 5. Test Date-Based Queries

#### Get Employees by Date
```bash
curl -X GET "http://localhost:8080/api/employees/by-date?date=2025-07-07"
```

#### Get Employees by Date and Shift
```bash
curl -X GET "http://localhost:8080/api/employees/by-date-and-shift?date=2025-07-07&shiftId=SHIFT001"
```

#### Get Users by Date
```bash
curl -X GET "http://localhost:8080/api/users/by-date?date=2025-07-07"
```

#### Get Users by Date and Shift
```bash
curl -X GET "http://localhost:8080/api/users/by-date-and-shift?date=2025-07-07&shiftId=SHIFT001"
```

### 6. Test Shift Assignments by Date

#### Get Shift Assignments by Date
```bash
curl -X GET "http://localhost:8080/api/shift-assignments/by-date?date=2025-07-07"
```

#### Get Shift Assignments by Date and Shift
```bash
curl -X GET "http://localhost:8080/api/shift-assignments/by-date-and-shift?date=2025-07-07&shiftId=SHIFT001"
```

## Expected Workflow

1. **Create Employee** → Should return employee object with networkId
2. **Create User** → Should return user object with ID
3. **Create Shift Type** → Should return shift type object
4. **Create Shift Assignment** → Should return assignment object
5. **Query by Date** → Should return employees/users with assignments on that date
6. **Query by Date and Shift** → Should return employees/users with specific shift assignments

## Common Issues

1. **415 Unsupported Media Type**: Ensure Content-Type header is set to application/json
2. **400 Bad Request**: Check that all required fields are provided
3. **404 Not Found**: Verify the endpoint URL is correct
4. **500 Internal Server Error**: Check if referenced entities exist (e.g., shift type before assignment)

## Database Setup Notes

- Ensure shift types exist in `shift_type_dim` table before creating assignments
- Employee and User emails should match for date-based user queries to work
- The application uses MySQL, ensure the database is accessible