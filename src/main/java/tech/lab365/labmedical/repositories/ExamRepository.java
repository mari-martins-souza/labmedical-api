package tech.lab365.labmedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.lab365.labmedical.entities.Exam;

import java.util.List;
import java.util.UUID;

public interface ExamRepository extends JpaRepository<Exam, UUID> {

    List<Exam> findByPatient_Id(UUID id);

    boolean existsByPatient_Id(UUID id);
}
