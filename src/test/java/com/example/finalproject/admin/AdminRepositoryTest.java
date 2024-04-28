package com.example.finalproject.admin;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


@DataJpaTest
class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findByEmailAndPassword_test() {
        // given
        String email = "Ropa@naver.com";
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
