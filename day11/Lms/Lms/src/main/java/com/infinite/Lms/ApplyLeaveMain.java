package com.infinite.Lms;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ApplyLeaveMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LeaveDetails leaveDetails = new LeaveDetails();
		System.out.println("Enter Employee Id  ");
		leaveDetails.setEmpId(sc.nextInt());
		System.out.println("Enter Leave Start Date (yyyy-MM-dd)   ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date lstartDate = sdf.parse(sc.next());
			java.sql.Date startDate = new java.sql.Date(lstartDate.getTime());
			leaveDetails.setLeaveStartDate(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter Leave End Date (yyyy-MM-dd)  ");
		try {
			Date lendDate = sdf.parse(sc.next());
			java.sql.Date endDate = new java.sql.Date(lendDate.getTime());
			leaveDetails.setLeaveEndDate(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter Reason   ");
		leaveDetails.setLeaveReason(sc.next());
		LeaveDetailsDAO dao = new LeaveDetailsDAO();
		try {
			System.out.println(dao.applyLeave(leaveDetails));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}