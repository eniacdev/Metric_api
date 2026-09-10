## metric_api Project

This project was developed to learn/understand the fundamentals of backend development. This service, which I developed for my home server, is triggered daily at specified times using a schedule. It collects metrics from the server (or any device), saves them to a database, and also creates a file in JSON format (I made sure the JSON format was readable, simple, and straightforward). In short, this project has taught me a great deal and helped me progress and improve in backend development.

## Setup & Running

**Prerequisites:**
- Java 21
- PostgreSQL (running instance, database created)
- Maven (or use the included `mvn` wrapper - no local Maven install required)

**1. Clone the repository**
```bash
git clone https://github.com/eniacdev/Metric_api.git
cd Metric_api
```

**2. Configure the database**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**3. Run the appplication**
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

> A separate Docker-Based setup is planned; this project currently runs via Maven only.

## Scheduler Configuration
Metric collection runs on a schedule (`@Scheduled`), configured via a cron expression in `application.properties`:
```properties
scheduler.cron.expression=0 * * * * *
scheduler.cron.zone=UTC
```
Spring's cron format has **6 fields**: `second minute hour day-of-month month day-of-week`. The example above (`0 * * * * *`) runs once every minute.

## Endpoints

Base path: `/api/v1/metrics`

| Method | Endpoint    | Description                                   |
|--------|-------------|-----------------------------------------------|
| POST   | `/collect`  | Collects metrics and saves to DB              |
| GET    | `/`         | Returns all metrics                           |
| GET    | `/log/{id}` | Returns a metric log by id                    |
| DELETE | `/log/{id}` | Deletes a metric log by id                    |
| GET    | `/system`   | Returns system info (hostname, OS, uptime)    |
| GET    | `/cpu`      | Returns only CPU metrics                      |
| GET    | `/memory`   | Returns only RAM metrics                      |
| GET    | `/disk`     | Returns only disk metrics                     |
| GET    | `/network`  | Returns only network metrics                  |
| GET    | `/snapshot` | Returns all metrics and saves JSON file       |
| GET    | `/search`   | Returns metrics with pagination and filtering |

### GET /search
**Query Parameters:**

| Param        | Type   | Description                                 |
|--------------|--------|---------------------------------------------|
| `startDate`  | date   | Filter results from this date (optional)    |
| `endDate`    | date   | Filter results from this date (optional)    |
| `pageNumber` | int    | Page number (default: 0)                    |
| `pageSize`   | int    | Page size (default: 12)                     |
| `sortedBy`   | string | Sort direction: ASC or DESC (default: DESC) |

**Example Request**

`GET /api/v1/metrics/search?startDate=2026-08-15&endDate=2026-08-25&pageNumber=&pageSize=&sortedBy=desc`

**Example Response**

```json
{
"content": [
    {
        "logId": 2129,
        "createdAt": "2026-09-10T09:21:00.138655",
        "cpu": {
            "processCpuLoad": 0.04998000799680128,
            "processCpuLoadFormatted": "0.05%",
            "systemCpuLoad": 3.5985605757696923,
            "systemCpuLoadFormatted": "3.60%",
            "systemAverageLoad": 1.0185546875,
            "systemAverageLoadFormatted": "1.02%",
            "cpuTemp": "36°C"
        },
        "memory": {...},
        "disk": {...},
        "network": {...},
        "uptime": {...}
    }
],
"pageable": {
    "pageNumber": 0,
    "pageSize": 12,
    "sort": {
        "sorted": true,
        "empty": false,
        "unsorted": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
},
"totalElements": 1988,
"totalPages": 166,
"last": false,
"size": 12,
"number": 0,
"sort": {
    "sorted": true,
    "empty": false,
    "unsorted": false
},
"numberOfElements": 12,
"first": true,
"empty": false
}
```

## Architecture

![Arcihtecture](./docs/metric-api-diagram.drawio.svg)

## Technology

- Java
- Maven
- Springboot

## Licence
- Apache License 2.0 
