package ch.sbb.rotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetencyDTO {

    private Long id;
    private String title;
    private String description;
    private String competenceArea;
    private Integer semester;
}