package ch.sbb.rotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprenticeDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer year;
    private Boolean lookingForRotation;
}