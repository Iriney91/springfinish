package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public Page<PublishingHouse> getPaginatedBySearchRequest(PublishingHousePaginalRequestDTO publishingHousePaginalRequestDTO) {
        return publishingHouseRepository.findAllByNameLikeAndInn(publishingHousePaginalRequestDTO.getName(), publishingHousePaginalRequestDTO.getInn(),
                publishingHousePaginalRequestDTO.getPageRequest());
    }

    public PublishingHouse getById(Long id) {
        return publishingHouseRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public void update(PublishingHouse publishingHouse) {
        if (publishingHouseRepository.existsById(publishingHouse.getId())) {
            publishingHouseRepository.save(publishingHouse);
        } else {
            throw new IllegalArgumentException("No such author found");
        }
    }

    public void delete(Long id) {
        if (publishingHouseRepository.existsById(id)) {
            publishingHouseRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No such author found");
        }
    }

    public PublishingHouse create(PublishingHouse publishingHouse) {
        if (!publishingHouseRepository.existsById(publishingHouse.getId())) {
            publishingHouseRepository.save(publishingHouse);
        } else {
            throw new IllegalArgumentException("Already exist");
        }
        return publishingHouse;
    }
}