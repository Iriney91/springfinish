package ru.itfbgroup.telecom.services.notificationservice.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;

import java.util.Collection;
import java.util.Collections;

@Slf4j
public class ClientDetails implements UserDetails {

    private Client client;

    public ClientDetails(Client client) {
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority(client.getUserRole()));
    }

    @Override
    public String getPassword() {
        return client.getPassword();
    }

    @Override
    public String getUsername() {
        return client.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
