package com.xcelore.service;

import com.xcelore.entity.*;
import com.xcelore.repository.DoctorRepository;
import com.xcelore.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SuggestionService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public String suggestDoctor(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));

        Speciality speciality = getSpecialityBySymptom(patient.getSymptom());
        List<Doctor> doctors = doctorRepository.findByCityAndSpeciality(patient.getCity(), speciality);

        if (doctors.isEmpty()) {
            if (isKnownCity(patient.getCity())) {
                return "There isn’t any doctor present at your location for your symptom";
            } else {
                return "We are still waiting to expand to your location";
            }
        }

        return doctors.toString(); // Customize the response format as needed
    }

    private Speciality getSpecialityBySymptom(Symptom symptom) {
        switch (symptom) {
            case ARTHRITIS:
            case BACK_PAIN:
            case TISSUE_INJURIES:
                return Speciality.ORTHOPAEDIC;
            case DYSMENORRHEA:
                return Speciality.GYNECOLOGY;
            case SKIN_INFECTION:
            case SKIN_BURN:
                return Speciality.DERMATOLOGY;
            case EAR_PAIN:
                return Speciality.ENT;
            default:
                throw new IllegalArgumentException("Unknown symptom: " + symptom);
        }
    }

    private boolean isKnownCity(String city) {
        return Arrays.asList("Delhi", "Noida", "Faridabad").contains(city);
    }
}
