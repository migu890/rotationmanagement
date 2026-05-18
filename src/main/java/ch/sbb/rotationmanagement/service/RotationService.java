package ch.sbb.rotationmanagement.service;

import ch.sbb.rotationmanagement.dto.RotationDTO;
import ch.sbb.rotationmanagement.repository.RotationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotationService {

    private final RotationRepository rotationRepository;

    public RotationService(RotationRepository rotationRepository) {
        this.rotationRepository = rotationRepository;
    }

    public List<RotationDTO> getAllRotations() {
        return this.rotationRepository.getAllRotations();
    }

    public List<RotationDTO> getAvailableRotations() {
        return this.rotationRepository.getAvailableRotations();
    }
}