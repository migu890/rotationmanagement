package ch.sbb.rotationmanagement.service;

import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.repository.CompetencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetencyService {

    private final CompetencyRepository competencyRepository;

    public CompetencyService(CompetencyRepository competencyRepository) {
        this.competencyRepository = competencyRepository;
    }

    public List<CompetencyDTO> getAllCompetencies() {
        return this.competencyRepository.getAllCompetencies();
    }
}