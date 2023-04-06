package com.estuardo.wyss.hospital.treatment;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
@Getter
@Setter
public class AppointmentParam {
    private String appointmentKey;
    private String patientId;
    private String doctorId;
    private String symptoms;
    private String appointmentDate;
    private String symptomsStartedDate;
    private String appointmentDescription;
}
