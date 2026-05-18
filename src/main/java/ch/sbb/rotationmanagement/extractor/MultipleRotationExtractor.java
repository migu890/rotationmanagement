package ch.sbb.rotationmanagement.extractor;

import ch.sbb.rotationmanagement.dto.RotationDTO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MultipleRotationExtractor implements ResultSetExtractor<List<RotationDTO>> {

    @Override
    public List<RotationDTO> extractData(ResultSet resultSet) throws SQLException {

        List<RotationDTO> rotations = new ArrayList<>();

        while (resultSet.next()) {

            RotationDTO rotationDTO = new RotationDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getString("department"),
                    resultSet.getString("technologies"),
                    resultSet.getString("status"),
                    resultSet.getDate("date_of_availability").toLocalDate()
            );

            rotations.add(rotationDTO);
        }

        return rotations;
    }
}