package ch.sbb.rotationmanagement.repository;

import ch.sbb.rotationmanagement.dto.RotationDTO;
import ch.sbb.rotationmanagement.extractor.MultipleRotationExtractor;
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
    private final Properties queryProperties;

    public RotationRepository(
            NamedParameterJdbcTemplate jdbcTemplate,
            MultipleRotationExtractor multipleRotationExtractor,
            Properties queryProperties
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.multipleRotationExtractor = multipleRotationExtractor;
        this.queryProperties = queryProperties;
    }

    public List<RotationDTO> getAllRotations() {

        String query = this.queryProperties.getProperty("getAllRotations");

        Map<String, Object> queryParameters = new HashMap<>();

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        return this.jdbcTemplate.query(
                query,
                parameters,
                this.multipleRotationExtractor
        );
    }

    public List<RotationDTO> getAvailableRotations() {

        String query = this.queryProperties.getProperty("getAvailableRotations");

        Map<String, Object> queryParameters = new HashMap<>();

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        return this.jdbcTemplate.query(
                query,
                parameters,
                this.multipleRotationExtractor
        );
    }
}