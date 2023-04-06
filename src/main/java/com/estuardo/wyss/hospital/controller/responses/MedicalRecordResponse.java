package com.estuardo.wyss.hospital.controller.responses;

import com.estuardo.wyss.hospital.patient.entities.MedicalRecord;
import lombok.Getter;
import lombok.Setter;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
@Getter
@Setter
public class MedicalRecordResponse extends StandardResponse {
    MedicalRecord medicalRecord;
}
