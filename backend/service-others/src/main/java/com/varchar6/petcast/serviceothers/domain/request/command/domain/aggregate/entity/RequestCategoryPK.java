package com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class RequestCategoryPK implements Serializable {

    private int categoryId;
    private int requestId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
