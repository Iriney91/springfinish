package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.PublishingHouse;
import ru.itfbgroup.telecom.services.notificationservice.repository.PublishingHouseRepository;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.PublishingHousePaginalRequestDTO;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    public List<PublishingHouse> getPaginatedBySearchRequest(PublishingHousePaginalRequestDTO publishingHousePaginalRequestDTO) {
        return publishingHouseRepository.findAllByNameLikeAndINN(publishingHousePaginalRequestDTO.getName(), publishingHousePaginalRequestDTO.getINN(),
                publishingHousePaginalRequestDTO.getPageRequest());
    }

    public PublishingHouse getById(Long Id) {
        return publishingHouseRepository.findById(Id).orElseThrow(IllegalAccessError::new);
    }

    public void update(PublishingHouse publishingHouse) {
        if (publishingHouseRepository.existsById(publishingHouse.getId())) {
            publishingHouseRepository.save(publishingHouse);
        } else {
            Result.error(1, "Is not exist", new IllegalArgumentException());
        }
    }

    public void delete(PublishingHouse publishingHouse) {
        if (publishingHouseRepository.existsById(publishingHouse.getId())) {
            publishingHouseRepository.delete(publishingHouse);
        } else {
            Result.error(1, "Is not exist", new IllegalArgumentException());
        }
    }

    public PublishingHouse create(PublishingHouse publishingHouse) {
        if (!publishingHouseRepository.existsById(publishingHouse.getId())) {
            publishingHouseRepository.save(publishingHouse);
        } else {
            Result.error(2, "Already exist", new IllegalArgumentException());
        }
        return publishingHouse;
    }
}