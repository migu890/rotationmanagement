package ch.sbb.rotationmanagement.controller;

import ch.sbb.rotationmanagement.dto.ApprenticeDTO;
import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.service.ApprenticeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApprenticeController {

    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @GetMapping("${api.apprentice}")
    public List<ApprenticeDTO> getAllApprentices() {
        return this.apprenticeService.getAllApprentices();
    }

    @GetMapping("${api.apprentice}/{id}")
    public ApprenticeDTO getApprenticeById(@PathVariable Long id) {
        return this.apprenticeService.getApprenticeById(id);
    }

    @PostMapping("${api.apprentice}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createApprentice(@RequestBody ApprenticeDTO apprenticeDTO) {
        this.apprenticeService.createApprentice(apprenticeDTO);
    }

    @PutMapping("${api.apprentice}")
    @ResponseStatus(HttpStatus.OK)
    public void updateApprentice(@RequestBody ApprenticeDTO apprenticeDTO) {
        this.apprenticeService.updateApprentice(apprenticeDTO);
    }

    @DeleteMapping("${api.apprentice}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteApprenticeById(@PathVariable Long id) {
        this.apprenticeService.deleteApprenticeById(id);
    }

    @GetMapping("${api.apprentice}/{id}/competencies")
    public List<CompetencyDTO> getApprenticeCompetencies(@PathVariable Long id) {
        return this.apprenticeService.getApprenticeCompetencies(id);
    }

    @PutMapping("${api.apprentice}/{id}/competencies")
    @ResponseStatus(HttpStatus.OK)
    public void updateCompetenciesByApprentice(
            @PathVariable Long id,
            @RequestBody List<CompetencyDTO> competencyDTOs
    ) {
        this.apprenticeService.updateCompetenciesByApprentice(id, competencyDTOs);
    }
}