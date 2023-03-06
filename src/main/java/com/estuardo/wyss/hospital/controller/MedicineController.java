package com.estuardo.wyss.hospital.controller;


import com.estuardo.wyss.hospital.controller.responses.MedicineListResponse;
import com.estuardo.wyss.hospital.controller.responses.MedicineResponse;
import com.estuardo.wyss.hospital.process.service.MedicineService;
import com.estuardo.wyss.hospital.treatment.Medicine;
import jakarta.enterprise.context.RequestScoped;
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
@Path("/medicines")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class MedicineController {
    @Inject
    MedicineService medicineService;

    @GET
    @Path("/ping")
    public String hello() {
        return "Your are getting into wyss-hospital-personnel Cloud Applications World!";
    }

    @GET
    @Path("/{medicineKey}")
    public MedicineResponse getMedicine(
            @DefaultValue("error") @PathParam("medicineKey") String medicineKey
    ){
        String endPointApiKey="vnN53cpUUUQdvrp7JmBjskj0F";
        MedicineResponse hospitalResponse = new MedicineResponse();

        if(!medicineKey.equals("error")) {
            Medicine medicine = this.medicineService.getMedicine(medicineKey);

            hospitalResponse.setSuccess(true);
            hospitalResponse.setMedicine(medicine);
            hospitalResponse.setMessage(this.medicineService.getMessage());
            hospitalResponse.setStatus("ok");

            if (Objects.isNull(medicine)) {
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
    public MedicineListResponse getMedicines(){
        String endPointApiKey="4Bhh4ehitS9ngqZcrFq95tq5R";
        MedicineListResponse hospitalResponse = new MedicineListResponse();

        Collection<Medicine> Medicine = this.medicineService.getMedicines().values();

        hospitalResponse.setSuccess(true);
        hospitalResponse.setMedicines(new ArrayList<Medicine>());
        hospitalResponse.setMessage(this.medicineService.getMessage());
        hospitalResponse.setStatus("ok");

        hospitalResponse.setOperationStatus(true);

        return hospitalResponse;
    }
}
