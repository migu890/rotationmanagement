package ch.sbb.rotationmanagement.service;

import ch.sbb.rotationmanagement.dto.ApprenticeDTO;
import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.repository.ApprenticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;

    public ApprenticeService(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    public List<ApprenticeDTO> getAllApprentices() {
        return this.apprenticeRepository.getAllApprentices();
    }

    public ApprenticeDTO getApprenticeById(Long id) {
        return this.apprenticeRepository.getApprenticeById(id);
    }

    public void createApprentice(ApprenticeDTO apprenticeDTO) {
        this.apprenticeRepository.createApprentice(apprenticeDTO);
    }

    public void updateApprentice(ApprenticeDTO apprenticeDTO) {
        this.apprenticeRepository.updateApprentice(apprenticeDTO);
    }

    public void deleteApprenticeById(Long id) {
        this.apprenticeRepository.deleteApprenticeById(id);
    }

    public List<CompetencyDTO> getApprenticeCompetencies(Long id) {
        return this.apprenticeRepository.getApprenticeCompetencies(id);
    }

    public void updateCompetenciesByApprentice(Long id, List<CompetencyDTO> competencyDTOs) {
        this.apprenticeRepository.updateCompetenciesByApprentice(id, competencyDTOs);
    }
}