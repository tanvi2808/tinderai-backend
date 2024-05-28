package org.springproject.data.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(indexes = @Index(name = "userid_expcatId", columnList = "userId,expenseCatId",unique = true))
@Builder
@Data
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
