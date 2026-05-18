package ch.sbb.rotationmanagement.extractor;

import ch.sbb.rotationmanagement.dto.AssignmentDTO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MultipleAssignmentExtractor implements ResultSetExtractor<List<AssignmentDTO>> {

    @Override
    public List<AssignmentDTO> extractData(ResultSet resultSet) throws SQLException {

        List<AssignmentDTO> assignments = new ArrayList<>();

        while (resultSet.next()) {

            AssignmentDTO assignmentDTO = new AssignmentDTO(
                    resultSet.getLong("id"),
                    resultSet.getLong("apprentice_id"),
                    resultSet.getLong("rotation_id"),
                    resultSet.getDate("start_date").toLocalDate(),
                    resultSet.getDate("end_date").toLocalDate()
            );

            assignments.add(assignmentDTO);
        }

        return assignments;
    }
}