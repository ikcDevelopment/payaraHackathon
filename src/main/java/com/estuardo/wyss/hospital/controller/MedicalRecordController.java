package com.estuardo.wyss.hospital.controller;

import com.estuardo.wyss.hospital.controller.responses.StandardResponse;
import com.estuardo.wyss.hospital.patient.entities.LaboratoryAnalysis;
import com.estuardo.wyss.hospital.patient.entities.MedicalRecord;
import com.estuardo.wyss.hospital.process.service.MedicalRecordService;
import com.estuardo.wyss.hospital.treatment.Appointment;
import com.estuardo.wyss.hospital.treatment.Hospitalization;
import com.estuardo.wyss.hospital.treatment.MedicalProcedure;
import com.estuardo.wyss.hospital.treatment.Prescription;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
@Path("/medical-record")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicalRecordController {
    @Inject
    MedicalRecordService medicalRecordService;

    @GET
    @Path("/ping")
    public String hello() {
        return "Your are getting into wyss-hospital-personnel Cloud Applications World!";
    }

    @POST
    @Path("/patient-record")
    public StandardResponse addMedicalRecord(MedicalRecord record){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.medicalRecordService.addMedicalRecord(record));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.medicalRecordService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @POST
    @Path("/appointment")
    public StandardResponse addAppointment(Appointment appointment){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.medicalRecordService.addAppointment(appointment));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.medicalRecordService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @POST
    @Path("/lab")
    public StandardResponse addLaboratoryAnalysis(
            String patientId,
            String appointmentKey,
            LaboratoryAnalysis lab){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.medicalRecordService.addLaboratoryAnalysis(patientId, appointmentKey, lab));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.medicalRecordService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @POST
    @Path("/prescription")
    public StandardResponse addPrescription(
            String patientId,
            String appointmentKey,
            Prescription medicine){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.medicalRecordService.addPrescription(patientId, appointmentKey, medicine));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.medicalRecordService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @POST
    @Path("/medical-procedure")
    public StandardResponse addMedicalProcedure(
            String patientId,
            String appointmentKey,
            MedicalProcedure procedure){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.medicalRecordService.addMedicalProcedure(patientId, appointmentKey, procedure));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.medicalRecordService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @POST
    @Path("/hospitalization")
    public StandardResponse addHospitalization(
            String patientId,
            String appointmentKey,
            Hospitalization hospitalization){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.medicalRecordService.addHospitalization(patientId, appointmentKey, hospitalization));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.medicalRecordService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @PUT
    @Path("/patient-record/{recordKey}")
    public StandardResponse updateMedicalRecord(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            MedicalRecord medicalRecord){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error")) {
            if(recordKey.equals(medicalRecord.getRecordKey())) {
                hospitalResponse.setOperationStatus(this.medicalRecordService.updateMedicalRecord(medicalRecord));
                hospitalResponse.setStatus("ok");
                hospitalResponse.setMessage(this.medicalRecordService.getMessage());
                hospitalResponse.setSuccess(true);
            }else{
                hospitalResponse.setOperationStatus(false);
                hospitalResponse.setMessage("Invalid parameters!!");
                hospitalResponse.setSuccess(false);
                hospitalResponse.setStatus("err");
            }
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @DELETE
    @Path("/patient-record/{recordKey}/patient/{patientKey}")
    public StandardResponse deleteMedicalRecord(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("patientKey")  String patientKey){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !patientKey.equals("error")) {
                hospitalResponse.setOperationStatus(this.medicalRecordService.deleteMedicalRecord(patientKey));
                hospitalResponse.setStatus("ok");
                hospitalResponse.setMessage(this.medicalRecordService.getMessage());
                hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @PUT
    @Path("/patient-record/{recordKey}/appointment/{appointmentKey}/lab")
    public StandardResponse updateLaboratoryAnalysis(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("appointmentKey") String appointmentKey,
            LaboratoryAnalysis lab){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !appointmentKey.equals("error")) {
            hospitalResponse.setOperationStatus(this.medicalRecordService.updateLaboratoryAnalysis(appointmentKey, lab));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.medicalRecordService.getMessage());
            hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @DELETE
    @Path("/patient-record/{recordKey}/appointment/{appointmentKey}/lab/{labKey}")
    public StandardResponse deleteLaboratoryAnalysis(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("appointmentKey") String appointmentKey,
            @DefaultValue("**error**") @PathParam("labKey") String labKey,
            @DefaultValue("**error**") @QueryParam("patientKey") String patientKey
            ){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !appointmentKey.equals("error") && !labKey.equals("error")) {
            hospitalResponse.setOperationStatus(this.medicalRecordService.deleteLaboratoryAnalysis(patientKey, appointmentKey, labKey));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.medicalRecordService.getMessage());
            hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @PUT
    @Path("/patient-record/{recordKey}/appointment/{appointmentKey}/prescription")
    public StandardResponse updatePrescription(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("appointmentKey") String appointmentKey,
            Prescription prescription){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !appointmentKey.equals("error") ) {
            hospitalResponse.setOperationStatus(this.medicalRecordService.updatePrescription(appointmentKey, prescription));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.medicalRecordService.getMessage());
            hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @DELETE
    @Path("/patient-record/{recordKey}/appointment/{appointmentKey}/prescription/{prescriptionKey}")
    public StandardResponse deletePrescription(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("appointmentKey") String appointmentKey,
            @DefaultValue("**error**") @PathParam("prescriptionKey") String prescriptionKey,
            @DefaultValue("**error**") @QueryParam("patientKey") String patientKey
            ){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !appointmentKey.equals("error") && !prescriptionKey.equals("error")) {
            hospitalResponse.setOperationStatus(this.medicalRecordService.deletePrescription(patientKey, appointmentKey, prescriptionKey));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.medicalRecordService.getMessage());
            hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @PUT
    @Path("/patient-record/{recordKey}/appointment/{appointmentKey}/medical-procedure")
    public StandardResponse updateMedicalProcedure(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("appointmentKey") String appointmentKey,
            MedicalProcedure medicalProcedure){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !appointmentKey.equals("error") ) {
            hospitalResponse.setOperationStatus(this.medicalRecordService.updateMedicalProcedure(appointmentKey, medicalProcedure));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.medicalRecordService.getMessage());
            hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @DELETE
    @Path("/patient-record/{recordKey}/appointment/{appointmentKey}/medical-procedure/{medicalProcedureKey}")
    public StandardResponse deleteMedicalProcedure(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("appointmentKey") String appointmentKey,
            @DefaultValue("**error**") @PathParam("medicalProcedureKey") String medicalProcedureKey,
            @DefaultValue("**error**") @QueryParam("patientKey") String patientKey
            ){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !appointmentKey.equals("error") && !medicalProcedureKey.equals("error")) {
            hospitalResponse.setOperationStatus(this.medicalRecordService.deleteMedicalProcedure(patientKey, appointmentKey, medicalProcedureKey));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.medicalRecordService.getMessage());
            hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @PUT
    @Path("/patient-record/{recordKey}/appointment/{appointmentKey}/hospitalization")
    public StandardResponse updateHospitalization(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("appointmentKey") String appointmentKey,
            Hospitalization hospitalization){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !appointmentKey.equals("error") ) {
            hospitalResponse.setOperationStatus(this.medicalRecordService.updateHospitalization(appointmentKey, hospitalization));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.medicalRecordService.getMessage());
            hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @DELETE
    @Path("/patient-record/{recordKey}/appointment/{appointmentKey}/hospitalization/{hospitalizationKey}")
    public StandardResponse deleteHospitalization(
            @DefaultValue("**error**") @PathParam("recordKey") String recordKey,
            @DefaultValue("**error**") @PathParam("appointmentKey") String appointmentKey,
            @DefaultValue("**error**") @PathParam("hospitalizationKey") String hospitalizationKey,
            @DefaultValue("**error**") @QueryParam("patientKey") String patientKey){
        String endPointApiKey="";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!recordKey.equals("error") && !appointmentKey.equals("error") && !hospitalizationKey.equals("error")) {
            hospitalResponse.setOperationStatus(this.medicalRecordService.deleteHospitalization(patientKey, appointmentKey, hospitalizationKey));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.medicalRecordService.getMessage());
            hospitalResponse.setSuccess(true);
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }




}
