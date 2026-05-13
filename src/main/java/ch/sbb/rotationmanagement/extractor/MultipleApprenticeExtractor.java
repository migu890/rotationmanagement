package ch.sbb.rotationmanagement.extractor;

import ch.sbb.rotationmanagement.dto.ApprenticeDTO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MultipleApprenticeExtractor implements ResultSetExtractor<List<ApprenticeDTO>> {

    @Override
    public List<ApprenticeDTO> extractData(java.sql.ResultSet resultSet) throws SQLException {
        List<ApprenticeDTO> apprentices = new ArrayList<>();

        while (resultSet.next()) {
            ApprenticeDTO apprentice = new ApprenticeDTO(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("year"),
                    resultSet.getBoolean("looking_for_rotation")
            );

            apprentices.add(apprentice);
        }

        return apprentices;
    }
}