package org.springproject.data.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Indexed;

import java.time.Instant;

@Entity
@Table(indexes = @Index(name = "userid_expcatId", columnList = "userId,expenseCatId",unique = true))
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long expenseCatId;

    @Column
    String expCat;
    @Column
    Long userId;
    @Column
    @CreationTimestamp
    Instant createdAt;

    @Column
    @UpdateTimestamp
    Instant modifiedAt;


}
