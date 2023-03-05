package com.estuardo.wyss.hospital.controller.responses;

import com.estuardo.wyss.hospital.treatment.Medicine;
import lombok.Getter;
import lombok.Setter;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/03/2023
 */
@Getter
@Setter
public class MedicineResponse extends StandardResponse {
    Medicine medicine;
}
