package com.fmb.friendservice.models;

import com.fmb.friendservice.enums.FriendshipStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friendship")
@Data
public class Friendships {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null
    private Long id;

    @NotNull
    @Column(name = "requester_id")
    private Long requesterId;

    @NotNull
    @Column(name = "addressee_id")
    private Long addresseeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FriendshipStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
