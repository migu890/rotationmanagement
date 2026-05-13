package ch.sbb.rotationmanagement.extractor;

import ch.sbb.rotationmanagement.dto.ApprenticeDTO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


@Component
public class ApprenticeExtractor implements ResultSetExtractor<ApprenticeDTO> {

    @Override
    public ApprenticeDTO extractData(java.sql.ResultSet resultSet) throws SQLException {
            ApprenticeDTO apprentice = new ApprenticeDTO();

        while (resultSet.next()) {
                    apprentice.setId(resultSet.getLong("id"));
                    apprentice.setFirstName(resultSet.getString("first_name")); 
                    apprentice.setLastName(resultSet.getString("last_name")); 
                    apprentice.setYear(resultSet.getInt("year"));
                    apprentice.setLookingForRotation(resultSet.getBoolean("looking_for_rotation")); 

        }

        return apprentice;
    }
}