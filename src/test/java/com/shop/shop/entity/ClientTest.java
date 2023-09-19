package com.shop.shop.entity;

import com.shop.shop.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;


@SpringBootTest
@Transactional
public class ClientTest {

    @Autowired
    ClientRepository clientRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "gildong", roles = "USER")
    public void auditingTest() {
        Client newClient = new Client();
        clientRepository.save(newClient);

        em.flush();
        em.clear();

        Client client =clientRepository.findById(newClient.getUser_Num()).orElseThrow(EntityNotFoundException::new);

        System.out.println("register time : " + client.getRegTime());
        System.out.println("update time : " + client.getUpdateTime());
        System.out.println("create client : " + client.getCreatedBy());
        System.out.println("modify client : " + client.getModifiedBy());
    }
}