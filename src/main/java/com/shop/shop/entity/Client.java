package com.shop.shop.entity;

import com.shop.shop.constant.Role;
import com.shop.shop.dto.ClientFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Entity
public class Client extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Num")
    private Long User_Num;

    private String User;

    private String Password;

    private String UserName;

    private String BirthDate;

    @Column(unique = true)
    private String Email;

    private String Address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Client createClient(ClientFormDto clientFormDto, PasswordEncoder passwordEncoder) {
        Client client = new Client();

        client.setUser(clientFormDto.getUser());
        String password = passwordEncoder.encode(clientFormDto.getPassword());
        client.setPassword(password);
        client.setUserName(clientFormDto.getUserName());
        client.setBirthDate(clientFormDto.getBirthDate());
        client.setEmail(clientFormDto.getEmail());
        client.setAddress(clientFormDto.getAddress());
        client.setRole(Role.USER);
        client.setRole(Role.ADMIN);

        return client;
    }
}
