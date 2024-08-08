package tech.lab365.labmedical.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.lab365.labmedical.entities.Appointment;
import tech.lab365.labmedical.repositories.AppointmentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PatientToAppointmentServiceImpl implements PatientToAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public boolean isAppointmentOfPatient(UUID patientId, UUID appointmentId) {
        List<Appointment> appointments = appointmentRepository.findByPatient_Id(patientId);

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return true;
            }
        }

        return false;
    }
}
