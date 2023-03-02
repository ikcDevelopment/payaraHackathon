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
public class Medicine {
    private String medicineKey;
    private String medicineName;
    private String medicineSupplier;
    private BigDecimal medicinePrice;
}
