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
public class Hospitalization {
    private String hospitalizationKey;
    private String patientId;
    private String primaryDoctorId;
    private String description;
    private Date hospitalizationDate;
    private Date hospitalizationEnds;
    private HospitalRoom room;
    private String additionalComments;
}
