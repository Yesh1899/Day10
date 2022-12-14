package com.infinite.Lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaveDetailsDAO {

	Connection connection;
	PreparedStatement pst;
	
	public String approveDeny(int leaveId, int mgrId, String managerComments, String status) throws ClassNotFoundException, SQLException {
		LeaveDetails ld = searchByLeaveId(leaveId);
		connection = ConnectionHelper.getConnection();
		Employee emp = new EmployeeDAO().searchEmploy(ld.getEmpId());
		int empId = emp.getEmpId();
		int mgId = emp.getMgrId();
		int noOfDays = ld.getNoOfDays();
		if (mgrId==mgId) {
			if (status.toUpperCase().equals("YES")) {
				String cmd = "Update leave_history SET LEAVE_STATUS='APPROVED', "
						+ "LEAVE_MNGR_COMMENTS=? where LEAVE_ID=?";
				pst = connection.prepareStatement(cmd);
				pst.setString(1, managerComments);
				pst.setInt(2, leaveId);
				pst.executeUpdate();
				return "Your Leave Approved...";
			} else {
				String cmd = "Update leave_history SET LEAVE_STATUS='DENIED', "
						+ "LEAVE_MNGR_COMMENTS=? where LEAVE_ID=?";
				pst = connection.prepareStatement(cmd);
				pst.setString(1, managerComments);
				pst.setInt(2, leaveId);
				pst.executeUpdate();
				cmd = "UPDATE EMPLOYEE SET EMP_AVAIL_LEAVE_BAL=EMP_AVAIL_LEAVE_BAL+? "
						+ " WHERE EMP_ID=?";
				pst = connection.prepareStatement(cmd);
				pst.setInt(1, noOfDays);
				pst.setInt(2, empId);
				pst.executeUpdate();
				return "Your Leave not Approved...";
			}
		} else {
			return "You are Leave Approved...";
		}
	}
	public LeaveDetails searchByLeaveId(int leaveId) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from leave_history where LEAVE_ID=?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, leaveId);
		ResultSet rs = pst.executeQuery();
		LeaveDetails leaveDetails = null;
		if (rs.next()) {
			leaveDetails = new LeaveDetails();
			leaveDetails.setLeaveId(rs.getInt("LEAVE_ID"));
			leaveDetails.setEmpId(rs.getInt("EMP_ID"));
			leaveDetails.setNoOfDays(rs.getInt("LEAVE_NO_OF_DAYS"));
			leaveDetails.setLeaveStartDate(rs.getDate("LEAVE_START_DATE"));
			leaveDetails.setLeaveEndDate(rs.getDate("LEAVE_END_DATE"));
			leaveDetails.setLeaveType(LeaveType.valueOf(rs.getString("LEAVE_TYPE")));
			leaveDetails.setLeaveStatus(LeaveStatus.valueOf(rs.getString("LEAVE_STATUS")));
			leaveDetails.setLeaveReason(rs.getString("LEAVE_REASON"));
			
		}
		return leaveDetails;
	}
	
	public String applyLeave(LeaveDetails leaveDetails) throws ClassNotFoundException, SQLException {
		/*
		 * 1. LeaveStartDate cannot be greater than leave End Date
		 * 2. Balance must be available
		 */
		 long ms = leaveDetails.getLeaveEndDate().getTime() - leaveDetails.getLeaveStartDate().getTime();
		    long m = ms / (1000 * 24 * 60 * 60);
		    int days = (int) m;
		    days = days + 1;
		    leaveDetails.setLeaveType(LeaveType.EL);
		    leaveDetails.setLeaveStatus(LeaveStatus.PENDING);
		 System.out.println("difference is  " +days);
		 EmployeeDAO edao = new EmployeeDAO();
		 Employee employee = edao.searchEmploy(leaveDetails.getEmpId());
		 if (employee!=null) {
			 int leaveAvail = employee.getLeaveAvail();
			 if (days < 0) {
				 return "Leave Start-Date Cannot be greater than Leave-End Date...";
			 } else if (leaveAvail-days < 0) {
				 return "Insufficient Leave Balance...";
			 } else {
				 	int diff = leaveAvail-days;
			    	leaveDetails.setNoOfDays(days);
			    	connection = ConnectionHelper.getConnection();
			    	String cmd = "Insert into LEAVE_HISTORY(Emp_ID,Leave_Start_Date, "
			    			+ "Leave_End_Date,Leave_Type,Leave_Status,Leave_Reason,LEAVE_NO_OF_DAYS) "
			    			+ " VALUES(?,?,?,?,?,?,?)";
			    	pst = connection.prepareStatement(cmd);
			    	pst.setInt(1, leaveDetails.getEmpId());
			    	pst.setDate(2, leaveDetails.getLeaveStartDate());
			    	pst.setDate(3, leaveDetails.getLeaveEndDate());
			    	pst.setString(4, leaveDetails.getLeaveType().toString());
			    	pst.setString(5, leaveDetails.getLeaveStatus().toString());
			    	pst.setString(6, leaveDetails.getLeaveReason());
			    	pst.setInt(7, leaveDetails.getNoOfDays()); 
			    	pst.executeUpdate();
			    	cmd = "Update Employee set EMP_AVAIL_LEAVE_BAL=EMP_AVAIL_LEAVE_BAL-? WHERE EMP_ID=?";
			    	pst = connection.prepareStatement(cmd);
			    	pst.setInt(1, days);
			    	pst.setInt(2, leaveDetails.getEmpId());
			    	pst.executeUpdate();
			    	return "Leave Applied Successfully...";
			 }
		 } else {
			 return "Invalid Employee Id...";
		 }
	}
}