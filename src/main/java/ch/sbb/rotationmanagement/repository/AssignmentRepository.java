package ch.sbb.rotationmanagement.repository;

import ch.sbb.rotationmanagement.dto.AssignmentDTO;
import ch.sbb.rotationmanagement.extractor.AssignmentExtractor;
import ch.sbb.rotationmanagement.extractor.MultipleAssignmentExtractor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
public class AssignmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AssignmentExtractor assignmentExtractor;
    private final MultipleAssignmentExtractor multipleAssignmentExtractor;
    private final Properties queryProperties;

    public AssignmentRepository(
            NamedParameterJdbcTemplate jdbcTemplate,
            AssignmentExtractor assignmentExtractor,
            MultipleAssignmentExtractor multipleAssignmentExtractor,
            @Qualifier("rotationQueries") Properties queryProperties
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.assignmentExtractor = assignmentExtractor;
        this.multipleAssignmentExtractor = multipleAssignmentExtractor;
        this.queryProperties = queryProperties;
    }

    public List<AssignmentDTO> getAllAssignments() {

        String query = this.queryProperties.getProperty("getAllAssignments");
        return this.jdbcTemplate.query(
                query,
                this.multipleAssignmentExtractor
        );
    }

    public AssignmentDTO getAssignmentById(Integer id) {

        String query = this.queryProperties.getProperty("getAssignmentById");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("id", id);

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        return this.jdbcTemplate.query(
                query,
                parameters,
                this.assignmentExtractor
        );
    }

    public AssignmentDTO getAssignmentByApprentice(Integer id) {

        String query = this.queryProperties.getProperty("getAssignmentByApprentice");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("id", id);

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        return this.jdbcTemplate.query(
                query,
                parameters,
                this.assignmentExtractor
        );
    }

    public void createAssignment(AssignmentDTO assignmentDTO) {

        String query = this.queryProperties.getProperty("createAssignment");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("apprenticeId", assignmentDTO.getApprenticeId());
        queryParameters.put("rotationId", assignmentDTO.getRotationId());
        queryParameters.put("startDate", assignmentDTO.getStartDate());
        queryParameters.put("endDate", assignmentDTO.getEndDate());

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        this.jdbcTemplate.update(
                query,
                parameters
        );
    }

    public void deleteAssignmentById(Integer id) {

        String query = this.queryProperties.getProperty("deleteAssignmentById");

        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("id", id);

        MapSqlParameterSource parameters =
                new MapSqlParameterSource(queryParameters);

        this.jdbcTemplate.update(
                query,
                parameters
        );
    }

    public List<AssignmentDTO> getAllAssignmentsByApprentice(Integer id) {

            String query = this.queryProperties.getProperty("getAllAssignmentsByApprentice");

            Map<String, Object> queryParameters = new HashMap<>();
            queryParameters.put("id", id);

            MapSqlParameterSource parameters =
                    new MapSqlParameterSource(queryParameters);

            return this.jdbcTemplate.query(
                    query,
                    parameters,
                    this.multipleAssignmentExtractor
            );
        }
}