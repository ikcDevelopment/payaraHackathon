package com.estuardo.wyss.hospital.controller;


import com.estuardo.wyss.hospital.controller.responses.*;
import com.estuardo.wyss.hospital.hr.personnel.entities.Personnel;
import com.estuardo.wyss.hospital.process.service.PersonnelService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/03/2023
 */
@Path("/personnel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
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
        String endPointApiKey="bsNH0eeTmZiy1IhuaonZKq3P#";
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
        String endPointApiKey="zbnrcvemlrYo9sp8B2j1bP9Lf";
        EmployeesResponse hospitalResponse = new EmployeesResponse();

        Collection<Personnel> employees = this.personnelService.getEmployees().values();

        hospitalResponse.setSuccess(true);
        hospitalResponse.setEmployees(new ArrayList<Personnel>(employees));
        hospitalResponse.setMessage(this.personnelService.getMessage());
        hospitalResponse.setStatus("ok");

        hospitalResponse.setOperationStatus(true);

        return hospitalResponse;
    }

    @GET
    @Path("/all-tree-ui")
    public DataTreeUI getEmployeesTreeUi(){
        String endPointApiKey="zopipfduemlrYo9sp8B2j1bP9Lf";
        DataTreeUI hospitalResponse = new DataTreeUI();
        List<DataTreeNodeUI> nodes = new ArrayList<>();

        Collection<Personnel> employees = this.personnelService.getEmployees().values();

        List<Personnel> data = new ArrayList<Personnel>(employees);

        data.forEach(d->{
            DataTreeNodeUI dtnui = new DataTreeNodeUI();
            dtnui.setId(d.getFiscalId());
            dtnui.setName(d.getFirstName()+ ' '+d.getLastName());
            nodes.add(dtnui);
        });

        hospitalResponse.setSuccess(true);
        hospitalResponse.setNodes(nodes);
        hospitalResponse.setMessage(this.personnelService.getMessage());
        hospitalResponse.setStatus("ok");

        hospitalResponse.setOperationStatus(true);

        return hospitalResponse;
    }

    @POST
    @Path("/new")
    public StandardResponse insertEmployee(
            Personnel employee
    ){
        String endPointApiKey="sDx6bxzWnanXKsT31Zsobnc8#";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.personnelService.addEmployee(employee));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.personnelService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @PUT
    @Path("/update")
    public StandardResponse updateEmployee(
            Personnel employee
    ){
        String endPointApiKey="hJi1E5cc5sdndlwKKy0Y2ah3V";
        StandardResponse hospitalResponse = new StandardResponse();

        hospitalResponse.setOperationStatus(this.personnelService.updateEmployee(employee));
        hospitalResponse.setStatus("ok");
        hospitalResponse.setMessage(this.personnelService.getMessage());
        hospitalResponse.setSuccess(true);

        return hospitalResponse;
    }

    @DELETE
    @Path("/{fiscalId}/del")
    public StandardResponse deleteEmployee(
            @DefaultValue("error") @PathParam("fiscalId") String fiscalId
    ){
        String endPointApiKey="s2Pm2@pXEkd4ZSAmhvYr9Im7V";
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
