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
        String sql = "SELECT DISTINCT c.* " +
                "FROM codi_tb c " +
                "LEFT JOIN photo_tb p ON c.id = p.codi_id " +
                "WHERE p.is_main_photo = true AND c.description LIKE :keyword";

        Query query = em.createNativeQuery(sql, Codi.class);
        query.setParameter("keyword", keyword);

        return query.getResultList();
    }
}