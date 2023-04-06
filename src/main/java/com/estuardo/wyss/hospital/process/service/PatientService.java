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

import com.estuardo.wyss.hospital.patient.entities.Patient;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;

import java.util.Collections;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */

@ApplicationScoped
public class PatientService {

    private final TreeMap<String, Patient> patientsTreeMap = new TreeMap<>();

    @Getter
    private String message="";

    public boolean addPatient(Patient patient){
        this.message="Patient successfully added.";
        if(!this.patientsTreeMap.containsKey(patient.getFiscalId())){
            this.patientsTreeMap.put(patient.getFiscalId(), patient);
            //this.persister.store(this.patientsTreeMap);
            return true;
        }else{
            this.message="Patient already exists in database.";
            return false;
        }
    }

    public Patient getPatient(String fiscalId){
        Patient patient = this.patientsTreeMap.get(fiscalId);
        this.message="Patient successfully retrieved.";

        if(Objects.isNull(patient)){
            this.message="Patient does not exists in database.";
        }

        return patient;
    }

    public SortedMap<String, Patient> getPatients(){
        return Collections.unmodifiableSortedMap(this.patientsTreeMap);
    }

    public boolean updatePatient(Patient patient){
        this.message="Patient successfully updated.";
        Patient patientR = this.patientsTreeMap.put(patient.getFiscalId(), patient);

        if(Objects.isNull(patientR)){
            this.message="Patient you intend to update, does not exists in database.";
            return false;
        }else{
            //this.persister.store(this.patientsTreeMap);
            return true;
        }
    }

    public boolean deletePatient(String fiscalId){
        this.message="Patient successfully deleted.";
        Patient patient = this.patientsTreeMap.remove(fiscalId);

        if(Objects.isNull(patient)){
            this.message="Patient you intend to delete, does not exists in database.";
            return false;
        }else{
            //this.persister.store(this.patientsTreeMap);
            return true;
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

        final PatientService service  = (PatientService)o;
        return Objects.equals(this.patientsTreeMap, service.patientsTreeMap);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.patientsTreeMap);
    }

}
