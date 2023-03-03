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

package com.estuardo.wyss.hospital.patient.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
@Getter
@Setter
public class Patient {
    private String fiscalId;
    private String firstName;
    private String lastName;
    private String personalId;
    private String smartPhone;
    private String homePhone;
    private String officeHome;
    private String email;
    private String bloodType;
    private String allergicTo;
    private String inEmergencyCallTo;
    private String inEmergencySmartPhone;
    private String inEmergencyHomePhone;
    private String inEmergencyOfficeHome;
    private String inEmergencyEmail;
}
