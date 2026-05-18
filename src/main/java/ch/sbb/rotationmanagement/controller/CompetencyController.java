package ch.sbb.rotationmanagement.controller;

import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.service.CompetencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompetencyController {

    private final CompetencyService competencyService;

    public CompetencyController(CompetencyService competencyService) {
        this.competencyService = competencyService;
    }

    @GetMapping("${api.competency}")
    public List<CompetencyDTO> getAllCompetencies() {
        return this.competencyService.getAllCompetencies();
    }
}