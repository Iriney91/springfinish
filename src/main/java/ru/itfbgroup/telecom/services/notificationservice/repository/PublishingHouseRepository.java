package ru.itfbgroup.telecom.services.notificationservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itfbgroup.telecom.services.notificationservice.model.PublishingHouse;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface PublishingHouseRepository extends JpaRepository <PublishingHouse, Long>{

    List <PublishingHouse> findAllByNameLikeAndInn(String name, String inn, Pageable pageable);
}
