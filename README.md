# Spring-Based-AlertingSystem
The user of this tool is a nurse in the ICU. The patient’s vital signs are sensed and formatted into a JSON-string by a patient-monitoring device. The formatted data keeps arriving periodically. When a parameter goes out-of-range, an alert is given on the console-output.
##The domain layer of the API consists of following classes.
1. Patient class consists of patient Id, name, gender, contact, aadhar no. The patient is associated with one ICU bed. 
2. Icu class consists of bedid and occupancy. If the bed is occupied then occupancy is set to 1 and patient object is associated with the ICU bed.
3. PatientVitals class consists of oxygenlevel, pulser rate, temperature along with the patient id.
The service layer has following functionalities.
1.The PatientMonitorSimulatorServiceImpl class generates the patient vitals.
2. The VitalValidationServiceForErrorsImpl class checks the vitals of patients whether the values are valid or not. 
3. The VitalCheckServiceImpl class checks whether the values are within the specified range and if not it sets the flag.
4. The Alerting raising module raises an alert when the values are out of range.
