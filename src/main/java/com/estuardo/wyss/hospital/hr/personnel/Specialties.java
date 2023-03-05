package com.estuardo.wyss.hospital.hr.personnel;

/**
 * @project payara.hackathon.jakartaEE10
 * @coder estuardo.wyss
 * @date 03/02/2023
 */
public enum Specialties {
    None("none"),
    Allergists("allergists"),
    Immunologists("immunologists"),
    Anesthesiologists("anesthesiologists"),
    Cardiologists("cardiologists"),
    Colon_and_Rectal_Surgeons("colon and rectal surgeons"),
    Critical_Care_Medicine_Specialists("critical care medicine specialists"),
    Dermatologists("dermatologists"),
    Endocrinologists("endocrinologists"),
    Emergency_Medicine_Specialists("emergency medicine specialists"),
    Family_physicians("family physicians"),
    Gastroenterologists("gastroenterologists"),
    Geriatric_MedicineSpecialists("geriatric medicine specialists"),
    Hematologists("hematologists"),
    Hospice_and_Palliative_Medicine_Specialists("hospice and palliative medicine specialists"),
    Infectious_Disease_Specialists("infectious disease specialists"),
    Internists("internists"),
    Medical_Geneticists("medical geneticists"),
    Nephrologists("nephrologists"),
    Neurologists("neurologists"),
    Obstetricians_and_Gynecologists("obstetricians and Gynecologists"),
    Oncologists("oncologists"),
    Ophthalmologists("ophthalmologists"),
    Osteopaths("Osteopaths"),
    Otolaryngologists("otolaryngologists"),
    Pathologists("pathologists"),
    Pediatricians("pediatricians"),
    Physiatrists("physiatrists"),
    Plastic_Surgeons("plastic surgeons"),
    Podiatrists("podiatrists"),
    Preventive_Medicine_Specialists("Preventive Medicine Specialists"),
    Psychiatrists("psychiatrists"),
    Pulmonologists("pulmonologists"),
    Radiologists("radiologists"),
    Rheumatologists("Rheumatologists"),
    Sleep_Medicine_Specialists("sleep medicine specialists"),
    Sports_Medicine_Specialists("sports medicine specialists"),
    General_Surgeons("general surgeons"),
    Urologists("urologists");

    public final String label;

    Specialties(String label) {
        this.label = label;
    }
}
