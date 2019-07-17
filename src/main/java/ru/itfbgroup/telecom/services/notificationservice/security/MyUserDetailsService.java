package ru.itfbgroup.telecom.services.notificationservice.security;

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

@Service
public class MyUserDetailsService implements UserDetailsService {

// private ClientDetails clientDetails;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
////
//        Client client = clientDetails.getUser(email);
//        Set<GrantedAuthority> roles = new HashSet();
//        roles.add(new SimpleGrantedAuthority("User"));
//        UserDetails userDetails =
//                new org.springframework.security.core.userdetails.User(client.getLogin(),
//                        client.getPassword(),
//                        roles);
//
//        return userDetails;
//    }
//
//    private final ClientRepository clientRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        return new ClientDetails(clientRepository.findClientByLogin(login).format("Customer not found with login: %s", login));
//    }
}
