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
 * TODO refactor medical record, creating an object to store each visit to the hospital
 */

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

    public void updateMedicalRecord(MedicalRecord medicalRecord){
        this.message="Medical record successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(medicalRecord.getPatientId());

        if(Objects.nonNull(record)) {
            record.setAdditionalComments(medicalRecord.getAdditionalComments());
            record.setSymptoms(medicalRecord.getSymptoms());
            record.setDoctorId(medicalRecord.getDoctorId());
            record.setSymptomsStarted(medicalRecord.getSymptomsStarted());

            this.patientsRecordsTreeMap.replace(medicalRecord.getPatientId(), record);
            this.persister.store(this.patientsRecordsTreeMap);
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void deleteMedicalRecord(String patientKey){
        this.message="Medical record successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)) {
            if(record.getAnalysisDone().size()>0 || record.getProcedures().size()>0
                || record.getHospitalizations().size()>0 || record.getPrescriptions().size()>0
            ){
                this.message="It's not possible to delete the medical record you intend to, because it has valuable data on it. ";
            }else{
                this.patientsRecordsTreeMap.remove(patientKey);
                this.persister.store(this.patientsRecordsTreeMap);
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void updateLaboratoryAnalysis(LaboratoryAnalysis lab){
        this.message="Laboratory analysis successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(lab.getPatientId());

        if(Objects.nonNull(record)){
            List<LaboratoryAnalysis> analysisDone = record.getAnalysisDone();
            Optional<LaboratoryAnalysis> analysis = analysisDone.stream().filter(
                    p->p.getLaboratoryKey().equals(lab.getLaboratoryKey())
            ).findFirst();

            if(analysis.isPresent()) {
                LaboratoryAnalysis result = analysis.get();
                int indice = analysisDone.indexOf(result);

                if(indice>-1){
                    // replace the new laboratory analysis
                    // I replace result with lab and pursue the storage process
                    analysisDone.set(indice, lab);
                    record.setAnalysisDone(analysisDone);
                    this.patientsRecordsTreeMap.replace(lab.getPatientId(), record);
                    this.persister.store(this.patientsRecordsTreeMap);
                }else{
                    // there was an error
                    this.message="There was an error recovering the analysis from database.";
                }
            }else{
                this.message="The analysis doesn't exist in the database.";
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void deleteLaboratoryAnalysis(String patientKey, String labKey){
        this.message="Laboratory analysis successfully deleted.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)){
            List<LaboratoryAnalysis> labsDone = record.getAnalysisDone();
            Optional<LaboratoryAnalysis>labsRecorded = labsDone.stream().filter(
                    p->p.getLaboratoryKey().equals(labKey)
            ).findFirst();

            if(labsRecorded.isPresent()) {
                LaboratoryAnalysis result = labsRecorded.get();
                int indice = labsDone.indexOf(result);

                if(indice>-1){
                    // replace the new laboratory analysis
                    // I replace result with hospitalization and pursue the storage process
                    labsDone.remove(indice);
                    record.setAnalysisDone(labsDone);
                    this.patientsRecordsTreeMap.replace(patientKey, record);
                    this.persister.store(this.patientsRecordsTreeMap);
                }else{
                    // there was an error
                    this.message="There was an error recovering the Laboratory analysis from database.";
                }
            }else{
                this.message="The Laboratory analysis doesn't exist in the database.";
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void updatePrescription(Prescription prescription){
        this.message="Prescription successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(prescription.getPatientId());

        if(Objects.nonNull(record)){
            List<Prescription> prescriptionsDone = record.getPrescriptions();
            Optional<Prescription> prescriptionRecorded = prescriptionsDone.stream().filter(
                    p->p.getPrescriptionKey().equals(prescription.getPrescriptionKey())
            ).findFirst();

            if(prescriptionRecorded.isPresent()) {
                Prescription result = prescriptionRecorded.get();
                int indice = prescriptionsDone.indexOf(result);

                if(indice>-1){
                    // replace the new laboratory analysis
                    // I replace result with lab and pursue the storage process
                    prescriptionsDone.set(indice, prescription);
                    record.setPrescriptions(prescriptionsDone);
                    this.patientsRecordsTreeMap.replace(prescription.getPatientId(), record);
                    this.persister.store(this.patientsRecordsTreeMap);
                }else{
                    // there was an error
                    this.message="There was an error recovering the Prescription from database.";
                }
            }else{
                this.message="The Prescription doesn't exist in the database.";
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void deletePrescription(String patientKey, String prescriptionKey){
        this.message="Prescription successfully deleted.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)){
            List<Prescription> prescriptionsDone = record.getPrescriptions();
            Optional<Prescription>prescriptionRecorded = prescriptionsDone.stream().filter(
                    p->p.getPrescriptionKey().equals(prescriptionKey)
            ).findFirst();

            if(prescriptionRecorded.isPresent()) {
                Prescription result = prescriptionRecorded.get();
                int indice = prescriptionsDone.indexOf(result);

                if(indice>-1){
                    // replace the new laboratory analysis
                    // I replace result with hospitalization and pursue the storage process
                    prescriptionsDone.remove(indice);
                    record.setPrescriptions(prescriptionsDone);
                    this.patientsRecordsTreeMap.replace(patientKey, record);
                    this.persister.store(this.patientsRecordsTreeMap);
                }else{
                    // there was an error
                    this.message="There was an error recovering the prescription from database.";
                }
            }else{
                this.message="The prescription doesn't exist in the database.";
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void updateMedicalProcedure(MedicalProcedure medicalProcedure){
        this.message="Medical Procedure successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(medicalProcedure.getPatientId());

        if(Objects.nonNull(record)){
            List<MedicalProcedure> proceduresDone = record.getProcedures();
            Optional<MedicalProcedure> procedureRecorded = proceduresDone.stream().filter(
                    p->p.getProcedureKey().equals(medicalProcedure.getProcedureKey())
            ).findFirst();

            if(procedureRecorded.isPresent()) {
                MedicalProcedure result = procedureRecorded.get();
                int indice = proceduresDone.indexOf(result);

                if(indice>-1){
                    // replace the new laboratory analysis
                    // I replace result with lab and pursue the storage process
                    proceduresDone.set(indice, medicalProcedure);
                    record.setProcedures(proceduresDone);
                    this.patientsRecordsTreeMap.replace(medicalProcedure.getPatientId(), record);
                    this.persister.store(this.patientsRecordsTreeMap);
                }else{
                    // there was an error
                    this.message="There was an error recovering the Medical Procedure from database.";
                }
            }else{
                this.message="The Medical Procedure doesn't exist in the database.";
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void deleteMedicalProcedure(String patientKey, String medicalProcedureKey){
        this.message="Medical procedure successfully deleted.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)){
            List<MedicalProcedure> medicalProceduresDone = record.getProcedures();
            Optional<MedicalProcedure>medicalProcedureRecorded = medicalProceduresDone.stream().filter(
                    p->p.getProcedureKey().equals(medicalProcedureKey)
            ).findFirst();

            if(medicalProcedureRecorded.isPresent()) {
                MedicalProcedure result = medicalProcedureRecorded.get();
                int indice = medicalProceduresDone.indexOf(result);

                if(indice>-1){
                    // replace the new laboratory analysis
                    // I replace result with hospitalization and pursue the storage process
                    medicalProceduresDone.remove(indice);
                    record.setProcedures(medicalProceduresDone);
                    this.patientsRecordsTreeMap.replace(patientKey, record);
                    this.persister.store(this.patientsRecordsTreeMap);
                }else{
                    // there was an error
                    this.message="There was an error recovering the hospitalization from database.";
                }
            }else{
                this.message="The hospitalization Procedure doesn't exist in the database.";
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void updateHospitalization(Hospitalization hospitalization){
        this.message="Hospitalization successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(hospitalization.getPatientId());

        if(Objects.nonNull(record)){
            List<Hospitalization> hospitalizationsDone = record.getHospitalizations();
            Optional<Hospitalization> procedureRecorded = hospitalizationsDone.stream().filter(
                    p->p.getHospitalizationKey().equals(hospitalization.getHospitalizationKey())
            ).findFirst();

            if(procedureRecorded.isPresent()) {
                Hospitalization result = procedureRecorded.get();
                int indice = hospitalizationsDone.indexOf(result);

                if(indice>-1){
                    // replace the new laboratory analysis
                    // I replace result with hospitalization and pursue the storage process
                    hospitalizationsDone.set(indice, hospitalization);
                    record.setHospitalizations(hospitalizationsDone);
                    this.patientsRecordsTreeMap.replace(hospitalization.getPatientId(), record);
                    this.persister.store(this.patientsRecordsTreeMap);
                }else{
                    // there was an error
                    this.message="There was an error recovering the hospitalization from database.";
                }
            }else{
                this.message="The hospitalization Procedure doesn't exist in the database.";
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

    public void deleteHospitalization(String patientKey, String hospitalizationKey){
        this.message="Hospitalization successfully deleted.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)){
            List<Hospitalization> hospitalizationsDone = record.getHospitalizations();
            Optional<Hospitalization> hospitalizationRecorded = hospitalizationsDone.stream().filter(
                    p->p.getHospitalizationKey().equals(hospitalizationKey)
            ).findFirst();

            if(hospitalizationRecorded.isPresent()) {
                Hospitalization result = hospitalizationRecorded.get();
                int indice = hospitalizationsDone.indexOf(result);

                if(indice>-1){
                    // replace the new laboratory analysis
                    // I replace result with hospitalization and pursue the storage process
                    hospitalizationsDone.remove(indice);
                    record.setHospitalizations(hospitalizationsDone);
                    this.patientsRecordsTreeMap.replace(patientKey, record);
                    this.persister.store(this.patientsRecordsTreeMap);
                }else{
                    // there was an error
                    this.message="There was an error recovering the hospitalization from database.";
                }
            }else{
                this.message="The hospitalization Procedure doesn't exist in the database.";
            }
        }else{
            this.message="The patient doesn't have a medical record.";
        }
    }

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
