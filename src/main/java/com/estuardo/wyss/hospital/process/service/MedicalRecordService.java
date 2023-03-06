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
import com.estuardo.wyss.hospital.treatment.Appointment;
import com.estuardo.wyss.hospital.treatment.Hospitalization;
import com.estuardo.wyss.hospital.treatment.MedicalProcedure;
import com.estuardo.wyss.hospital.treatment.Prescription;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
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
    @Getter
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

    public boolean addAppointment(Appointment appointment){
        this.message="Medical record for the Patient successfully added.";
        if(this.patientsRecordsTreeMap.containsKey(appointment.getPatientId())){
            MedicalRecord record = this.patientsRecordsTreeMap.get(appointment.getPatientId());

            if(Objects.nonNull(record)){
                Optional<Appointment> appointmentE = record .getAppointments().stream()
                        .filter(r -> r.getAppointmentKey().equals(appointment.getAppointmentKey())).findFirst();

                if(appointmentE.isEmpty()){
                    record.getAppointments().add(appointment);
                    this.patientsRecordsTreeMap.put(record.getPatientId(), record);
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="The appointment already exists in database.";
                    return false;
                }

            }else{
                // there is no record
                this.message="Patient doesn't have a medical.";
                return false;
            }

        }else{
            this.message="Patient already has a medical record in database.";
            return false;
        }
    }

    public boolean addLaboratoryAnalysis(String patientId, String appointmentKey, LaboratoryAnalysis lab){
        this.message="Laboratory analysis successfully added.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientId);

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record .getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);
                // add appointment
                appointmentRecovered.getAnalysisDone().add(lab);
                // update appointments in record
                record.getAppointments().set(appointmentIndex, appointmentRecovered);
                // update treeMap with record
                this.patientsRecordsTreeMap.replace(patientId, record);
                // update store
                this.persister.store(this.patientsRecordsTreeMap);
                return true;
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="Patient doesn't have a medical.";
            return false;
        }
    }

    public boolean addPrescription(String patientId, String appointmentKey, Prescription medicine){
        this.message="Prescription successfully added.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientId);

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);
                // add medicine (prescription)
                appointmentRecovered.getPrescriptions().add(medicine);
                // update appointments in record
                record.getAppointments().set(appointmentIndex, appointmentRecovered);
                // update treeMap with record
                this.patientsRecordsTreeMap.replace(patientId, record);
                // update store
                this.persister.store(this.patientsRecordsTreeMap);
                return true;
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }

        }else{
            this.message="Patient doesn't have a medical.";
            return false;
        }
    }

    public boolean addMedicalProcedure(String patientId, String appointmentKey, MedicalProcedure procedure){
        this.message="Medical procedure successfully added.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientId);

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);
                // add procedure to the appointment
                appointmentRecovered.getProcedures().add(procedure);
                // update appointments in record
                record.getAppointments().set(appointmentIndex, appointmentRecovered);
                // update treeMap with record
                this.patientsRecordsTreeMap.replace(patientId, record);
                // update store
                this.persister.store(this.patientsRecordsTreeMap);
                return true;
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="Patient doesn't have a medical.";
            return false;
        }
    }

    public boolean addHospitalization(String patientId, String appointmentKey, Hospitalization hospitalization){
        this.message="Hospitalization successfully added.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientId);

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);
                // add hospitalization to the appointment
                appointmentRecovered.getHospitalizations().add(hospitalization);
                // update appointments in record
                record.getAppointments().set(appointmentIndex, appointmentRecovered);
                // update treeMap with record
                this.patientsRecordsTreeMap.replace(patientId, record);
                // update store
                this.persister.store(this.patientsRecordsTreeMap);
                return true;
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="Patient doesn't have a medical.";
            return false;
        }
    }

    public boolean updateMedicalRecord(MedicalRecord medicalRecord){
        this.message="Medical record successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(medicalRecord.getPatientId());

        if(Objects.nonNull(record)) {
            record.setAdditionalComments(medicalRecord.getAdditionalComments());
            record.setDoctorId(medicalRecord.getDoctorId());

            this.patientsRecordsTreeMap.replace(medicalRecord.getPatientId(), record);
            this.persister.store(this.patientsRecordsTreeMap);
            return true;
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
        }
    }

    public boolean deleteMedicalRecord(String patientKey){
        this.message="Medical record successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)) {
            if(record.getAppointments().size()>0){
                this.message="It's not possible to delete the medical record you intend to, because it has valuable data on it. ";
                return false;
            }else{
                this.patientsRecordsTreeMap.remove(patientKey);
                this.persister.store(this.patientsRecordsTreeMap);
                return true;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
        }
    }

    public boolean updateLaboratoryAnalysis(String appointmentKey, LaboratoryAnalysis lab){
        this.message="Laboratory analysis successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(lab.getPatientId());

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);

                List<LaboratoryAnalysis> analysisDone = appointmentRecovered.getAnalysisDone();

                Optional<LaboratoryAnalysis> analysisE = analysisDone.stream()
                        .filter(a -> a.getLaboratoryKey().equals(lab.getLaboratoryKey())).findFirst();

                if(analysisE.isPresent()){
                    LaboratoryAnalysis laboratoryAnalysisRecovered = analysisE.get();
                    int laboratoryAnalysisIndex = appointmentRecovered.getAnalysisDone().indexOf(laboratoryAnalysisRecovered);
                    // replace element
                    appointmentRecovered.getAnalysisDone().set(laboratoryAnalysisIndex, lab);
                    // update appointments in record
                    record.getAppointments().set(appointmentIndex, appointmentRecovered);
                    // update treeMap with record
                    this.patientsRecordsTreeMap.replace(lab.getPatientId(), record);
                    // update store
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="Laboratory analysis doesn't exist.";
                    return false;
                }
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
        }
    }

    public boolean deleteLaboratoryAnalysis(String patientKey, String appointmentKey, String labKey){
        this.message="Laboratory analysis successfully deleted.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);

                List<LaboratoryAnalysis> analysisDone = appointmentRecovered.getAnalysisDone();

                Optional<LaboratoryAnalysis> analysisE = analysisDone.stream()
                        .filter(a -> a.getLaboratoryKey().equals(labKey)).findFirst();

                if(analysisE.isPresent()){
                    LaboratoryAnalysis laboratoryAnalysisRecovered = analysisE.get();
                    int laboratoryAnalysisIndex = appointmentRecovered.getAnalysisDone().indexOf(laboratoryAnalysisRecovered);
                    // replace element
                    appointmentRecovered.getAnalysisDone().remove(laboratoryAnalysisRecovered);
                    // update appointments in record
                    record.getAppointments().set(appointmentIndex, appointmentRecovered);
                    // update treeMap with record
                    this.patientsRecordsTreeMap.replace(patientKey, record);
                    // update store
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="Laboratory analysis doesn't exist.";
                    return false;
                }
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
        }
    }

    public boolean updatePrescription(String appointmentKey, Prescription prescription){
        this.message="Prescription successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(prescription.getPatientId());

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);

                List<Prescription> prescriptionDone = appointmentRecovered.getPrescriptions();

                Optional<Prescription> prescriptionE = prescriptionDone.stream()
                        .filter(p -> p.getPrescriptionKey().equals(prescription.getPrescriptionKey())).findFirst();

                if(prescriptionE.isPresent()){
                    Prescription prescriptionRecovered = prescriptionE.get();
                    int prescriptionIndex = appointmentRecovered.getPrescriptions().indexOf(prescriptionRecovered);
                    // replace element
                    appointmentRecovered.getPrescriptions().set(prescriptionIndex, prescription);
                    // update appointments in record
                    record.getAppointments().set(appointmentIndex, appointmentRecovered);
                    // update treeMap with record
                    this.patientsRecordsTreeMap.replace(prescription.getPatientId(), record);
                    // update store
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="Laboratory analysis doesn't exist.";
                    return false;
                }
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
        }
    }

    public boolean deletePrescription(String patientKey, String appointmentKey, String prescriptionKey){
        this.message="Prescription successfully deleted.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);

                List<Prescription> prescriptionsDone = appointmentRecovered.getPrescriptions();

                Optional<Prescription> prescriptionE = prescriptionsDone.stream()
                        .filter(p -> p.getPrescriptionKey().equals(prescriptionKey)).findFirst();

                if(prescriptionE.isPresent()){
                    Prescription prescriptionRecovered = prescriptionE.get();
                    int prescriptionIndex = appointmentRecovered.getPrescriptions().indexOf(prescriptionRecovered);
                    // replace element
                    appointmentRecovered.getPrescriptions().remove(prescriptionRecovered);
                    // update appointments in record
                    record.getAppointments().set(appointmentIndex, appointmentRecovered);
                    // update treeMap with record
                    this.patientsRecordsTreeMap.replace(patientKey, record);
                    // update store
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="Prescription doesn't exist.";
                    return false;
                }
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
        }

    }

    public boolean updateMedicalProcedure(String appointmentKey, MedicalProcedure medicalProcedure){
        this.message="Medical Procedure successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(medicalProcedure.getPatientId());

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);

                List<MedicalProcedure> proceduresDone = appointmentRecovered.getProcedures();

                Optional<MedicalProcedure> procedureE = proceduresDone.stream()
                        .filter(p -> p.getProcedureKey().equals(medicalProcedure.getProcedureKey())).findFirst();

                if(procedureE.isPresent()){
                    MedicalProcedure procedureRecovered = procedureE.get();
                    int procedureIndex = appointmentRecovered.getProcedures().indexOf(procedureRecovered);
                    // replace element
                    appointmentRecovered.getProcedures().set(procedureIndex, medicalProcedure);
                    // update appointments in record
                    record.getAppointments().set(appointmentIndex, appointmentRecovered);
                    // update treeMap with record
                    this.patientsRecordsTreeMap.replace(medicalProcedure.getPatientId(), record);
                    // update store
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="Medical procedure doesn't exist.";
                    return false;
                }
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return true;
        }
    }

    public boolean deleteMedicalProcedure(String patientKey, String appointmentKey, String medicalProcedureKey){
        this.message="Medical procedure successfully deleted.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);

                List<MedicalProcedure> proceduresDone = appointmentRecovered.getProcedures();

                Optional<MedicalProcedure> procedureE = proceduresDone.stream()
                        .filter(p -> p.getProcedureKey().equals(medicalProcedureKey)).findFirst();

                if(procedureE.isPresent()){
                    MedicalProcedure procedureRecovered = procedureE.get();
                    // replace element
                    appointmentRecovered.getProcedures().remove(procedureRecovered);
                    // update appointments in record
                    record.getAppointments().set(appointmentIndex, appointmentRecovered);
                    // update treeMap with record
                    this.patientsRecordsTreeMap.replace(patientKey, record);
                    // update store
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="Medical procedure doesn't exist.";
                    return false;
                }
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
        }
    }

    public boolean updateHospitalization(String appointmentKey, Hospitalization hospitalization){
        this.message="Hospitalization successfully updated.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(hospitalization.getPatientId());

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);

                List<Hospitalization> hospitalizationsDone = appointmentRecovered.getHospitalizations();

                Optional<Hospitalization> hospitalizationE = hospitalizationsDone.stream()
                        .filter(h -> h.getHospitalizationKey().equals(hospitalization.getHospitalizationKey())).findFirst();

                if(hospitalizationE.isPresent()){
                    Hospitalization hospitalizationRecovered = hospitalizationE.get();
                    int hospitalizationIndex = appointmentRecovered.getHospitalizations().indexOf(hospitalizationRecovered);
                    // replace element
                    appointmentRecovered.getHospitalizations().set(hospitalizationIndex, hospitalization);
                    // update appointments in record
                    record.getAppointments().set(appointmentIndex, appointmentRecovered);
                    // update treeMap with record
                    this.patientsRecordsTreeMap.replace(hospitalization.getPatientId(), record);
                    // update store
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="Hospitalization doesn't exist.";
                    return false;
                }
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
        }
    }

    public boolean deleteHospitalization(String patientKey, String appointmentKey, String hospitalizationKey){
        this.message="Hospitalization successfully deleted.";
        MedicalRecord record = this.patientsRecordsTreeMap.get(patientKey);

        if(Objects.nonNull(record)){
            Optional<Appointment> appointmentE = record.getAppointments().stream()
                    .filter(r -> r.getAppointmentKey().equals(appointmentKey)).findFirst();

            if(appointmentE.isPresent()) {
                Appointment appointmentRecovered = appointmentE.get();
                int appointmentIndex = record.getAppointments().indexOf(appointmentRecovered);

                List<Hospitalization> hospitalizationsDone = appointmentRecovered.getHospitalizations();

                Optional<Hospitalization> hospitalizationE = hospitalizationsDone.stream()
                        .filter(h -> h.getHospitalizationKey().equals(hospitalizationKey)).findFirst();

                if(hospitalizationE.isPresent()){
                    Hospitalization hospitalizationRecovered = hospitalizationE.get();
                    // replace element
                    appointmentRecovered.getHospitalizations().remove(hospitalizationRecovered);
                    // update appointments in record
                    record.getAppointments().set(appointmentIndex, appointmentRecovered);
                    // update treeMap with record
                    this.patientsRecordsTreeMap.replace(patientKey, record);
                    // update store
                    this.persister.store(this.patientsRecordsTreeMap);
                    return true;
                }else{
                    this.message="Hospitalization doesn't exist.";
                    return false;
                }
            }else{
                this.message="Appointment doesn't exist.";
                return false;
            }
        }else{
            this.message="The patient doesn't have a medical record.";
            return false;
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
