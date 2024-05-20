package com.example.finalproject.domain.codi;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class CodiNativeRepository {
    @PersistenceContext
    private EntityManager em;
    public List<Codi> findItemsByCodiDescription(String keyword) {
        String sql = "SELECT c.* " +
                "FROM codi_tb c where c.description LIKE ?";

        Query query = em.createNativeQuery(sql, Codi.class);
        query.setParameter('1', keyword);

        return query.getResultList();
    }
}