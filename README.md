# Cold Email Backend

Run:

- Build: `./mvnw -q -DskipTests package`
- Start: `./mvnw spring-boot:run`
- UI: open `http://localhost:8080/` for configuration and bulk send

APIs (base `/api`):
- POST `/sendBulk` { recipients: string[], subject: string, body: string }
- POST `/config` SMTP config
- GET `/config` current config (no password)
