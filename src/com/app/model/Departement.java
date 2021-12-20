package com.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departement")
@NamedQueries({ @NamedQuery(name = "getAllDep", query = "from Departement"),
		@NamedQuery(name = "getAllDepMC", query = "from Departement where name like :name") })
public class Departement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_dep;
	@Column
	private String name;
	@OneToMany(mappedBy = "dep")
	List<Employee> empls = new ArrayList<Employee>();

	public Departement() {

	}

	@Override
	public String toString() {
		return "Departement [id_dep=" + id_dep + ", name=" + name + "]";
	}

	public Departement(int id_dep, String name) {
		super();
		this.id_dep = id_dep;
		this.name = name;
	}

	public int getId_dep() {
		return id_dep;
	}

	public void setId_dep(int id_dep) {
		this.id_dep = id_dep;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmpls() {
		return empls;
	}

	public void setEmpls(List<Employee> empls) {
		this.empls = empls;
	}

}
