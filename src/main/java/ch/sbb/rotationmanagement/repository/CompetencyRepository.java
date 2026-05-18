package ch.sbb.rotationmanagement.repository;

import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.extractor.CompetencyExtractor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
public class CompetencyRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CompetencyExtractor competencyExtractor;
    private final Properties queryProperties;

    public CompetencyRepository(
            NamedParameterJdbcTemplate jdbcTemplate,
            CompetencyExtractor competencyExtractor,
            @Qualifier("rotationQueries") Properties queryProperties
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.competencyExtractor = competencyExtractor;
        this.queryProperties = queryProperties;
    }

    public List<CompetencyDTO> getAllCompetencies() {

        String query = this.queryProperties.getProperty("getAllCompetencies");

           return this.jdbcTemplate.query(
                query,
                this.competencyExtractor
        );
    }

    public List<CompetencyDTO> getCompetenciesByRotation(Integer id) {

        String query = this.queryProperties.getProperty("getCompetenciesByRotation");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("rotationId", id);

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        return this.jdbcTemplate.query(
                query,
                parameters,
                this.competencyExtractor
        );
    }
}