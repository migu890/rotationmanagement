package ch.sbb.rotationmanagement.repository;

import ch.sbb.rotationmanagement.dto.ApprenticeDto;
import ch.sbb.rotationmanagement.extractor.ApprenticeExtractor;
import ch.sbb.rotationmanagement.service.QueryService;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ApprenticeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final QueryService queryService;
    private final ApprenticeExtractor apprenticeExtractor;

    public ApprenticeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, QueryService queryService, ApprenticeExtractor apprenticeExtractor) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.queryService = queryService;
        this.apprenticeExtractor = apprenticeExtractor;
    }

    public List<ApprenticeDto> findAll() {
        return namedParameterJdbcTemplate.query(
                queryService.getQuery("getAllApprentices"),
                Map.of(),
                apprenticeExtractor
        );
    }
}