package com.estuardo.wyss.hospital.treatment;

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
public class HospitalRoom {
    private String roomKey;
    private String roomType;
    private String roomName;
    private String roomDescription;
    private BigDecimal roomFee;
}
