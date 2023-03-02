package com.estuardo.wyss.hospital.hr.personnel.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class Personnel {
    private String fiscalId;
    private String firstName;
    private String lastName;
    private String personalId;
    private String typeOfMember;
    private String speciality;
    private String smartPhone;
    private String homePhone;
    private String officeHome;
    private String email;
}
