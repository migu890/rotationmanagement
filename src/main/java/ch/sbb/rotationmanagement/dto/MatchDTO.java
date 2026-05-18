package ch.sbb.rotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {

    private Integer rotationId;
    private String rotationTitle;
    private String department;
    private String technologies;
    private Double matchingScore;
}