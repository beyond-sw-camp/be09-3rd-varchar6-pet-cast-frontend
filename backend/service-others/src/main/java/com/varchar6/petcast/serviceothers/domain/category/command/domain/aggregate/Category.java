package com.varchar6.petcast.serviceothers.domain.category.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tbl_catgory")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;
}
