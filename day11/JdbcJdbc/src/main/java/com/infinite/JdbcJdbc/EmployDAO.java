package com.infinite.JdbcJdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployDAO {

	Connection connection;
	PreparedStatement pst;
	
	public List<Employ> showEmploy() throws ClassNotFoundException, SQLException {
		List<Employ> employList =new ArrayList<Employ>();
		connection= ConnectionHelper.getConnection();
		String cmd="select * from Employ";
		pst=connection.prepareStatement(cmd);
		ResultSet rs =pst.executeQuery();
		Employ employ=null; 
		while(rs.next()){
			employ= new Employ();
			employ.setEmpno(rs.getInt("empno"));
			employ.setName(rs.getString("name"));
			employ.setDept(rs.getString("dept"));
			employ.setDesig(rs.getString("desig"));
			employ.setBasic(rs.getInt("basic"));
			employList.add(employ);
		}
		return employList;
	}
	
	public Employ searchEmploy(int empno) throws ClassNotFoundException, SQLException{
		connection= ConnectionHelper.getConnection();
		String cmd="select * from Employ where empno= ? ";
		pst=connection.prepareStatement(cmd);
		pst.setInt(1, empno);
		ResultSet rs =pst.executeQuery();
		Employ employ=null; 
		if(rs.next()){
			employ= new Employ();
			employ.setEmpno(rs.getInt("empno"));
			employ.setName(rs.getString("name"));
			employ.setDept(rs.getString("dept"));
			employ.setDesig(rs.getString("desig"));
			employ.setBasic(rs.getInt("basic"));
		
		}
		return employ;
		
	}
	 public String addEmploy(Employ employ) throws ClassNotFoundException, SQLException{
		 connection =ConnectionHelper.getConnection();
		 String cmd="insert into Employ(name,dept,desig,basic)" + "values(?,?,?,?)";
		 pst=connection.prepareStatement(cmd);
		 pst.setString(1, employ.getName());
		 pst.setString(2, employ.getDept());
		 pst.setString(3, employ.getDesig());
		 pst.setInt(4, employ.getBasic());
		 pst.executeUpdate();
		 return "record inserted..";
		  
	 }
	 
	 public String deleteEmploy(int empno) throws ClassNotFoundException, SQLException{
		 Employ employ =searchEmploy(empno);
		 if(employ!=null){
			 connection =ConnectionHelper.getConnection();
			 String cmd="delete from Employ where empno=?";
			 pst=connection.prepareStatement(cmd);
			 pst.setInt(1,empno);
			 pst.executeUpdate();
			 return "record Deleted..";
		 }
		 return "Employ Record Not found";
		 }
		 
	 public String updateEmploy(Employ employnew) throws ClassNotFoundException, SQLException{
		 Employ employ=searchEmploy(employnew.getEmpno());
		 if (employ!=null){
			 String cmd="update Employ set name=?, dept=?, desig=?," + "basic=? where empno=?";
			 pst=connection.prepareStatement(cmd);
			 pst.setString(1, employnew.getName());
			 pst.setString(2, employnew.getDept());
			 pst.setString(3, employnew.getDesig());
			 pst.setInt(4, employnew.getBasic());
			 pst.setInt(5, employnew.getEmpno());
			 pst.executeUpdate();
			 return "record inserted..";
		 }
		 return "record not found";
	 }
}
