package com.blog.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;



@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	private String email;
	
	@CreationTimestamp
	private Date fechaAlta;

	private String ciudad;
	
	
	@ColumnTransformer(write=" MD5(?) ")
	private String password;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Comment> comments = new HashSet<>();
	
	@OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
	private Set<Post> posts = new HashSet<>();
	
	

	public User() {
		
	}
	
	public User(String email, String nombre, String ciudad, String password) {
		this.email = email;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.password = password;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getFechaAlta() {
		return fechaAlta;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Set<Post> getPosts() {
		return posts;
	}

	public Set<Comment> getComments() {
		return comments;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + nombre + ", email=" + email + ", fechaAlta=" + fechaAlta + ", ciudad="
				+ ciudad + ", password=" + password + "]";
	}
	
	
	
	
		
}
