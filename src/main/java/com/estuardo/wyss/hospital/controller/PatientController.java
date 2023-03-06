package com.estuardo.wyss.hospital.controller;


import com.estuardo.wyss.hospital.controller.responses.PatientListResponse;
import com.estuardo.wyss.hospital.controller.responses.PatientResponse;
import com.estuardo.wyss.hospital.controller.responses.StandardResponse;
import com.estuardo.wyss.hospital.patient.entities.Patient;
import com.estuardo.wyss.hospital.process.service.PatientService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Path("/patients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class PatientController {
    @Inject
    PatientService patientService;

    @GET
    @Path("/ping")
    public String hello() {
        return "Your are getting into wyss-hospital-patients Cloud Applications World!";
    }

    @GET
    @Path("/{fiscalId}")
    public PatientResponse getPatient(
            @DefaultValue("error") @PathParam("fiscalId") String fiscalId
    ){
        String endPointApiKey="36Re8Ncim*OAKuSxoIj67eVK5";
        PatientResponse hospitalResponse = new PatientResponse();

        if(!fiscalId.equals("error")) {
            Patient patient = this.patientService.getPatient(fiscalId);

            hospitalResponse.setSuccess(true);
            hospitalResponse.setPatient(patient);
            hospitalResponse.setMessage(this.patientService.getMessage());

            if (Objects.isNull(patient)) {
                hospitalResponse.setStatus("err");
            } else {
                hospitalResponse.setStatus("ok");
            }
        }else{
            hospitalResponse.setOperationStatus(false);
            hospitalResponse.setMessage("Unauthorized access!!");
            hospitalResponse.setSuccess(false);
            hospitalResponse.setStatus("away");
        }

        return hospitalResponse;
    }

    @GET
    @Path("/all")
    public PatientListResponse getPatients(){
        String endPointApiKey="0xqZOhzGE1wdl5#t8rMW1E0gM";
        PatientListResponse hospitalResponse = new PatientListResponse();

        Collection<Patient> patients = this.patientService.getPatients().values();

        hospitalResponse.setSuccess(true);
        hospitalResponse.setPatients(new ArrayList<Patient>(patients));
        hospitalResponse.setMessage(this.patientService.getMessage());

        hospitalResponse.setStatus("ok");

        return hospitalResponse;
    }

    @POST
    @Path("/new")
    public StandardResponse insertPatient(
        Patient patient
    ){
        String endPointApiKey="g7r4qz07Pdb7EsO0j4wb848Di";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.patientService.addPatient(patient));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.patientService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @PUT
    @Path("/update")
    public StandardResponse updatePatient(
            Patient patient
    ){
        String endPointApiKey="jn00GAZxhfYV9$U019LIUqH@b";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.patientService.updatePatient(patient));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.patientService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @DELETE
    @Path("/{fiscalId}/del")
    public StandardResponse deletePatient(
            @DefaultValue("error") @PathParam("fiscalId") String fiscalId
    ){
        String endPointApiKey="9keh6ywp9ulfptfw4mJhr4*Ga";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!fiscalId.equals("error")) {
            hospitalResponse.setOperationStatus(this.patientService.deletePatient(fiscalId));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.patientService.getMessage());
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
