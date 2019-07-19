package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.repository.ClientRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final ClientRepository clientRepository;

    private final ApplicationContext context;

    public void clientAuthorization (String login) {

        String password = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        CharSequence pass = encoder.encode(password);

        if (clientRepository.existsByLogin(login)) {
            clientRepository.findByPassword(pass.toString());
            log.info(String.format("Client password is: %s", password));
        }
    }
}
