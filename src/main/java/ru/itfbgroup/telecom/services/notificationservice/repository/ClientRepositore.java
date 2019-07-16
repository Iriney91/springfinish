package ru.itfbgroup.telecom.services.notificationservice.repository;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface ClientRepositore extends JpaRepository <Client, Long>{

    Page<Client> findAllByFullNameLikeAndLogin (String fullName, String login, Pageable pageable);
}
