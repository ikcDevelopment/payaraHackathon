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

package com.estuardo.wyss.hospital.process.service;

import com.estuardo.wyss.hospital.patient.entities.LaboratoryAnalysis;
import com.estuardo.wyss.hospital.patient.entities.MedicalRecord;
import com.estuardo.wyss.hospital.treatment.Hospitalization;
import com.estuardo.wyss.hospital.treatment.MedicalProcedure;
import com.estuardo.wyss.hospital.treatment.Prescription;
import jakarta.inject.Inject;
import one.microstream.integrations.cdi.types.Storage;
import one.microstream.persistence.types.Persister;

import java.util.*;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Storage
public class MedicalRecordService {

    @Inject
    private transient Persister persister;

    private final TreeMap<String, MedicalRecord> patientsRecordsTreeMap = new TreeMap<>();

    private String message="";

    /**
     * Create the patient's medical record for the first time
     * @param record
     * @return
     */
    public boolean addMedicalRecord(MedicalRecord record){
        this.message="Medical record for the Patient successfully added.";
        if(!this.patientsRecordsTreeMap.containsKey(record.getPatientId())){
            this.patientsRecordsTreeMap.put(record.getPatientId(), record);
            this.persister.store(this.patientsRecordsTreeMap);
            return true;
        }else{
            this.message="Patient already has a medical record in database.";
            return false;
        }
    }

    public boolean addLaboratoryAnalysis(String patientId, LaboratoryAnalysis lab){
        this.message="Laboratory analysis successfully added.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientId);

        if(Objects.nonNull(record)){
            record.getAnalysisDone().add(lab);
            this.persister.store(this.patientsRecordsTreeMap);
            return true;
        }else{
            this.message="A problem was found while adding the laboratory analysis.";
            return false;
        }
    }

    public boolean addPrescription(String patientId, Prescription medicine){
        this.message="Prescription successfully added.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientId);

        if(Objects.nonNull(record)){
            record.getPrescriptions().add(medicine);
            this.persister.store(this.patientsRecordsTreeMap);
            return true;
        }else{
            this.message="A problem was found while adding the prescription.";
            return false;
        }
    }

    public boolean addMedicalProcedure(String patientId, MedicalProcedure procedure){
        this.message="Medical procedure successfully added.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientId);

        if(Objects.nonNull(record)){
            record.getProcedures().add(procedure);
            this.persister.store(this.patientsRecordsTreeMap);
            return true;
        }else{
            this.message="A problem was found while adding the medical procedure.";
            return false;
        }
    }

    public boolean addHospitalization(String patientId, Hospitalization hospitalization){
        this.message="Hospitalization successfully added.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientId);

        if(Objects.nonNull(record)){
            record.getHospitalizations().add(hospitalization);
            this.persister.store(this.patientsRecordsTreeMap);
            return true;
        }else{
            this.message="A problem was found while adding the hospitalization.";
            return false;
        }
    }

    public void updateMedicalRecord(){}

    public void deleteMedicalRecord(){}

    public void updateLaboratoryAnalysis(){}

    public void deleteLaboratoryAnalysis(){}

    public void updatePrescription(){}

    public void deletePrescription(){}

    public void updateMedicalProcedure(){}

    public void deleteMedicalProcedure(){}

    public void updateHospitalization(){}

    public void deleteHospitalization(){}

    @Override
    public boolean equals(final Object o){
        if(this == o){
            return true;
        }else{
            if(o == null || this.getClass() != o.getClass()) {
                return false;
            }
        }

        final MedicalRecordService service  = (MedicalRecordService)o;
        return Objects.equals(this.patientsRecordsTreeMap, service.patientsRecordsTreeMap);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.patientsRecordsTreeMap);
    }
}
