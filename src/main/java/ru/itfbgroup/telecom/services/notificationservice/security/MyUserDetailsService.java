package ru.itfbgroup.telecom.services.notificationservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.repository.ClientRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client customer = repository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException("No such user found"));
        return new ClientDetails(customer);
    }
}
