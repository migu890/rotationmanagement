package ch.sbb.rotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetencyDTO {

    private Integer id;
    private String description;
    private String category;
    private String code;
    private String competencyState;
    private Double weight;
}