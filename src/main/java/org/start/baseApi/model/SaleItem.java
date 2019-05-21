package org.start.baseApi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIdentityInfo(scope = SaleItem.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SaleItem implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @Type(type="uuid-char")
    @Column(unique = true)
    private UUID id;

    @Column
    private int amount;

    @Column
    private Double value;

    @ManyToOne
    private Sale sale;

    @OneToOne
    private Product product;

    public SaleItem(){

    }

    public SaleItem(UUID id, int amount, Double value, Sale sale, Product product) {
        this.id = id;
        this.amount = amount;
        this.value = value;
        this.sale = sale;
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Objects.equals(id, saleItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
