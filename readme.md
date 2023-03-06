

**Hospital Management App**
Managing a Hospital is a complex application.

With this code, I am putting together the principal objects the application would instantiate, to 
attend patients in the daily basis.

**Personnel or employees**
To handle any kind of personnel the hospital needs. For the sake of this app, I am targeting on medics 
or doctors, focusing on his or her speciality and type to distinguish him or her from the rest of 
people.

**Patient** is the person who goes to the hospital to be treated medically.

The process I am following is, the patient reach the hospital:

* Ask to be register
* or says I am a register patient

**Next ask for an appointment**
At this point the application:

* Create a medical record
* or pull the medical record from the microstream database

With the medical record the application:

`public class MedicalRecord {
    private String recordKey;
    private String patientId;
    private String doctorId;
    private List<Appointment> appointments;
    private String additionalComments;
}`

* inserts the appointment in the medical record

With the Appointment object the application is able to store or track what the doctor(s) did to
the patient:

* Laboratory analysis
  * analysis description and cost
* Prescriptions
  * medicine description and cost
* Medical procedures
  * List of doctors involved in the procedure
    * the characteristics of each member
    * and the fees to be billed
* Hospitalizations
  * Room assigned
  * Room fees to be billed

`public class Appointment {
    private String appointmentKey;
    private String patientId;
    private String doctorId;
    private String symptoms;
    private Date appointmentDate;
    private Date symptomsStartedDate;
    private String appointmentDescription;
    private List<LaboratoryAnalysis> analysisDone=new ArrayList<>();
    private List<Prescription> prescriptions=new ArrayList<>();
    private List<MedicalProcedure> procedures=new ArrayList<>();
    private List<Hospitalization> hospitalizations=new ArrayList<>();
}`

    `public class LaboratoryAnalysis {
        private String laboratoryKey;
        private String patientId;
        private String doctorId;
        private Analysis analysis;
    }`

            `public class Analysis {
                private String analysisKey;
                private String analysisType;
                private String analysisName;
                private String analysisDescription;
                private BigDecimal analysisPrice;
            }`

    `public class Prescription {
        private String prescriptionKey;
        private String patientId;
        private String doctorId;
        private Medicine medicine;
        private Date prescriptionDate;
    }`

            `public class Medicine {
                private String medicineKey;
                private String medicineName;
                private String medicineSupplier;
                private BigDecimal medicinePrice;
            }`

    `public class MedicalProcedure {
        private String procedureKey;
        private String patientId;
        private String primaryDoctorId;
        private List<DoctorInvolved> doctors;
        private String additionalComments;
    }`

            `public class DoctorInvolved {
                private String doctorInvolvedKey;
                private Personnel doctor;
                private BigDecimal medicalFee;
            }`

    `public class Hospitalization {
        private String hospitalizationKey;
        private String patientId;
        private String primaryDoctorId;
        private String description;
        private Date hospitalizationDate;
        private Date hospitalizationEnds;
        private HospitalRoom room;
        private String additionalComments;
    }`

            `public class HospitalRoom {
                private String roomKey;
                private String roomType;
                private String roomName;
                private String roomDescription;
                private BigDecimal roomFee;
            }`