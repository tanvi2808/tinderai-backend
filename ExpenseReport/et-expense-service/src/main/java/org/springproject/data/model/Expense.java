package org.springproject.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long expenseId;

    @Column
    Long userId;

    @Column
    String expenseTitle;

    @Column
    Double amount;

    @ManyToOne
    @JoinColumn(name="expenseCatId", referencedColumnName = "expenseCatId")
    ExpenseCategory expenseCat;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant modifiedAt;


}
