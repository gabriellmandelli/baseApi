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
@JsonIdentityInfo(scope = Salesman.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Salesman implements Serializable {

	private static final Long serialVersionUID = 1L;
	
	@Id
	@Type(type="uuid-char")
	private UUID id;

	@Column
	private String name;

	@Column(unique = true)
	private String login;

	@Column
	private String password;

	@Column
	private String phone;

	@Column
	private String email;

	@Column
	private int userType;
	
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expireAt;

	@Column
	private int status;

	@OneToMany(mappedBy = "salesman", cascade = CascadeType.ALL)
	private List<Client> client;

	@ManyToOne
	private Manager manager;

	public Salesman() {
		this.createdAt = new Date();
	}

	public Salesman(UUID id, String name, String login, String password, String phone, String email, int userType, Date createdAt, Date expireAt, int status, List<Client> client) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.userType = userType;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
		this.status = status;
		this.client = client;
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

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
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
	public List<Client> getClient() {
		return client;
	}

	public void setClient(List<Client> client) {
		this.client = client;
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
		Salesman salesman = (Salesman) o;
		return Objects.equals(id, salesman.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
