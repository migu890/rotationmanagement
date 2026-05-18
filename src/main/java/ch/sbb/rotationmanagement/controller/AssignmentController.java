package ch.sbb.rotationmanagement.controller;

import ch.sbb.rotationmanagement.dto.AssignmentDTO;
import ch.sbb.rotationmanagement.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("${api.assignment}")
    public List<AssignmentDTO> getAllAssignments() {
        return this.assignmentService.getAllAssignments();
    }

    @GetMapping("${api.assignment}/{id}")
    public AssignmentDTO getAssignmentById(@PathVariable Long id) {
        return this.assignmentService.getAssignmentById(id);
    }

    @PostMapping("${api.assignment}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        this.assignmentService.createAssignment(assignmentDTO);
    }

    @DeleteMapping("${api.assignment}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssignmentById(@PathVariable Long id) {
        this.assignmentService.deleteAssignmentById(id);
    }
}