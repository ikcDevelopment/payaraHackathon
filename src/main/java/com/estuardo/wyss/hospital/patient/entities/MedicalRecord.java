package com.estuardo.wyss.hospital.patient.entities;

import com.estuardo.wyss.hospital.treatment.Hospitalization;
import com.estuardo.wyss.hospital.treatment.MedicalProcedure;
import com.estuardo.wyss.hospital.treatment.Prescription;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class MedicalRecord {
    private String recordKey;
    private String patientId;
    private String doctorId;
    private String symptoms;
    private Date symptomsStarted;
    private List<LaboratoryAnalysis> analysisDone;
    private List<Prescription> prescriptions;
    private List<MedicalProcedure> procedures;
    private List<Hospitalization> hospitalizations;
    private String additionalComments;
}
