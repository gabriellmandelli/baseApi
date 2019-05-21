package org.start.baseApi.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIdentityInfo(scope = Manager.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Manager implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @Type(type="uuid-char")
    @Column(unique = true)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(unique = true, name = "login", length = 20)
    private String login;

    @Column(name = "password", length = 20)
    private String password;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireAt;

    @Column
    private int status;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Salesman> salesman;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Product> product;

    public  Manager(){
        this.createdAt = new Date();
    }

    public Manager(UUID id, String name, String login, String password, String phone, String email, Date createdAt, Date expireAt, int status, List<Salesman> salesman) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.expireAt = expireAt;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public List<Salesman> getSalesman() {
        return salesman;
    }

    public void setSalesman(List<Salesman> salesman) {
        this.salesman = salesman;
    }

    @JsonIdentityReference(alwaysAsId = true)
    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
