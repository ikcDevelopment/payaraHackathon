package com.estuardo.wyss.hospital.controller;


import com.estuardo.wyss.hospital.controller.responses.EmployeeResponse;
import com.estuardo.wyss.hospital.controller.responses.EmployeesResponse;
import com.estuardo.wyss.hospital.controller.responses.StandardResponse;
import com.estuardo.wyss.hospital.hr.personnel.entities.Personnel;
import com.estuardo.wyss.hospital.process.service.PersonnelService;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/03/2023
 */
@Path("/personnel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonnelController {
    @Inject
    PersonnelService personnelService;

    @GET
    @Path("/ping")
    public String hello() {
        return "Your are getting into wyss-hospital-personnel Cloud Applications World!";
    }

    @GET
    @Path("/{fiscalId}")
    public EmployeeResponse getEmployee(
            @DefaultValue("error") @PathParam("fiscalId") String fiscalId
    ){
        String endPointApiKey="xyz45kjfdiou@io#978";
        EmployeeResponse hospitalResponse = new EmployeeResponse();

        if(!fiscalId.equals("error")) {
            Personnel employee = this.personnelService.getEmployee(fiscalId);

            hospitalResponse.setSuccess(true);
            hospitalResponse.setEmployee(employee);
            hospitalResponse.setMessage(this.personnelService.getMessage());
            hospitalResponse.setStatus("ok");

            if (Objects.isNull(employee)) {
                hospitalResponse.setOperationStatus(false);
            } else {
                hospitalResponse.setOperationStatus(true);
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
    public EmployeesResponse getEmployees(){
        String endPointApiKey="xyz45kjfdi*0ou@io#978";
        EmployeesResponse hospitalResponse = new EmployeesResponse();

        Collection<Personnel> employees = this.personnelService.getEmployees().values();

        hospitalResponse.setSuccess(true);
        hospitalResponse.setEmployees(new ArrayList<Personnel>(employees));
        hospitalResponse.setMessage(this.personnelService.getMessage());
        hospitalResponse.setStatus("ok");

        hospitalResponse.setOperationStatus(true);

        return hospitalResponse;
    }

    @POST
    @Path("")
    public StandardResponse insertEmployee(
            Personnel employee
    ){
        String endPointApiKey="xy3565kjfdiou@io#978";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.personnelService.addEmployee(employee));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.personnelService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @PUT
    @Path("")
    public StandardResponse updateEmployee(
            Personnel employee
    ){
        String endPointApiKey="xy3565kjfopdifdsou@io@978";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.personnelService.updateEmployee(employee));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.personnelService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @DELETE
    @Path("/{fiscalId}")
    public StandardResponse deletePatient(
            @DefaultValue("error") @PathParam("fiscalId") String fiscalId
    ){
        String endPointApiKey="xy35659354kjfopdifdsou@io@978";
        StandardResponse hospitalResponse = new StandardResponse();

        if(!fiscalId.equals("error")) {
            hospitalResponse.setOperationStatus(this.personnelService.deleteEmployee(fiscalId));
            hospitalResponse.setStatus("ok");
            hospitalResponse.setMessage(this.personnelService.getMessage());
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
