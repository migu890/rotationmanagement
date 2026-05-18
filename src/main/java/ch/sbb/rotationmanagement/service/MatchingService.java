package ch.sbb.rotationmanagement.service;

import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.dto.MatchDTO;
import ch.sbb.rotationmanagement.dto.RotationDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MatchingService {

    private final ApprenticeService apprenticeService;
    private final CompetencyService competencyService;
    private final RotationService rotationService;

    public MatchingService(
            ApprenticeService apprenticeService,
            CompetencyService competencyService,
            RotationService rotationService
    ) {
        this.apprenticeService = apprenticeService;
        this.competencyService = competencyService;
        this.rotationService = rotationService;
    }

    public List<MatchDTO> getMatchingRotationsByApprentice(Integer apprenticeId) {

        List<CompetencyDTO> apprenticeCompetencies =
                this.apprenticeService.getApprenticeCompetencies(apprenticeId);

        List<RotationDTO> availableRotations =
                this.rotationService.getAvailableRotations();

        List<MatchDTO> matches = new ArrayList<>();

        for (RotationDTO rotationDTO : availableRotations) {

            List<CompetencyDTO> rotationCompetencies =
                    this.competencyService.getCompetenciesByRotation(rotationDTO.getId());

            double matchingScore =
                    this.calculateMatchingScore(apprenticeCompetencies, rotationCompetencies);

            MatchDTO matchDTO = new MatchDTO(
                    rotationDTO.getId(),
                    rotationDTO.getTitle(),
                    rotationDTO.getDepartment(),
                    rotationDTO.getTechnologies(),
                    matchingScore
            );

            matches.add(matchDTO);
        }

        matches.sort(Comparator.comparing(MatchDTO::getMatchingScore).reversed());

        return matches;
    }

    private double calculateMatchingScore(
            List<CompetencyDTO> apprenticeCompetencies,
            List<CompetencyDTO> rotationCompetencies
    ) {
        double matchingScore = 0.0;

        for (CompetencyDTO rotationCompetency : rotationCompetencies) {

            CompetencyDTO apprenticeCompetency =
                    this.findApprenticeCompetencyById(
                            apprenticeCompetencies,
                            rotationCompetency.getId()
                    );

            double statusFactor =
                    this.getStatusFactor(apprenticeCompetency);

            double weight =
                    rotationCompetency.getWeight() == null
                            ? 1.0
                            : rotationCompetency.getWeight();

            matchingScore += weight * statusFactor;
        }

        return matchingScore;
    }

    private CompetencyDTO findApprenticeCompetencyById(
            List<CompetencyDTO> apprenticeCompetencies,
            Integer competencyId
    ) {
        for (CompetencyDTO apprenticeCompetency : apprenticeCompetencies) {
            if (apprenticeCompetency.getId().equals(competencyId)) {
                return apprenticeCompetency;
            }
        }

        return null;
    }

    private double getStatusFactor(CompetencyDTO competencyDTO) {

        if (competencyDTO == null) {
            return 1.0;
        }

        if ("Done".equalsIgnoreCase(competencyDTO.getCompetencyState())) {
            return 0.0;
        }

        if ("In Progress".equalsIgnoreCase(competencyDTO.getCompetencyState())) {
            return 0.5;
        }

        return 1.0;
    }
}