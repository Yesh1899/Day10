package com.infinite.JsfJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean

@SessionScoped



public class EmployDAO {
     Connection connection;
     PreparedStatement ps;
     
     public String addEmploy(Employ employ) throws ClassNotFoundException, SQLException{
    	 connection =ConnectionHelper.getConnection();
    	 String sql="insert into Employ(name,dept,desig,basic)"+"values(?,?,?,?)";
    	 ps=connection.prepareStatement(sql);
    	 ps.setString(1,employ.getName());
    	 ps.setString(2,employ.getDept());
    	 ps.setString(3,employ.getDesig());
    	 ps.setInt(4, employ.getBasic());
    	 ps.executeUpdate();
    	// return "Record inserted....";
    	 return "EmployShow.xhtml?faces-redirect=true";
    	 
     }
      public String updateEmploy(Employ employNew) throws ClassNotFoundException, SQLException{
    	
    	  Employ employ=searchEmploy(employNew.getEmpno());
    		  connection=ConnectionHelper.getConnection();
        	  String sql="update employ set Name=?, Dept=? ,Desig=?, Basic=? where empno=?";
        	  ps=connection.prepareStatement(sql);
        	  ps.setString(1, employNew.getName());
        	  ps.setString(2, employNew.getDept());
        	  ps.setString(3, employNew.getDesig());
        	  ps.setInt(4, employNew.getBasic());
        	  ps.setInt(5, employNew.getEmpno());
        	  ps.executeUpdate();
        	  return "Record updated";
    	  
      }
     public String deleteEmploy(int empno) throws SQLException, ClassNotFoundException{
    	Employ employ=searchEmploy(empno);
     connection=ConnectionHelper.getConnection();
    	 String sql="delete from employ where empno=?";
    	 ps=connection.prepareStatement(sql);
    	 ps.setInt(1, empno);
    	ps.executeUpdate();
		return "Employ Record not found";	
     }
     
       public Employ searchEmploy(int empno) throws ClassNotFoundException, SQLException{
    	   Map<String,Object> sessionMap= FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    	 
    	   connection=ConnectionHelper.getConnection();
    	   String sql="select * from Employ where empno=?";
    	   ps=connection.prepareStatement(sql);
    	   ps.setInt(1, empno);
    	   ResultSet rs=ps.executeQuery();
    	   Employ employ=null;
    	   if(rs.next()){
    		   employ=new Employ();
    			employ.setEmpno(rs.getInt("empno"));	 
    		   employ.setName(rs.getString("name"));
    		   employ.setDept(rs.getString("dept"));
    		   employ.setDesig(rs.getString("desig"));
    		   employ.setBasic(rs.getInt("basic"));
    	   }
		return employ;
    	   
       }
     public List<Employ> showEmploy() throws ClassNotFoundException, SQLException{
    	 List<Employ> employList=new ArrayList<Employ>();
    	 connection=ConnectionHelper.getConnection();
    	 String sql="select * from Employ";
    	 ps=connection.prepareStatement(sql);
    	 ResultSet rs=ps.executeQuery();
    	 Employ employ=null;
    	 while(rs.next()){
    		 employ=new Employ();
    		 employ.setEmpno(rs.getInt("empno"));
    		 employ.setName(rs.getString("name"));
    		 employ.setDept(rs.getString("dept"));
    		 employ.setDesig(rs.getString("desig"));
    		 employ.setBasic(rs.getInt("basic"));
    		 employList.add(employ);
    	 }
    	 return employList;
     }
}
