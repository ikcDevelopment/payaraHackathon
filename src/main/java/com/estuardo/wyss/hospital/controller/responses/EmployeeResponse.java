package com.estuardo.wyss.hospital.controller.responses;

import com.estuardo.wyss.hospital.hr.personnel.entities.Personnel;
import lombok.Getter;
import lombok.Setter;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class EmployeeResponse extends StandardResponse{
    Personnel employee;
}
