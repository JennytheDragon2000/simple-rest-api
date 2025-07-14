#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=== Testing Employee Management ==="
echo "1. Adding new employee..."
curl -X POST $BASE_URL/api/employees \
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

echo -e "\n\n2. Listing all employees..."
curl -X GET $BASE_URL/api/employees

echo -e "\n\n=== Testing User Management ==="
echo "3. Adding new user..."
curl -X POST $BASE_URL/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "age": 30
  }'

echo -e "\n\n4. Listing all users..."
curl -X GET $BASE_URL/api/users

echo -e "\n\n=== Testing Shift Assignment ==="
echo "5. Creating shift assignment..."
curl -X POST $BASE_URL/api/shift-assignments \
  -H "Content-Type: application/json" \
  -d '{
    "networkId": "EMP001",
    "shiftId": "SHIFT001", 
    "assignmentDate": "2025-07-07"
  }'

echo -e "\n\n6. Listing all shift assignments..."
curl -X GET $BASE_URL/api/shift-assignments

echo -e "\n\n=== Testing Date-based Queries ==="
echo "7. Getting employees by date..."
curl -X GET "$BASE_URL/api/employees/by-date?date=2025-07-07"

echo -e "\n\n8. Getting shift assignments by date..."
curl -X GET "$BASE_URL/api/shift-assignments/by-date?date=2025-07-07"

echo -e "\n\nTests completed!"