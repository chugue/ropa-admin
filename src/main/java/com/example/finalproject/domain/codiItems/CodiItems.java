package com.example.finalproject.domain.codiItems;

import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.items.Items;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "codi_items_tb")
@Data
public class CodiItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "items_id", nullable = false)
    private Items items; // 아이템 고유번호

    @ManyToOne
    @JoinColumn(name = "codi_id", nullable = false)
    private Codi codi; // 코디 고유번호

    @Builder
    public CodiItems(Integer id, Items items, Codi codi) {
        this.id = id;
        this.items = items;
        this.codi = codi;
    }
}
