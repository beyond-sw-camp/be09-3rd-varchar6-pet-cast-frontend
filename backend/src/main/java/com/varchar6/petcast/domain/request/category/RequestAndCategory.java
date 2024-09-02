package com.varchar6.petcast.domain.request.category;

import jakarta.persistence.*;

@Entity(name = "request_and_category")
@Table(name = "tbl_request")
public class RequestAndCategory {

    @Id
    @Column(name = "request_id")
    private int request_id;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    public RequestAndCategory() {
    }

    public RequestAndCategory(int request_id, Category category) {
        this.request_id = request_id;
        this.category = category;
    }

    public int getRequest_id() {
        return request_id;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "RequestAndCategory{" +
                "request_id=" + request_id +
                ", category=" + category +
                '}';
    }
}
