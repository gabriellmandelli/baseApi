package org.start.baseApi.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIdentityInfo(scope = Client.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @Type(type="uuid-char")
    @Column(unique = true)
    private UUID id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private int status;

    @ManyToOne
    private Salesman salesman;

    public  Client(){

    }

    public Client(UUID id, String name, String phone, String email, int status, Salesman salesman) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.salesman = salesman;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
