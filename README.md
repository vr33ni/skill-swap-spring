# Skill Swap

Skill Swap is a Spring Boot application for connecting users who want to exchange skills. It uses PostgreSQL with PostGIS for geolocation support.

## Features

- User registration and authentication
- Users can offer and request multiple skills
- Matchmaking between users based on skills and location
- Messaging between matched users
- RESTful APIs for users, skills, matches, and messages
- PostgreSQL with PostGIS for spatial queries

## Prerequisites

- Java 17 or higher
- Maven
- Docker (for PostgreSQL/PostGIS)

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd skillswap
```

### 2. Start PostgreSQL/PostGIS

Use Docker Compose to start the required services:

```bash
docker-compose up -d
```

### 3. Run the application

Build and run the application:

```bash
./mvnw spring-boot:run
```

### 4. Test thea application

Use curl or a tool like Postman to test the REST APIs. For example:

```bash
curl -X GET http://localhost:8080/api/skills
```

## Configuration

### Database

The application uses PostgreSQL with PostGIS. Update the database connection details in src/main/resources/application.yml if needed.

### API Endpoints

#### Users /api/users

POST /register — Register a new user

GET /{userId} — Get user details

PATCH /{userId}/location — Update user location

#### Skills /api/skills

GET / — List all skills

POST / — Create a new skill

#### User Skills /api/users/{userId}/skills

POST / — Add a skill to user

DELETE /{skillId} — Remove a skill from user

#### Matches /api/matches

POST / — Create a new match

PATCH /{matchId}/status — Update match status

GET /user/{userId} — Get matches for a user

#### Match Skills /api/matches/{matchId}/skills

GET / — List skills for a match

POST /?skillId=... — Add a skill to a match

DELETE /?skillId=... — Remove a skill from a match

#### Messages /api/messages

POST / — Send a message in a match

GET /match/{matchId} — Get messages for a match

## License

This project is licensed under the MIT License.
