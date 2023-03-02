package com.estuardo.wyss.hospital.storage;

import com.estuardo.wyss.hospital.hr.personnel.entities.Personnel;
import com.estuardo.wyss.hospital.patient.entities.LaboratoryAnalysis;
import com.estuardo.wyss.hospital.patient.entities.MedicalRecord;
import com.estuardo.wyss.hospital.patient.entities.Patient;
import com.estuardo.wyss.hospital.treatment.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class DataRoot {
    private final List<Personnel> personnel = new ArrayList<>();
    // ?
    private final List<LaboratoryAnalysis> laboratoryAnalyses = new ArrayList<>();

    private final List<MedicalRecord> medicalRecords = new ArrayList<>();

    private final List<Patient> patients = new ArrayList<>();

    private final List<Analysis> analyses = new ArrayList<>();

    private final List<Hospitalization> hospitalizations = new ArrayList<>();

    private final List<HospitalRoom> hospitalRooms= new ArrayList<>();

    private final List<MedicalProcedure> medicalProcedures = new ArrayList<>();

    private final List<Medicine> medicines = new ArrayList<>();

}
