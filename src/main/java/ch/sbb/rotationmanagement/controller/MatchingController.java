package ch.sbb.rotationmanagement.controller;

import ch.sbb.rotationmanagement.dto.MatchDTO;
import ch.sbb.rotationmanagement.service.MatchingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("${api.matching}/{apprenticeId}")
    public List<MatchDTO> getMatchingRotationsByApprentice(
            @PathVariable("apprenticeId") Integer apprenticeId
    ) {
        return this.matchingService.getMatchingRotationsByApprentice(apprenticeId);
    }
}