package tech.lab365.labmedical.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import tech.lab365.labmedical.dtos.ExamRequestDTO;
import tech.lab365.labmedical.dtos.ExamResponseDTO;
import tech.lab365.labmedical.services.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<ExamResponseDTO> registerExam(@Valid @RequestBody ExamRequestDTO examRequestDTO) {
        ExamResponseDTO examResponseDTO = examService.registerExam(examRequestDTO);
        return ResponseEntity.ok(examResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponseDTO> getExam(@PathVariable Long id) {
        ExamResponseDTO exam = examService.getExam(id);
        return ResponseEntity.ok(exam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResponseDTO> updateExam(@PathVariable Long id, @Valid @RequestBody ExamRequestDTO examRequestDTO) throws BadRequestException {
        ExamResponseDTO updateExam = examService.updateExam(id, examRequestDTO);
        return ResponseEntity.ok(updateExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

}
