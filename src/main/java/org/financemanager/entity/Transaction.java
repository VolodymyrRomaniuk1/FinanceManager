package org.financemanager.entity;

import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Objects;

@Builder

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(generator = "transactions_generator")
    @GenericGenerator(
            name = "transactions_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "transactions_generator", value = "transactions_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;
    @Column(name = "operation_type")
    @Pattern(regexp = "Spending|Gain")
    @NotBlank
    private String operationType;      // allowed: "Spending" OR "Gain"
    @Positive(message = "Sum must be positive")
    private double sum;

    @NotNull(message = "Date must not be empty")
    private Date date;
    private String description;

    public Transaction(){
    }

    public Transaction(Long id, Category category, String operationType, double sum, Date date, String description) {
        this.id = id;
        this.category = category;
        this.operationType = operationType;
        this.sum = sum;
        this.date = date;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.sum, sum) == 0 && Objects.equals(id, that.id) && Objects.equals(category, that.category) && Objects.equals(operationType, that.operationType) && Objects.equals(date, that.date) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, operationType, sum, date, description);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", category=" + category +
                ", operationType=" + operationType +
                ", sum=" + sum +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
