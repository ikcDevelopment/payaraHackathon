package com.estuardo.wyss.hospital.patient.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class Patient {
    private String fiscalId;
    private String firstName;
    private String lastName;
    private String personalId;
    private String smartPhone;
    private String homePhone;
    private String officeHome;
    private String email;
    private String bloodType;
    private String allergicTo;
    private String inEmergencyCallTo;
    private String inEmergencySmartPhone;
    private String inEmergencyHomePhone;
    private String inEmergencyOfficeHome;
    private String inEmergencyEmail;
}
