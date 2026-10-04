# Bounded Context Progress

- Total bounded contexts: 52
- Completed bounded contexts: 42
- Current bounded context: V43 `chat_participants` (`chatparticipant`)
- Next bounded context: V44 `chat_messages` (`chatmessage`)

## Completed migrations

| Migration | Table | Package |
| --- | --- | --- |
| V1 | `countries` | `country` |
| V2 | `state_regions` | `stateregion` |
| V3 | `city_municipalities` | `citymunicipality` |
| V4 | `genders` | `gender` |
| V5 | `document_types` | `documenttype` |
| V6 | `professional_types` | `professionaltype` |
| V7 | `relationship_types` | `relationshiptype` |
| V8 | `contacts` | `contact` |
| V9 | `phone_contacts` | `phonecontact` |
| V10 | `email_contacts` | `emailcontact` |
| V11 | `professionals` | `professional` |
| V12 | `patients` | `patient` |
| V13 | `patient_contacts` | `patientcontact` |
| V14 | `studies` | `study` |
| V15 | `professional_studies` | `professionalstudy` |
| V16 | `clinical_record_statuses` | `clinicalrecordstatus` |
| V17 | `clinical_records` | `clinicalrecord` |
| V18 | `encounter_types` | `encountertype` |
| V19 | `encounter_modalities` | `encountermodality` |
| V20 | `encounter_statuses` | `encounterstatus` |
| V21 | `encounters` | `encounter` |
| V22 | `clinical_notes` | `clinicalnote` |
| V23 | `mental_status_exams` | `mentalstatusexam` |
| V24 | `risk_levels` | `risklevel` |
| V25 | `risk_assessments` | `riskassessment` |
| V26 | `treatment_statuses` | `treatmentstatus` |
| V27 | `treatment_plans` | `treatmentplan` |
| V28 | `treatment_goal_statuses` | `treatmentgoalstatus` |
| V29 | `treatment_goals` | `treatmentgoal` |
| V30 | `medication_routes` | `medicationroute` |
| V31 | `assessment_types` | `assessmenttype` |
| V32 | `consent_types` | `consenttype` |
| V33 | `diagnostic_systems` | `diagnosticsystem` |
| V34 | `conversation_statuses` | `conversationstatus` |
| V35 | `priorities` | `priority` |
| V36 | `sender_types` | `sendertype` |
| V37 | `message_types` | `messagetype` |
| V38 | `ai_runs_statuses` | `airunstatus` |
| V39 | `escalations_statuses` | `escalationstatus` |
| V40 | `provider_models_ai` | `aiprovidermodel` |
| V41 | `ai_models` | `aimodel` |
| V42 | `chat_conversations` | `chatconversation` |

## Pending migrations

V43 through V52 remain pending. The `Current bounded context` field is the
authoritative next implementation target.
