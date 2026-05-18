package ch.sbb.rotationmanagement.service;

import ch.sbb.rotationmanagement.dto.AssignmentDTO;
import ch.sbb.rotationmanagement.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<AssignmentDTO> getAllAssignments() {
        return this.assignmentRepository.getAllAssignments();
    }

    public AssignmentDTO getAssignmentById(Integer id) {
        return this.assignmentRepository.getAssignmentById(id);
    }

    public AssignmentDTO getAssignmentByApprentice(Integer id) {
        return this.assignmentRepository.getAssignmentByApprentice(id);
    }

    public void createAssignment(AssignmentDTO assignmentDTO) {
        this.assignmentRepository.createAssignment(assignmentDTO);
    }

    public void deleteAssignmentById(Integer id) {
        this.assignmentRepository.deleteAssignmentById(id);
    }
}