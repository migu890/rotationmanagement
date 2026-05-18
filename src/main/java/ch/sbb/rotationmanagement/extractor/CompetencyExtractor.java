package ch.sbb.rotationmanagement.extractor;

import ch.sbb.rotationmanagement.dto.CompetencyDTO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompetencyExtractor implements ResultSetExtractor<List<CompetencyDTO>> {

    @Override
    public List<CompetencyDTO> extractData(ResultSet resultSet) throws SQLException {

        List<CompetencyDTO> competencies = new ArrayList<>();

        while (resultSet.next()) {

            CompetencyDTO competencyDTO = new CompetencyDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("category"),
                    resultSet.getString("code"),
                    this.getStringIfExists(resultSet, "competency_state"),
                    this.getDoubleIfExists(resultSet, "weight")
            );

            competencies.add(competencyDTO);
        }

        return competencies;
    }

    private String getStringIfExists(ResultSet resultSet, String columnName) {
        try {
            return resultSet.getString(columnName);
        } catch (SQLException exception) {
            return null;
        }
    }

    private Double getDoubleIfExists(ResultSet resultSet, String columnName) {
        try {
            double value = resultSet.getDouble(columnName);
            return resultSet.wasNull() ? null : value;
        } catch (SQLException exception) {
            return null;
        }
    }
}