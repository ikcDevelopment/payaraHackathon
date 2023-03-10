/*Copyright 2023 Juan Estuardo Wyss Rosito
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.estuardo.wyss.hospital.patient.entities;

import com.estuardo.wyss.hospital.treatment.Appointment;
import com.estuardo.wyss.hospital.treatment.Hospitalization;
import com.estuardo.wyss.hospital.treatment.MedicalProcedure;
import com.estuardo.wyss.hospital.treatment.Prescription;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class MedicalRecord {
    private String recordKey;
    private String patientId;
    private String doctorId;
    private List<Appointment> appointments;
    private String additionalComments;
}
