package com.shop.shop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ClientFormDto {

    @NotEmpty(message = "아이디는 필수 값입니다.")
    private String User;

    @NotEmpty(message = "비밀번호는 필수 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자이상 16자 이하로 입력해 주세요")
    private String Password;

    @NotBlank(message = "이름은 필수 값입니다.")
    private String UserName;

    @NotBlank(message = "생년월일을 입력하세요.")
    private String BirthDate;

    @NotEmpty(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식으로 입력해 주세요.")
    private String Email;

    @NotEmpty(message = "주소는 필수 값입니다.")
    private String Address;
}
