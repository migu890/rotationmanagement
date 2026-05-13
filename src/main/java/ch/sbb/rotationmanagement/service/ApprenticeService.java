package ch.sbb.rotationmanagement.service;

import ch.sbb.rotationmanagement.dto.ApprenticeDTO;
import ch.sbb.rotationmanagement.repository.ApprenticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenticeService {

    private final ApprenticeRepository apprenticeRepository;

    public ApprenticeService(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    public List<ApprenticeDTO> getAllApprentices() {
        return apprenticeRepository.getAllApprentices();
    }
}