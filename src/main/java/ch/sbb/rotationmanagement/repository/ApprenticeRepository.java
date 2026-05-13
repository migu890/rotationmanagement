package ch.sbb.rotationmanagement.repository;

import ch.sbb.rotationmanagement.dto.ApprenticeDTO;
import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import ch.sbb.rotationmanagement.extractor.MultipleApprenticeExtractor;
import ch.sbb.rotationmanagement.extractor.ApprenticeExtractor;
import ch.sbb.rotationmanagement.extractor.CompetencyExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
public class ApprenticeRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final MultipleApprenticeExtractor multipleApprenticeExtractor;
    private final ApprenticeExtractor apprenticeExtractor;
    private final CompetencyExtractor competencyExtractor;
    private final Properties queryProperties;

    public ApprenticeRepository(
            NamedParameterJdbcTemplate jdbcTemplate,
            MultipleApprenticeExtractor multipleApprenticeExtractor,
            ApprenticeExtractor apprenticeExtractor,
            CompetencyExtractor competencyExtractor,
            Properties queryProperties
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.apprenticeExtractor = apprenticeExtractor;
        this.multipleApprenticeExtractor = multipleApprenticeExtractor;
        this.competencyExtractor = competencyExtractor;
        this.queryProperties = queryProperties;
    }

    public List<ApprenticeDTO> getAllApprentices() {

        String query = this.queryProperties.getProperty("getAllApprentices");

        Map<String, Object> queryParameters = new HashMap<>();

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        return this.jdbcTemplate.query(
                query,
                parameters,
                this.multipleApprenticeExtractor
        );
    }

    public ApprenticeDTO getApprenticeById(Long id) {

        String query = this.queryProperties.getProperty("getApprenticeById");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("id", id);

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        ApprenticeDTO apprentice = this.jdbcTemplate.query(
                query,
                parameters,
                this.apprenticeExtractor
        );

        return apprentice;
    }

    public void createApprentice(ApprenticeDTO apprenticeDTO) {

        String query = this.queryProperties.getProperty("createApprentice");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("firstName", apprenticeDTO.getFirstName());
        queryParameters.put("lastName", apprenticeDTO.getLastName());
        queryParameters.put("year", apprenticeDTO.getYear());
        queryParameters.put("lookingForRotation", apprenticeDTO.getLookingForRotation());

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        this.jdbcTemplate.update(
                query,
                parameters
        );
    }

    public void updateApprentice(ApprenticeDTO apprenticeDTO) {

        String query = this.queryProperties.getProperty("updateApprentice");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("id", apprenticeDTO.getId());
        queryParameters.put("firstName", apprenticeDTO.getFirstName());
        queryParameters.put("lastName", apprenticeDTO.getLastName());
        queryParameters.put("year", apprenticeDTO.getYear());
        queryParameters.put("lookingForRotation", apprenticeDTO.getLookingForRotation());

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        this.jdbcTemplate.update(
                query,
                parameters
        );
    }

    public void deleteApprenticeById(Long id) {

        String query = this.queryProperties.getProperty("deleteApprenticeById");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("id", id);

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        this.jdbcTemplate.update(
                query,
                parameters
        );
    }

    public List<CompetencyDTO> getApprenticeCompetencies(Long id) {

        String query = this.queryProperties.getProperty("getApprenticeCompetencies");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("id", id);

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        return this.jdbcTemplate.query(
                query,
                parameters,
                this.competencyExtractor
        );
    }

    public void updateCompetenciesByApprentice(Long id, List<CompetencyDTO> competencyDTOs) {

        String deleteQuery = this.queryProperties.getProperty("deleteCompetenciesByApprenticeId");

        Map<String, Object> deleteParametersMap = new HashMap<>();
        deleteParametersMap.put("id", id);

        MapSqlParameterSource deleteParameters =
                new MapSqlParameterSource(deleteParametersMap);

        this.jdbcTemplate.update(
                deleteQuery,
                deleteParameters
        );

        String insertQuery = this.queryProperties.getProperty("createApprenticeCompetency");

        for (CompetencyDTO competencyDTO : competencyDTOs) {

            Map<String, Object> insertParametersMap = new HashMap<>();
            insertParametersMap.put("apprenticeId", id);
            insertParametersMap.put("competencyId", competencyDTO.getId());

            MapSqlParameterSource insertParameters =
                    new MapSqlParameterSource(insertParametersMap);

            this.jdbcTemplate.update(
                    insertQuery,
                    insertParameters
            );
        }
    }
}