package tech.lab365.labmedical.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.lab365.labmedical.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE (:name is null or LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) and (:patient_id is null or p.patient_id = :patient_id)")
    Page<Patient> findByNameAndPatientId(@Param("name") String name, @Param("patient_id") Long patient_id, Pageable pageable);


}








