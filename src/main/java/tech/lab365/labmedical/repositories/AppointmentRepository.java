package tech.lab365.labmedical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lab365.labmedical.entities.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatient_Id(Long id);
}
