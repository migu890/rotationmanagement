package ch.sbb.rotationmanagement.controller;

import ch.sbb.rotationmanagement.dto.RotationDTO;
import ch.sbb.rotationmanagement.service.RotationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RotationController {

    private final RotationService rotationService;

    public RotationController(RotationService rotationService) {
        this.rotationService = rotationService;
    }

    @GetMapping("${api.rotation}")
    public List<RotationDTO> getAllRotations() {
        return this.rotationService.getAllRotations();
    }

    @GetMapping("${api.rotation}/available")
    public List<RotationDTO> getAvailableRotations() {
        return this.rotationService.getAvailableRotations();
    }
}