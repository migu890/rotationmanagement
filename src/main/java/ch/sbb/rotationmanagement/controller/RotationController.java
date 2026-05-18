package ch.sbb.rotationmanagement.controller;

import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.dto.RotationDTO;
import ch.sbb.rotationmanagement.service.RotationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("${api.rotation}/{rotationId}")
    public RotationDTO getRotationById(
            @PathVariable("rotationId") Integer rotationId) {
        return this.rotationService.getRotationById(rotationId);
    }

    @PutMapping("${api.rotation}/{rotationid}")
    @ResponseStatus(HttpStatus.OK)
    public void updateRotationById(
            @PathVariable("rotationid") Integer id,
            @RequestBody RotationDTO rotationDTO
    ) {
        this.rotationService.updateRotationById(id, rotationDTO);
    }
}