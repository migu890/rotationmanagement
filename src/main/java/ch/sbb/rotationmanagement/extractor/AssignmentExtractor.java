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
            assignment.setId(resultSet.getLong("id"));
            assignment.setApprenticeId(resultSet.getLong("apprenticeId"));
            assignment.setRotationId(resultSet.getLong("rotationId"));
            assignment.setStartDate(resultSet.getDate("startDate").toLocalDate());
            assignment.setEndDate(resultSet.getDate("endDate").toLocalDate());

        }

        return assignment;
    }
}