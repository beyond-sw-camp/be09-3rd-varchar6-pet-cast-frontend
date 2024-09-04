package com.varchar6.petcast.domain.event.command.domain.aggregate;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCategoryId implements Serializable {
    private int categoryId;
    private int eventId;
}
