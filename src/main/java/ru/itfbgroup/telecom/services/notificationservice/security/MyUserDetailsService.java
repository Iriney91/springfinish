package ru.itfbgroup.telecom.services.notificationservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.repository.ClientRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = repository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException("No such user found"));
        return new ClientDetails(client);
    }
}
