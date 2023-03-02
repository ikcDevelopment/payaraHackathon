package com.estuardo.wyss.hospital.treatment;

import com.estuardo.wyss.hospital.hr.personnel.entities.Personnel;
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
public class DoctorInvolved {
    private String doctorInvolvedKey;
    private Personnel doctor;
    private BigDecimal medicalFee;
}
