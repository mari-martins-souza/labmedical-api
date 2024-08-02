package tech.lab365.labmedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.lab365.labmedical.entities.Exam;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByPatient_Id(Long id);
}
