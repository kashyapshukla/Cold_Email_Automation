## API Endpoint

### 1. **Send Bulk Mail with Attachment**
- **URL:** `http://localhost:8080/sendBulkMailWithAttachment`
- **Method:** `POST`
- **Content-Type:** `application/json`

#### Request Body:
```json
{
  "recipient": "xyx2@gmail.com",
  "msgBody": "hello",
  "subject": "hello",
  "attachment": "sdaakjshdkjhd.pdf",
  "csv": "sadksdsn2.csv"
}
