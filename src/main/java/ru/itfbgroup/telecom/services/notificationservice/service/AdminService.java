package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.repository.ClientRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final ClientRepository clientRepository;

    private final ApplicationContext context;

    public void clientAuthorization (Long id) {

        String password = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        CharSequence pass = encoder.encode(password);
        System.out.println(password);

        Client  client = clientRepository.findById(id).get();
        client.setPassword(pass.toString());
        clientRepository.save(client);
            log.info(String.format("Client password is: %s", password));

    }
}
