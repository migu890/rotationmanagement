package ch.sbb.rotationmanagement.service;

import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.dto.MatchDTO;
import ch.sbb.rotationmanagement.dto.RotationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchingServiceTest {

    @Mock
    private ApprenticeService apprenticeService;

    @Mock
    private CompetencyService competencyService;

    @Mock
    private RotationService rotationService;

    @InjectMocks
    private MatchingService matchingService;

    @Test
    void shouldReturnScoreZeroWhenCompetencyIsDone() {
        CompetencyDTO apprenticeCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", "Done", null);
        CompetencyDTO rotationCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", null, 2.0);
        RotationDTO rotationDTO = new RotationDTO(1, "Backend Rotation", "Backend Entwicklung mit Spring Boot", "IT", "Java, Spring Boot", "available", LocalDate.now());

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Arrays.asList(apprenticeCompetency));
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(rotationDTO));
        when(this.competencyService.getCompetenciesByRotation(1)).thenReturn(Arrays.asList(rotationCompetency));

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(1, result.size());
        assertEquals(0.0, result.get(0).getMatchingScore());
    }

    @Test
    void shouldReturnHalfScoreWhenCompetencyIsInProgress() {
        CompetencyDTO apprenticeCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", "In Progress", null);
        CompetencyDTO rotationCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", null, 2.0);
        RotationDTO rotationDTO = new RotationDTO(1, "Backend Rotation", "Backend Entwicklung mit Spring Boot", "IT", "Java, Spring Boot", "available", LocalDate.now());

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Arrays.asList(apprenticeCompetency));
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(rotationDTO));
        when(this.competencyService.getCompetenciesByRotation(1)).thenReturn(Arrays.asList(rotationCompetency));

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(1, result.size());
        assertEquals(1.0, result.get(0).getMatchingScore());
    }

    @Test
    void shouldReturnFullScoreWhenCompetencyIsOpen() {
        CompetencyDTO apprenticeCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", "Open", null);
        CompetencyDTO rotationCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", null, 2.0);
        RotationDTO rotationDTO = new RotationDTO(1, "Backend Rotation", "Backend Entwicklung mit Spring Boot", "IT", "Java, Spring Boot", "available", LocalDate.now());

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Arrays.asList(apprenticeCompetency));
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(rotationDTO));
        when(this.competencyService.getCompetenciesByRotation(1)).thenReturn(Arrays.asList(rotationCompetency));

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(1, result.size());
        assertEquals(2.0, result.get(0).getMatchingScore());
    }

    @Test
    void shouldReturnFullScoreWhenApprenticeDoesNotHaveCompetency() {
        CompetencyDTO rotationCompetency = new CompetencyDTO(2, "Datenbanken verwenden", "Applikationsentwicklung", "C2", null, 3.0);
        RotationDTO rotationDTO = new RotationDTO(1, "Database Rotation", "Arbeit mit MariaDB", "IT", "MariaDB", "available", LocalDate.now());

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Collections.emptyList());
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(rotationDTO));
        when(this.competencyService.getCompetenciesByRotation(1)).thenReturn(Arrays.asList(rotationCompetency));

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(1, result.size());
        assertEquals(3.0, result.get(0).getMatchingScore());
    }

    @Test
    void shouldSortMatchesByScoreDescending() {
        CompetencyDTO openCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", "Open", null);
        RotationDTO lowScoreRotation = new RotationDTO(1, "Low Score Rotation", "Kleine Übereinstimmung", "IT", "Java", "available", LocalDate.now());
        RotationDTO highScoreRotation = new RotationDTO(2, "High Score Rotation", "Hohe Übereinstimmung", "IT", "Java, SQL", "available", LocalDate.now());
        CompetencyDTO lowWeightCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", null, 1.0);
        CompetencyDTO highWeightCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", null, 5.0);

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Arrays.asList(openCompetency));
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(lowScoreRotation, highScoreRotation));
        when(this.competencyService.getCompetenciesByRotation(1)).thenReturn(Arrays.asList(lowWeightCompetency));
        when(this.competencyService.getCompetenciesByRotation(2)).thenReturn(Arrays.asList(highWeightCompetency));

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(2, result.size());
        assertEquals(2, result.get(0).getRotationId());
        assertTrue(result.get(0).getMatchingScore() > result.get(1).getMatchingScore());
    }

    @Test
    void shouldCalculateScoreWithMultipleCompetencies() {
        CompetencyDTO doneCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", "Done", null);
        CompetencyDTO inProgressCompetency = new CompetencyDTO(2, "Datenbanken verwenden", "Applikationsentwicklung", "C2", "In Progress", null);
        CompetencyDTO openCompetency = new CompetencyDTO(3, "Schnittstellen entwickeln", "Applikationsentwicklung", "C3", "Open", null);

        CompetencyDTO rotationCompetencyOne = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", null, 2.0);
        CompetencyDTO rotationCompetencyTwo = new CompetencyDTO(2, "Datenbanken verwenden", "Applikationsentwicklung", "C2", null, 4.0);
        CompetencyDTO rotationCompetencyThree = new CompetencyDTO(3, "Schnittstellen entwickeln", "Applikationsentwicklung", "C3", null, 3.0);

        RotationDTO rotationDTO = new RotationDTO(1, "Fullstack Rotation", "Backend, Datenbank und Schnittstellen", "IT", "Java, MariaDB, REST", "available", LocalDate.now());

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Arrays.asList(doneCompetency, inProgressCompetency, openCompetency));
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(rotationDTO));
        when(this.competencyService.getCompetenciesByRotation(1)).thenReturn(Arrays.asList(rotationCompetencyOne, rotationCompetencyTwo, rotationCompetencyThree));

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(1, result.size());
        assertEquals(5.0, result.get(0).getMatchingScore());
    }

    @Test
    void shouldUseDefaultWeightWhenWeightIsNull() {
        CompetencyDTO apprenticeCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", "Open", null);
        CompetencyDTO rotationCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", null, null);
        RotationDTO rotationDTO = new RotationDTO(1, "Backend Rotation", "Backend Entwicklung", "IT", "Java", "available", LocalDate.now());

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Arrays.asList(apprenticeCompetency));
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(rotationDTO));
        when(this.competencyService.getCompetenciesByRotation(1)).thenReturn(Arrays.asList(rotationCompetency));

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(1, result.size());
        assertEquals(1.0, result.get(0).getMatchingScore());
    }

    @Test
    void shouldReturnEmptyListWhenNoRotationsAreAvailable() {
        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Collections.emptyList());
        when(this.rotationService.getAvailableRotations()).thenReturn(Collections.emptyList());

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnScoreZeroWhenRotationHasNoCompetencies() {
        RotationDTO rotationDTO = new RotationDTO(1, "Leere Rotation", "Keine Kompetenzen zugeordnet", "IT", "Keine", "available", LocalDate.now());

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Collections.emptyList());
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(rotationDTO));
        when(this.competencyService.getCompetenciesByRotation(1)).thenReturn(Collections.emptyList());

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(1, result.size());
        assertEquals(0.0, result.get(0).getMatchingScore());
    }

    @Test
    void shouldMapRotationDataToMatchDTO() {
        CompetencyDTO rotationCompetency = new CompetencyDTO(1, "Backend entwickeln", "Applikationsentwicklung", "C1", null, 2.0);
        RotationDTO rotationDTO = new RotationDTO(7, "Backend Rotation", "Backend Entwicklung", "IT Bildung", "Java, Spring Boot", "available", LocalDate.now());

        when(this.apprenticeService.getApprenticeCompetencies(1)).thenReturn(Collections.emptyList());
        when(this.rotationService.getAvailableRotations()).thenReturn(Arrays.asList(rotationDTO));
        when(this.competencyService.getCompetenciesByRotation(7)).thenReturn(Arrays.asList(rotationCompetency));

        List<MatchDTO> result = this.matchingService.getMatchingRotationsByApprentice(1);

        assertEquals(1, result.size());
        assertEquals(7, result.get(0).getRotationId());
        assertEquals("Backend Rotation", result.get(0).getRotationTitle());
        assertEquals("IT Bildung", result.get(0).getDepartment());
        assertEquals("Java, Spring Boot", result.get(0).getTechnologies());
        assertEquals(2.0, result.get(0).getMatchingScore());
    }
}