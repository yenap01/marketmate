package com.shop.shop.service;

import com.shop.shop.entity.Client;
import com.shop.shop.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public Client saveClient(Client client) {
        validateDuplicateClient(client);
        return clientRepository.save(client);
    }

    private void validateDuplicateClient(Client client) {
        Optional<Client> findClient = clientRepository.findById(client.getUser_Num());
        if (findClient != null) {
            throw new IllegalStateException("이미 가입한 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Client client = clientRepository.findByEmail(email);

        if (client == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(client.getEmail())
                .password(client.getPassword())
                .roles(client.getRole().toString())
                .build();
    }

}
