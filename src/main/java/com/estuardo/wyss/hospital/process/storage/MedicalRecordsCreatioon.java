package com.estuardo.wyss.hospital.process.storage;

import com.estuardo.wyss.hospital.patient.entities.MedicalRecord;
import com.estuardo.wyss.hospital.process.service.MedicalRecordService;
import com.estuardo.wyss.hospital.treatment.Appointment;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Date;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
@ApplicationScoped
public class MedicalRecordsCreatioon {
    @Inject
    MedicalRecordService mrs;

    @PostConstruct
    private void createDataBase(){
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(new Date(2023, 3, 1));
        appointment.setAppointmentDescription("Laringitis");
        appointment.setAppointmentKey("456458f4");
        appointment.setDoctorId("med-001");
        appointment.setPatientId("1919466");
        appointment.setSymptoms("Dolor garganta");

        Appointment appointment2 = new Appointment();
        appointment.setAppointmentDate(new Date(2023, 3, 5));
        appointment.setAppointmentDescription("Pulmonia");
        appointment.setAppointmentKey("43pR458f4");
        appointment.setDoctorId("med-001");
        appointment.setPatientId("45236589");
        appointment.setSymptoms("Exceso de tos y dolor pecho");

        MedicalRecord mr1 = new MedicalRecord();
        mr1.setDoctorId(appointment.getDoctorId());
        mr1.setPatientId(appointment.getPatientId());
        mr1.setRecordKey("kljklj&$fg");
        mr1.getAppointments().add(appointment);

        this.mrs.addMedicalRecord(mr1);

        MedicalRecord mr2 = new MedicalRecord();
        mr2.setDoctorId(appointment2.getDoctorId());
        mr2.setPatientId(appointment2.getPatientId());
        mr2.setRecordKey("kzoemtb#igk@");
        this.mrs.addMedicalRecord(mr2);
        this.mrs.addAppointment(appointment2);
    }
}
