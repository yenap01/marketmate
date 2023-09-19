package com.shop.shop.controller;

import com.shop.shop.dto.ClientFormDto;
import com.shop.shop.entity.Client;
import com.shop.shop.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ClientControllerTest {

    @Autowired
    ClientService clientService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Client createClient() {
        ClientFormDto clientFormDto = new ClientFormDto();


        clientFormDto.setUser("ye01");
        clientFormDto.setPassword("1234");
        clientFormDto.setUserName("박예나");
        clientFormDto.setEmail("emily01815@naver.com");
        clientFormDto.setAddress("경기도 고양시 일산동구");

        return Client.createClient(clientFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception {
        // given
        String User = "ye0815";

        String Password = "1234";

        // when
        mockMvc.perform(
                formLogin()
                        .userParameter("User")
                        .loginProcessingUrl("/clients/login")
                        .user(User)
                        .password(Password)
                ).andExpect(SecurityMockMvcResultMatchers.authenticated());

    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception {
        // given
        String User = "ye0815";

        String Password = "1234";

        // when
        mockMvc.perform(
                formLogin()
                        .userParameter("User")
                        .loginProcessingUrl("/clients/login")
                        .user(User)
                        .password("12345")
                )
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());


    }

}