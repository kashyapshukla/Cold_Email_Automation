# Cold Email Backend

Run:

- Build: `./mvnw -q -DskipTests package`
- Start: `./mvnw spring-boot:run`
- UI: open `http://localhost:8080/` for configuration and bulk send

APIs (base `/api`):
- POST `/sendBulk` { recipients: string[], subject: string, body: string }
- POST `/config` SMTP config
- GET `/config` current config (no password)

## Docker

Build image:

```bash
docker build -t cold-email:latest .
```

Run container:

```bash
docker run -p 8080:8080 --name cold-email cold-email:latest
```

Open `http://localhost:8080/` to configure SMTP and send emails.

Pass JVM options (optional):

```bash
docker run -e JAVA_OPTS="-Xms256m -Xmx512m" -p 8080:8080 cold-email:latest
```
