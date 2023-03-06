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

import com.estuardo.wyss.hospital.treatment.Medicine;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import one.microstream.integrations.cdi.types.Storage;
import one.microstream.persistence.types.Persister;

import java.util.Collections;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * In this class I am creating the object Medicine to be able to create
 * the prescription and billing
 * The inventory control should be implemented in other class
 */

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Storage

public class MedicineService {
    @Inject
    private transient Persister persister;

    private final TreeMap<String, Medicine> medicinesTreeMap = new TreeMap<>();

    @Getter
    private String message="";

    public boolean addMedicine(Medicine medicine){
        this.message="Medicine successfully added.";
        if(!this.medicinesTreeMap.containsKey(medicine.getMedicineKey())){
            this.medicinesTreeMap.put(medicine.getMedicineKey(), medicine);
            this.persister.store(this.medicinesTreeMap);
            return true;
        }else{
            this.message="Medicine already exists in database.";
            return false;
        }
    }

    public Medicine getMedicine(String medicineKey){
        Medicine medicine = this.medicinesTreeMap.get(medicineKey);
        this.message="Medicine successfully retrieved.";

        if(Objects.isNull(medicine)){
            this.message="Medicine does not exists in database.";
        }

        return medicine;
    }

    public SortedMap<String, Medicine> getMedicines(){
        return Collections.unmodifiableSortedMap(this.medicinesTreeMap);
    }

    public boolean updateMedicine(Medicine medicine){
        this.message="Medicine successfully updated.";
        Medicine medicineR = this.medicinesTreeMap.put(medicine.getMedicineKey(), medicine);

        if(Objects.isNull(medicineR)){
            this.message="Medicine you intend to update, does not exists in database.";
            return false;
        }else{
            this.persister.store(this.medicinesTreeMap);
            return true;
        }
    }

    public boolean deleteMedicine(String medicineKey){
        this.message="Medicine successfully deleted.";
        Medicine medicine = this.medicinesTreeMap.remove(medicineKey);

        if(Objects.isNull(medicine)){
            this.message="Patient you intend to delete, does not exists in database.";
            return false;
        }else{
            this.persister.store(this.medicinesTreeMap);
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

        final MedicineService service  = (MedicineService)o;
        return Objects.equals(this.medicinesTreeMap, service.medicinesTreeMap);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.medicinesTreeMap);
    }
}
