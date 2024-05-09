package com.example.finalproject.admin;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void findByAdminAndPhoto_test() {
        //given
        Integer adminId = 1;

        // when
        Optional<Admin> admin = adminRepository.findByAdminAndPhoto(adminId);

        // then
        System.out.println("여기: "+admin);
    }

    @Test
    public void findAdminByPhoto_test() {
        // when
        List<Admin> admins = adminRepository.findAdminByPhoto();

        // then
        admins.forEach(System.out::println);
    }

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
