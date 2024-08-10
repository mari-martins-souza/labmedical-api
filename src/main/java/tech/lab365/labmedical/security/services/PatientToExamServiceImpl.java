package tech.lab365.labmedical.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.lab365.labmedical.entities.Exam;
import tech.lab365.labmedical.repositories.ExamRepository;

import java.util.List;
import java.util.UUID;

    @Service
    public class PatientToExamServiceImpl implements PatientToExamService {

        @Autowired
        private ExamRepository examRepository;

        @Override
        public boolean isExamOfPatient(UUID patientId, UUID examId) {
            List<Exam> exams = examRepository.findByPatient_Id(patientId);

            for (Exam exam : exams) {
                if (exam.getExamId().equals(examId)) {
                    return true;
                }
            }

            return false;
        }
}
