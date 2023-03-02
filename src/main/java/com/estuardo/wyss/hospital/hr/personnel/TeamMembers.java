package com.estuardo.wyss.hospital.hr.personnel;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
public enum TeamMembers {
    Nurse("nurse"),
    Doctor("doctor"),
    Attending_physician("attending physician"),
    Resident("resident"),
    Intern("intern"),
    Student("student"),
    Patient_advocate("patient advocate"),
    Technicians("technicians"),
    Physical_therapists("physical therapists"),
    Occupational_therapists("occupational therapists"),
    Speech_pathologists("speech pathologists"),
    Pharmacists("pharmacists"),
    Social_workers("Social workers"),
    Dietitians("dietitians"),
    Interpreters("interpreters");

    public final String label;

    private TeamMembers(String label) {
        this.label = label;
    }
}
