package com.app.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.model.Departement;
import com.app.model.Employee;
import com.hibernate.utils.HibernateUtils;

public class Main {

	public static void main(String[] args) {

		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();

		System.out.println("1- Tous les departement");

		List<Departement> deps = s.getNamedQuery("getAllDep").getResultList();
		for (Departement dd : deps) {
			System.out.println(dd.toString());
		}

		System.out.println("2- Tous les employes");

		List<Employee> empls = s.getNamedQuery("getAllEmp").getResultList();
		for (Employee dd : empls) {
			System.out.println(dd.toString());
		}

		String d = "A";
		System.out.println("3- Tous les departement de departement " + d);

		List<Departement> deps1 = s.getNamedQuery("getAllDepMC").setParameter("name", "%" + d + "%").getResultList();
		for (Departement dd1 : deps1) {
			System.out.println(dd1.toString());
		}

		System.out.println("4- Nombre employes par departement" + d);

		List<Object[]> empPerDep = s.getNamedQuery("getEmpCountPerDep").getResultList();

		for (Object[] oo : empPerDep) {
			System.out.println(oo[0] + "--->" + oo[1]);
		}

		System.out.println("4- start_date, first_name employes par departement Administration" + d);

		List<Object[]> getEmpInfoPerDep = s.getNamedQuery("getEmpInfoPerDep").setParameter("name", d).getResultList();

		for (Object[] ooo : getEmpInfoPerDep) {
			System.out.println("Departement: " + ooo[0] + " --- Employee: " + ooo[1] + " --- Start Date: " + ooo[2]);
		}

		System.out.println("4- Supprimer tous les employs");

		if (s.getNamedQuery("deleteEmps").setParameter("name", "A").executeUpdate() != -1)
			System.out.println("Delete Successfully!");
		else
			System.out.println("failed to delete...!");

		System.out.println("5- Inserer une nouvelle deparement");

		Departement dep = new Departement(2, "D");
		s.save(dep);

		System.out.println("6- Mettre a jour tous les employs");

		if (s.getNamedQuery("updateEmps").setParameter("name", "A").executeUpdate() != -1)
			System.out.println("Update Successfully!");
		else
			System.out.println("failed to Update!");

		s.close();
		t.commit();

	}

}
