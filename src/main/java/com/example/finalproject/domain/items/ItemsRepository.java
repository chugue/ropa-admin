package com.example.finalproject.domain.items;

import com.example.finalproject.domain.orderHistory.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemsRepository extends JpaRepository<Items, Integer> {


}
