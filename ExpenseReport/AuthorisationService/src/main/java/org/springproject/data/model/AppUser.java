package org.springproject.data.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Indexed;

import javax.annotation.processing.Generated;
import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(indexes = {@Index(name="IDX_EMAIL", columnList = "email")})
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String username;

    @Column
    String email;

    @Column
    String password;

    @CreationTimestamp
    Instant creationAt;

    @UpdateTimestamp
    Instant modifiedAt;
}
