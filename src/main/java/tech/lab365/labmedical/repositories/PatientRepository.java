package tech.lab365.labmedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lab365.labmedical.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}






