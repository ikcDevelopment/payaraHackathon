package com.estuardo.wyss.hospital.patient.entities;

import com.estuardo.wyss.hospital.treatment.Analysis;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class LaboratoryAnalysis {
    private String laboratoryKey;
    private String patientId;
    private String doctorId;
    private Analysis analysis;
}
