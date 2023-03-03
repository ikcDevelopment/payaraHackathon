package com.estuardo.wyss.hospital.process.service;

import com.estuardo.wyss.hospital.patient.entities.LaboratoryAnalysis;
import com.estuardo.wyss.hospital.treatment.Analysis;

import java.util.ArrayList;
import java.util.List;

/**
 * @company kappa.computacion
 * @coder estuardo.wyss
 * @date
 */
public class MainTraining {
    public static void  main(String args[]){
        List<LaboratoryAnalysis> analysisDone = new ArrayList<>();
        LaboratoryAnalysis a1 = new LaboratoryAnalysis();
        a1.setAnalysis(new Analysis());
        a1.setDoctorId("25");
        a1.setLaboratoryKey("4445");
        a1.setPatientId("wyss");
        analysisDone.add(a1);
        LaboratoryAnalysis a2 = new LaboratoryAnalysis();
        a2.setAnalysis(new Analysis());
        a2.setDoctorId("25");
        a2.setLaboratoryKey("4446");
        a2.setPatientId("wyss");
        analysisDone.add(a2);
        LaboratoryAnalysis a3 = new LaboratoryAnalysis();
        a3.setAnalysis(new Analysis());
        a3.setDoctorId("25");
        a3.setLaboratoryKey("4447");
        a3.setPatientId("wyss");
        analysisDone.add(a3);

        LaboratoryAnalysis a4 = new LaboratoryAnalysis();
        a3.setAnalysis(new Analysis());
        a3.setDoctorId("285");
        a3.setLaboratoryKey("477");
        a3.setPatientId("rosito");
        analysisDone.add(a4);

        LaboratoryAnalysis a5 = new LaboratoryAnalysis();
        a3.setAnalysis(new Analysis());
        a3.setDoctorId("2455");
        a3.setLaboratoryKey("6777");
        a3.setPatientId("telo");

        analysisDone.removeIf(p->p.getLaboratoryKey().equals("dkjfj"));

        int indice = analysisDone.indexOf(a5);
        System.out.println(indice);
    }
}
