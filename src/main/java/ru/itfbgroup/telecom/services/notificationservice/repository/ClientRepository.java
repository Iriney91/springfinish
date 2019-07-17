package ru.itfbgroup.telecom.services.notificationservice.repository;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository <Client, Long>, JpaSpecificationExecutor <Client>{

    Page<Client> findClientByLogin (String login, Pageable pageable);

    boolean existsByLogin (String login);


}
