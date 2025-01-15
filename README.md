## API Endpoint

### 1. **Send Bulk Mail with Attachment**
- **URL:** `http://localhost:8080/sendBulkMailWithAttachment`
- **Method:** `POST`
- **Content-Type:** `application/json`

#### Request Body:
```json
{
  "recipient": "kashyapshukla32@gmail.com",
  "msgBody": "hello",
  "subject": "hello",
  "attachment": "C:/Users/kashy/OneDrive/Desktop/Helix/2.pdf",
  "csv": "C:/Users/kashy/Downloads/12.csv"
}