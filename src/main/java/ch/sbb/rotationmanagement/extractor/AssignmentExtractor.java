package ch.sbb.rotationmanagement.extractor;

import ch.sbb.rotationmanagement.dto.ApprenticeDTO;
import ch.sbb.rotationmanagement.dto.AssignmentDTO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


@Component
public class AssignmentExtractor implements ResultSetExtractor<AssignmentDTO> {

    @Override
    public AssignmentDTO extractData(java.sql.ResultSet resultSet) throws SQLException {
        AssignmentDTO assignment = new AssignmentDTO();

        while (resultSet.next()) {
            assignment.setId(resultSet.getInt("id"));
            assignment.setApprenticeId(resultSet.getInt("apprentice_id"));
            assignment.setRotationId(resultSet.getInt("rotation_id"));
            assignment.setStartDate(resultSet.getDate("start_date").toLocalDate());
            assignment.setEndDate(resultSet.getDate("end_date").toLocalDate());

        }

        return assignment;
    }
}