package ch.sbb.rotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO {

    private Long id;
    private Long apprenticeId;
    private Long rotationId;
    private LocalDate startDate;
    private LocalDate endDate;
}