package ch.sbb.rotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RotationDTO {

    private Long id;
    private String title;
    private String description;
    private String department;
    private String technologies;
    private String status;
    private LocalDate dateOfAvailability;
}