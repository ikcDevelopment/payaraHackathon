package com.estuardo.wyss.hospital.treatment;

import com.estuardo.wyss.hospital.patient.entities.LaboratoryAnalysis;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
@Getter
@Setter
public class Appointment {
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
}
