## MySQL Database Design

## Tablas Principales del Sistema de Clínica

### Table: patients
- id: INT, Primary Key, Auto Increment
- first_name: VARCHAR(50), Not Null
- last_name: VARCHAR(50), Not Null
- email: VARCHAR(100), Unique, Not Null
- phone: VARCHAR(20), Not Null
- date_of_birth: DATE, Not Null
- address: TEXT
- emergency_contact: VARCHAR(100)
- created_at: TIMESTAMP, Default Current_Timestamp
- updated_at: TIMESTAMP, Default Current_Timestamp on Update Current_Timestamp

### Table: doctors
- id: INT, Primary Key, Auto Increment
- first_name: VARCHAR(50), Not Null
- last_name: VARCHAR(50), Not Null
- email: VARCHAR(100), Unique, Not Null
- phone: VARCHAR(20), Not Null
- specialization: VARCHAR(100), Not Null
- license_number: VARCHAR(50), Unique, Not Null
- biography: TEXT
- is_active: BOOLEAN, Default True
- created_at: TIMESTAMP, Default Current_Timestamp

### Table: appointments
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key → doctors(id)
- patient_id: INT, Foreign Key → patients(id)
- appointment_date: DATE, Not Null
- appointment_time: TIME, Not Null
- duration: INT, Default 60
- status: ENUM('scheduled', 'completed', 'cancelled', 'no_show'), Default 'scheduled'
- reason: TEXT
- notes: TEXT
- created_at: TIMESTAMP, Default Current_Timestamp

### Table: admin
- id: INT, Primary Key, Auto Increment
- username: VARCHAR(50), Unique, Not Null
- email: VARCHAR(100), Unique, Not Null
- password_hash: VARCHAR(255), Not Null
- role: ENUM('super_admin', 'clinic_manager'), Default 'clinic_manager'
- is_active: BOOLEAN, Default True
- last_login: TIMESTAMP
- created_at: TIMESTAMP, Default Current_Timestamp

### Table: doctor_availability
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key → doctors(id)
- day_of_week: ENUM('monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday'), Not Null
- start_time: TIME, Not Null
- end_time: TIME, Not Null
- is_available: BOOLEAN, Default True

### Table: clinic_locations
- id: INT, Primary Key, Auto Increment
- name: VARCHAR(100), Not Null
- address: TEXT, Not Null
- phone: VARCHAR(20)
- email: VARCHAR(100)
- is_active: BOOLEAN, Default True

### Table: prescriptions
- id: INT, Primary Key, Auto Increment
- appointment_id: INT, Foreign Key → appointments(id)
- patient_id: INT, Foreign Key → patients(id)
- doctor_id: INT, Foreign Key → doctors(id)
- medication_name: VARCHAR(100), Not Null
- dosage: VARCHAR(50)
- instructions: TEXT
- prescribed_date: DATE, Not Null
- refills_remaining: INT, Default 0

### Table: medical_records
- id: INT, Primary Key, Auto Increment
- patient_id: INT, Foreign Key → patients(id)
- doctor_id: INT, Foreign Key → doctors(id)
- appointment_id: INT, Foreign Key → appointments(id)
- diagnosis: TEXT
- symptoms: TEXT
- treatment: TEXT
- record_date: DATE, Not Null
- created_at: TIMESTAMP, Default Current_Timestamp


# MongoDB Collection Design

## Colecciones para Datos Flexibles

### Collection: medical_notes
```json
{
  "_id": "ObjectId('64abc123456def789')",
  "appointmentId": 105,
  "patientId": 67890,
  "doctorId": 54321,
  "noteType": "clinical_observation",
  "content": "Paciente presenta mejoría significativa en los síntomas reportados. Se observa reducción del 70% en la inflamación. Recomendado continuar con el tratamiento prescrito y realizar seguimiento en 2 semanas.",
  "tags": ["mejoria", "seguimiento", "tratamiento_continuo"],
  "attachments": [
    {
      "fileName": "radiografia_torax.pdf",
      "fileType": "pdf",
      "uploadDate": "2024-01-15T10:30:00Z",
      "fileSize": 2457600
    }
  ],
  "metadata": {
    "createdBy": "dr_rodriguez",
    "createdAt": "2024-01-15T14:20:00Z",
    "updatedAt": "2024-01-15T14:20:00Z",
    "version": 1,
    "isConfidential": true
  }
}

### Collection: patient_feedback
```json
{
  "_id": "ObjectId('64abc123456def790')",
  "appointmentId": 105,
  "patientId": 67890,
  "doctorId": 54321,
  "rating": 5,
  "comments": "El doctor fue muy atento y explicó todo claramente. El tiempo de espera fue mínimo y la atención excelente.",
  "categories": ["profesionalismo", "comunicacion", "puntualidad"],
  "sentiment": "positive",
  "metadata": {
    "submittedAt": "2024-01-16T09:15:00Z",
    "platform": "web",
    "language": "es",
    "isAnonymous": false
  },
  "followUp": {
    "required": false,
    "contacted": false
  }
}

### Collection: prescription_details
```json
{
  "_id": "ObjectId('64abc123456def791')",
  "prescriptionId": 12345,
  "patientId": 67890,
  "doctorId": 54321,
  "appointmentId": 105,
  "medications": [
    {
      "name": "Amoxicilina",
      "dosage": "500mg",
      "form": "capsula",
      "frequency": "Cada 8 horas",
      "duration": "7 días",
      "instructions": "Tomar con alimentos",
      "sideEffects": ["nausea", "diarrea"],
      "contraindications": ["alergia_penicilina"]
    },
    {
      "name": "Ibuprofeno",
      "dosage": "400mg",
      "form": "tableta",
      "frequency": "Cada 6 horas según dolor",
      "duration": "3 días",
      "instructions": "Tomar después de comer",
      "sideEffects": ["acidez_estomacal"],
      "contraindications": ["ulcera_gastrica"]
    }
  ],
  "tags": ["antibiotico", "antiinflamatorio", "dolor"],
  "pharmacyInfo": {
    "preferredPharmacy": "Farmacia San José",
    "location": "Av. Principal 123",
    "phone": "+1234567890"
  },
  "metadata": {
    "createdAt": "2024-01-15T16:45:00Z",
    "expiresAt": "2024-02-15T16:45:00Z",
    "status": "active",
    "refillsRemaining": 1
  }
}

### Collection: chat_messages
```json
{
  "_id": "ObjectId('64abc123456def792')",
  "conversationId": "conv_67890_54321",
  "patientId": 67890,
  "doctorId": 54321,
  "senderType": "patient",
  "messageType": "text",
  "content": "Buenos días doctor, tengo una duda sobre la medicación. ¿Puedo tomar ibuprofeno si estoy tomando amoxicilina?",
  "attachments": [],
  "metadata": {
    "sentAt": "2024-01-16T08:30:00Z",
    "deliveredAt": "2024-01-16T08:30:05Z",
    "readAt": "2024-01-16T08:35:20Z",
    "messageStatus": "read"
  },
  "replyTo": null
}

### Collection: audit_logs
```json
{
  "_id": "ObjectId('64abc123456def793')",
  "eventType": "patient_registration",
  "userId": 67890,
  "userType": "patient",
  "action": "register",
  "details": {
    "email": "paciente@ejemplo.com",
    "phone": "+1234567890",
    "registrationSource": "web"
  },
  "ipAddress": "192.168.1.100",
  "userAgent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36",
  "metadata": {
    "timestamp": "2024-01-15T09:00:00Z",
    "sessionId": "sess_abc123def456",
    "success": true
  }
}
