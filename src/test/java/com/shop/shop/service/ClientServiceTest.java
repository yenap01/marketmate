package com.shop.shop.service;

import com.shop.shop.dto.ClientFormDto;
import com.shop.shop.entity.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    public void saveClientTest() {
        // given
        Client client = createClient();

        // when
        Client saveClient = clientService.saveClient(client);

        // then
        // 객체를 비교하여 확인
        assertEquals(client, saveClient);
    }

    @Test
    @DisplayName("중복 가입 테스트")
    public void saverDuplicateClientTest() {
        // given
        Client client1 = createClient();

        // 회원 가입
        clientService.saveClient(client1);

        // when
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            // 중복 가입 시도
            clientService.saveClient(client1);
        });

        // then
        assertEquals("이미 가입한 회원입니다.", e.getMessage());
    }

    public Client createClient() {
        ClientFormDto clientFormDto = new ClientFormDto();
        clientFormDto.setUser("ye01");
        clientFormDto.setPassword("1234");
        clientFormDto.setUserName("박예나");
        clientFormDto.setEmail("emily01815@naver.com");
        clientFormDto.setAddress("경기도 고양시 일산동구");

        return Client.createClient(clientFormDto, passwordEncoder);
    }
}