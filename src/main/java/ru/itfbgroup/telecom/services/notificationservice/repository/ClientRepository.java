package ru.itfbgroup.telecom.services.notificationservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    @Query("SELECT c FROM Client c WHERE (:fullName is null or c.fullName = :fullName) and (:isConfirmed is null or c.isConfirmed = :isConfirmed) and (:login is null"
            + " or c.login = :login)" )
    Page<Client> findAllBySearchParams(@Param("fullName") String fullName, @Param("login") String login, @Param("isConfirmed") boolean isConfirmed, Pageable pageable);

    Optional<Client> findByFullName(String fullName);

    Optional<Client> findByLogin(String login);

    Boolean existsByLogin(String login);

    Optional <Client> findByPassword (String password);
}
