package com.varchar6.petcast.domain.gather.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_gather")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Gather {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "count", nullable = false)
    private int number;

    @Column(name = "url")
    private String url;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "active_yn", nullable = false)
    private boolean active;

    @Column(name = "invitation_id", nullable = false)
    private String invitationId;

    @Column(name = "invitation_content")
    private String invitationContent;
}
