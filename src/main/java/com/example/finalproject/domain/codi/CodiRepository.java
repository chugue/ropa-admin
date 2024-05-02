package com.example.finalproject.domain.codi;

import com.example.finalproject.domain.codiItems.CodiItems;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodiRepository extends JpaRepository<Codi, Integer> {



}
