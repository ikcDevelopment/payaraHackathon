package com.estuardo.wyss.hospital.treatment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class MedicalProcedure {
    private String procedureKey;
    private String patientId;
    private String primaryDoctorId;
    private List<DoctorInvolved> doctors;
    private String additionalComments;
}
