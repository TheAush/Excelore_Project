package com.xcelore.repository;

import com.xcelore.entity.Doctor;
import com.xcelore.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByCityAndSpeciality(String city, Speciality speciality);
}
