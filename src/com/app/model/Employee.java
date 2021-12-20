package com.app.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@NamedQueries({ @NamedQuery(name = "getAllEmp", query = "from Employee"),
		@NamedQuery(name = "getAllEmpMC", query = "from Employee where name like :name"),
		@NamedQuery(name = "getEmpCountPerDep", query = "Select dep.name, count(emp.name) from Departement dep left join Employee emp on dep.id_dep = emp.dep.id_dep group by dep.name"),
		@NamedQuery(name = "getEmpInfoPerDep", query = "Select dep.name, emp.name, emp.start_date from Departement dep inner join Employee emp on dep.id_dep = emp.dep.id_dep where dep.name = :name"),
		@NamedQuery(name = "deleteEmps", query = "Delete from Employee e where e.dep.id_dep in (select id_dep from Departement where name = :name)"),
		@NamedQuery(name = "udateEmps", query = "update Employee emp set emp.end_date = current_date where emp.dep.id_dep in (select dep.id_dep from Departement dep where dep.name =:name)"), })
public class Employee implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private Double salary;
	@Column
	private LocalDate start_date;
	@Column
	private LocalDate end_date;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dep")
	private Departement dep;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", dep=" + dep + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public Departement getDep() {
		return dep;
	}

	public void setDep(Departement dep) {
		this.dep = dep;
	}

}
