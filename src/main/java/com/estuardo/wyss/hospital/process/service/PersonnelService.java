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

import com.estuardo.wyss.hospital.hr.personnel.entities.Personnel;
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
public class PersonnelService {

    private final TreeMap<String, Personnel> personnelTreeMap = new TreeMap<>();

    @Getter
    private String message="";

    public boolean addEmployee(Personnel employee){
        this.message="Employee successfully added.";
        if(!this.personnelTreeMap.containsKey(employee.getFiscalId())){
            this.personnelTreeMap.put(employee.getFiscalId(), employee);
            //this.persister.store(this.personnelTreeMap);
            return true;
        }else{
            this.message="Employee already exists in database.";
            return false;
        }
    }

    public Personnel getEmployee(String fiscalId){
        Personnel employee = this.personnelTreeMap.get(fiscalId);
        this.message="Employee successfully retrieved.";

        if(Objects.isNull(employee)){
            this.message="Employee does not exists in database.";
        }

        return employee;
    }

    public SortedMap<String, Personnel> getEmployees(){
        return Collections.unmodifiableSortedMap(this.personnelTreeMap);
    }

    public boolean updateEmployee(Personnel employee){
        this.message="Employee successfully updated.";
        Personnel employeeR = this.personnelTreeMap.put(employee.getFiscalId(), employee);

        if(Objects.isNull(employeeR)){
            this.message="Employee you intend to update, does not exists in database.";
            return false;
        }else{
            //this.persister.store(this.personnelTreeMap);
            return true;
        }
    }

    public boolean deleteEmployee(String fiscalId){
        this.message="Employee successfully deleted.";
        Personnel employee = this.personnelTreeMap.remove(fiscalId);

        if(Objects.isNull(employee)){
            this.message="Employee you intend to delete, does not exists in database.";
            return false;
        }else{
            //this.persister.store(this.personnelTreeMap);
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

        final PersonnelService service  = (PersonnelService)o;
        return Objects.equals(this.personnelTreeMap, service.personnelTreeMap);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.personnelTreeMap);
    }
}
