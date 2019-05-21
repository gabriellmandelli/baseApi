package org.start.baseApi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIdentityInfo(scope = Sale.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sale  implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @Type(type="uuid-char")
    @Column(unique = true)
    private UUID id;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ManyToOne
    private Salesman salesman;

    @OneToOne
    private Client client;

    @Column
    private double value;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> saleItem;

    public Sale(){

    }

    public Sale(UUID id, Date date, Salesman salesman, List<SaleItem> saleItem) {
        this.id = id;
        this.date = date;
        this.salesman = salesman;
        this.saleItem = saleItem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public List<SaleItem> getSaleItem() {
        return saleItem;
    }

    public void setSaleItem(List<SaleItem>saleItem) {
        this.saleItem = saleItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
