package org.start.baseApi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIdentityInfo(scope = Product.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @Type(type="uuid-char")
    @Column(unique = true)
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double value;

    @Column
    private Double valueForSell;

    @ManyToOne
    private Manager manager;

    public Product(){

    }

    public Product(UUID id, String name, String description, Double value, Double valueForSell, Manager manager) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.valueForSell = valueForSell;
        this.manager = manager;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValueForSell() {
        return valueForSell;
    }

    public void setValueForSell(Double valueForSell) {
        this.valueForSell = valueForSell;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
