package ch.sbb.rotationmanagement.repository;

import ch.sbb.rotationmanagement.dto.AssignmentDTO;
import ch.sbb.rotationmanagement.dto.RotationDTO;
import ch.sbb.rotationmanagement.extractor.MultipleRotationExtractor;
import ch.sbb.rotationmanagement.extractor.RotationExtractor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
public class RotationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final MultipleRotationExtractor multipleRotationExtractor;
    private final RotationExtractor rotationExtractor;
    private final Properties queryProperties;

    public RotationRepository(
            NamedParameterJdbcTemplate jdbcTemplate,
            MultipleRotationExtractor multipleRotationExtractor,
            RotationExtractor rotationExtractor,
            @Qualifier("rotationQueries") Properties queryProperties
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.multipleRotationExtractor = multipleRotationExtractor;
        this.rotationExtractor = rotationExtractor;
        this.queryProperties = queryProperties;
    }

    public List<RotationDTO> getAllRotations() {

        String query = this.queryProperties.getProperty("getAllRotations");
        return this.jdbcTemplate.query(
                query,
                this.multipleRotationExtractor
        );
    }

    public List<RotationDTO> getAvailableRotations() {

        String query = this.queryProperties.getProperty("getAvailableRotations");
        return this.jdbcTemplate.query(
                query,
                this.multipleRotationExtractor
        );
    }

    public RotationDTO getRotationById(Integer id) {

            String query = this.queryProperties.getProperty("getRotationById");

            Map<String, Object> queryParameters = new HashMap<>();
            queryParameters.put("id", id);

            MapSqlParameterSource parameters =
                    new MapSqlParameterSource(queryParameters);

            return this.jdbcTemplate.query(
                    query,
                    parameters,
                    this.rotationExtractor
            );
    }

    public void updateRotationById(Integer id, RotationDTO rotationDTO) {

        String query = this.queryProperties.getProperty("updateRotationById");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("id", id);
        queryParameters.put("title", rotationDTO.getTitle());
        queryParameters.put("description", rotationDTO.getDescription());
        queryParameters.put("department", rotationDTO.getDepartment());
        queryParameters.put("technologies", rotationDTO.getTechnologies());
        queryParameters.put("status", rotationDTO.getStatus());
        queryParameters.put("dateOfAvailability", rotationDTO.getDateOfAvailability());

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        this.jdbcTemplate.update(
                query,
                parameters
        );
    }
}