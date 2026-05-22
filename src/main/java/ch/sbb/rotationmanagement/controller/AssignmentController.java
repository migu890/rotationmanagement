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
    public AssignmentDTO getAssignmentById(@PathVariable("id") Integer id) {
        return this.assignmentService.getAssignmentById(id);
    }

    @GetMapping("${api.assignment}/apprentice/{id}")
    public AssignmentDTO getAssignmentByApprentice(@PathVariable("id") Integer id) {
        return this.assignmentService.getAssignmentByApprentice(id);
    }

    @PostMapping("${api.assignment}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
        this.assignmentService.createAssignment(assignmentDTO);
    }

    @DeleteMapping("${api.assignment}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssignmentById(@PathVariable("id") Integer id) {
        this.assignmentService.deleteAssignmentById(id);
    }

    @GetMapping("${api.assignment}/apprentice/{id}")
    public List<AssignmentDTO> getAllAssignmentsByApprentice(@PathVariable("id") Integer id) {
        return this.assignmentService.getAllAssignmentsByApprentice(id);
    }
    
}