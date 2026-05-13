# Neon Ark Intake Tracker - Engineering Notes

## Project
Project #2 - Neon Ark Intake Tracker Version 1

## Environment
- Java 17
- Gradle
- Spring Boot
- Docker
- PostgreSQL 16
- Flyway
- Database: intake_tracker
- User: neonark
- Port: 5437

## Issues Encountered and Fixes

### Issue 1: Spring Initializr ZIP failed
The first curl command returned an invalid zip because the selected Spring Boot version was not available.
Fix: Removed the bad project.zip and regenerated the project without forcing that unavailable version.

### Issue 2: Docker daemon was not running
Terminal showed: Cannot connect to the Docker daemon.
Root cause: Docker Desktop was not running.
Fix: Opened Docker Desktop and reran docker compose up -d.

### Issue 3: Gradle used Java 8 instead of Java 17
Gradle showed: Gradle requires JVM 17 or later.
Root cause: Gradle daemon was using Java 8.
Fix: Added org.gradle.java.home to ~/.gradle/gradle.properties using the Java 17 Homebrew path.

## Successful Tests

### Docker
docker ps showed the PostgreSQL container running:
neon_ark_db on port 5437 -> 5432

### Flyway
Spring Boot startup showed that 5 migrations were applied successfully.

### API Tests

GET all creatures:
curl -i http://localhost:8080/api/creatures
Result: 200 OK with JSON array.

GET creature by ID:
curl -i http://localhost:8080/api/creatures/1
Result: 200 OK with one creature JSON object.

GET missing creature:
curl -i http://localhost:8080/api/creatures/999
Result: 404 Not Found.

POST create creature:
curl -i -X POST http://localhost:8080/api/creatures \
-H "Content-Type: application/json" \
-d '{
  "name": "Stormfang",
  "species": "Thunder Wolf",
  "dangerLevel": "HIGH",
  "condition": "STABLE",
  "notes": "Electrical discharge detected.",
  "habitatId": 1
}'
Result: 201 Created.

Build:
./gradlew clean build
Result: BUILD SUCCESSFUL.

## Final Result
The Spring Boot server runs successfully, connects to PostgreSQL through Docker, applies Flyway migrations, seeds data, and supports the required endpoints:
- GET /api/creatures
- GET /api/creatures/{id}
- POST /api/creatures
