package com.estuardo.wyss.hospital.treatment;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class Prescription {
    private String prescriptionKey;
    private String patientId;
    private String doctorId;
    private Medicine medicine;
    private Date prescriptionDate;
}
