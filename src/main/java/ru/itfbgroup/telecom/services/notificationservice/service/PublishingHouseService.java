package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.model.PublishingHouse;
import ru.itfbgroup.telecom.services.notificationservice.repository.PublishingHouseRepository;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.PublishingHousePaginalRequestDTO;


@Service
@RequiredArgsConstructor
public class PublishingHouseService {


    private final PublishingHouseRepository publishingHouseRepository;

    public Page<PublishingHouse> getPaginatedBySearchRequest(PublishingHousePaginalRequestDTO publishingHousePaginalRequestDTO) {
        if (publishingHousePaginalRequestDTO.getSortColumn() == null)
            publishingHousePaginalRequestDTO.setSortColumn("name");
        if (publishingHousePaginalRequestDTO.getName() == null)

            return publishingHouseRepository.findAll(publishingHousePaginalRequestDTO.getPageRequest());

        return publishingHouseRepository.findAllByNameLikeAndInn(publishingHousePaginalRequestDTO.getName(),
                publishingHousePaginalRequestDTO.getInn(), publishingHousePaginalRequestDTO.getPageRequest());
    }

    public PublishingHouse getById(Long id) {
        return publishingHouseRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public PublishingHouse update(PublishingHouse publishingHouse) {
        if (publishingHouseRepository.existsById(publishingHouse.getId())) {
            publishingHouseRepository.save(publishingHouse);
        } else {
            throw new IllegalArgumentException("No such publishingHouse found");
        }
        return publishingHouse;
    }

    public void delete(Long id) {
        if (publishingHouseRepository.existsById(id)) {
            publishingHouseRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No such publishingHouse found");
        }
    }

    public PublishingHouse create(PublishingHouse publishingHouse) {
        return publishingHouseRepository.save(publishingHouse);
    }
}