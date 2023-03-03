package com.estuardo.wyss.hospital.controller.responses;

import com.estuardo.wyss.hospital.patient.entities.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
@Getter
@Setter
public class PatientListResponse extends StandardResponse{
    List<Patient> patients;
}
