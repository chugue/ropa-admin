package com.example.finalproject.codi;


import com.example.finalproject.domain.codi.CodiRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CodiRepositoryTest {
    @Autowired
    private CodiRepository codiRepository;
}
