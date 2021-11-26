package br.com.wellmmjr.data.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person") //utilizado para distinguir a inicial minuscula ao hibernate no banco
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // hibernate será responsavel por gerar os ids
	private long id;
	
	@Column(name="first_name", nullable = false, length = 20)
	private String firstName;
	
	@Column(name="second_name", length = 50)
	private String secondName;
	
	@Column(length = 100)
	private String address;
	
	@Column(length = 20)
	private String gender;
	
	public Person() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, firstName, gender, id, secondName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && id == other.id
				&& Objects.equals(secondName, other.secondName);
	}
	
	
	
	
}
