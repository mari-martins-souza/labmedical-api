package tech.lab365.labmedical.services;

import org.springframework.stereotype.Service;
import tech.lab365.labmedical.dtos.DashboardStatsDTO;
import tech.lab365.labmedical.repositories.AppointmentRepository;
import tech.lab365.labmedical.repositories.ExamRepository;
import tech.lab365.labmedical.repositories.PatientRepository;

@Service
public class DashboardService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final ExamRepository examRepository;

    public DashboardService(PatientRepository patientRepository, AppointmentRepository appointmentRepository, ExamRepository examRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.examRepository = examRepository;
    }

    public DashboardStatsDTO getStats() {
        DashboardStatsDTO stats = new DashboardStatsDTO();

        stats.setTotalPatients(patientRepository.count());
        stats.setTotalAppointments(appointmentRepository.count());
        stats.setTotalExams(examRepository.count());
        return stats;
    }
}
