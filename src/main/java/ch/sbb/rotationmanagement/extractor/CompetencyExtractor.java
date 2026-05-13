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
                    resultSet.getLong("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("competence_area"),
                    resultSet.getInt("semester")
            );

            competencies.add(competencyDTO);
        }

        return competencies;
    }
}