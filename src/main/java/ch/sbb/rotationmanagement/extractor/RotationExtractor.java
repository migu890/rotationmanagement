package ch.sbb.rotationmanagement.extractor;

import ch.sbb.rotationmanagement.dto.ApprenticeDTO;
import ch.sbb.rotationmanagement.dto.AssignmentDTO;
import ch.sbb.rotationmanagement.dto.RotationDTO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


@Component
public class RotationExtractor implements ResultSetExtractor<RotationDTO> {

    @Override
    public RotationDTO extractData(java.sql.ResultSet resultSet) throws SQLException {
        RotationDTO rotation = new RotationDTO();

        while (resultSet.next()) {
            rotation.setId(resultSet.getInt("id"));
            rotation.setDescription(resultSet.getString("description"));
            rotation.setDepartment(resultSet.getString("department"));
            rotation.setTechnologies(resultSet.getString("technologies"));
            rotation.setStatus(resultSet.getString("status"));
            rotation.setDateOfAvailability(resultSet.getDate("date_of_availability").toLocalDate());

        }

        return rotation;
    }
}