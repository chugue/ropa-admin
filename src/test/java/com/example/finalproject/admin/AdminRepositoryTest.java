package com.example.finalproject.admin;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void findByEmailAndPassword_test() {
        // given
        String email = "nike@naver.com";
        String password = "1234";
        // when
        Optional<Admin> adminOP = adminRepository.findByEmailAndPassword(email,password);

        // then
        if (adminOP.isPresent()) {
            Admin admin = adminOP.get();
            System.out.println("여기있네 : " + admin.getEmail());
            System.out.println("여기있네 : " + admin.getPassword());
        }
    }
}
