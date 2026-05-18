package ch.sbb.rotationmanagement.service;

import ch.sbb.rotationmanagement.dto.RotationDTO;
import ch.sbb.rotationmanagement.repository.RotationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public RotationDTO getRotationById(Integer id) {
        return this.rotationRepository.getRotationById(id);
    }

    @Transactional
    public void updateRotationById(Integer id, RotationDTO rotationDTO) {
        this.rotationRepository.updateRotationById(id, rotationDTO);
    }
}