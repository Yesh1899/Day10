package com.infinite.Lms;

import java.sql.SQLException;

public class EmployeeShowAllMain {

	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		try {
			Employee[] arrEmploy = dao.showEmploy();
			for (Employee employee : arrEmploy) {
				System.out.println(employee);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}