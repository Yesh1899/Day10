package com.infinite.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class AgentDAO {
	Connection con;
	PreparedStatement ps;
	   
	public String updateAgent(Agent agentNew) throws ClassNotFoundException, SQLException{
			con=ConnectionHelper.getConnection();
			String sql="update agent set Name=?,City=?,GENDER=?,MaritalStatus=?,Premium=? where agentId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, agentNew.getName());
			ps.setString(2, agentNew.getCity());
			ps.setString(3, agentNew.getGender().toString());
		    ps.setInt(4, agentNew.getMaritalStatus());
		    ps.setDouble(5, agentNew.getPrimium());
		    ps.setInt(6, agentNew.getAgentId());
			ps.executeUpdate();
			return "AgentShow.xhtml?faces-redirect=true";
	}
	public String deleteAgent(int agentId) throws ClassNotFoundException, SQLException{
			con=ConnectionHelper.getConnection();
			String sql="delete from agent where agentId=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, agentId);
			ps.executeUpdate();
			return "record deleted";
					
	}
	public String searchAgent(int agentId) throws ClassNotFoundException, SQLException{
		Map<String,Object>sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		con=ConnectionHelper.getConnection();
		String sql="select * from agent where agentId=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, agentId);
		ResultSet rs=ps.executeQuery();
		Agent agent=null;
		if(rs.next()){
			agent=new Agent();
			agent.setAgentId(rs.getInt("AgentId"));
 		   agent.setName(rs.getString("Name"));
 		   agent.setCity(rs.getString("City")); 
 		  if(rs.getString("GENDER").toUpperCase().equals("MALE")){
				agent.setGender(Gender.MALE);
			}
			if(rs.getString("GENDER").toUpperCase().equals("FEMALE")){
				agent.setGender(Gender.FEMALE);
			}
 		   agent.setMaritalStatus(rs.getInt("MaritalStatus"));
 		   agent.setPrimium(rs.getDouble("Premium"));
 		  sessionMap.put("editAgent",agent);
 		  
		}return "/UpdateAgent.xhtml?faces-redirect=true";
	}
	public String addAgent(Agent agent) throws SQLException, ClassNotFoundException{
		con =ConnectionHelper.getConnection();
   	 String sql="insert into agent(Name,City,GENDER,MaritalStatus,Premium)"+"values(?,?,?,?,?)";
   	 ps=con.prepareStatement(sql);
   	ps.setString(1, agent.getName());
   	ps.setString(2, agent.getCity());
   	ps.setString(3, agent.getGender().toString());
   	ps.setInt(4, agent.getMaritalStatus());
   	ps.setDouble(5, agent.getPrimium());
   	 ps.executeUpdate();
   	 return "AgentShow.xhtml?faces-redirect=true";
   	 
	}
       public List<Agent> showAgent() throws ClassNotFoundException, SQLException{
    	   List<Agent> agentList=new ArrayList<Agent>();
    	   con=ConnectionHelper.getConnection();
    	   String sql="select * from agent";
    	   ps=con.prepareStatement(sql);
    	   ResultSet rs=ps.executeQuery();
    	   Agent agent=null;
    	   while(rs.next()){
    		   agent=new Agent();
    		   agent.setAgentId(rs.getInt("AgentId"));
    		   agent.setName(rs.getString("Name"));
    		   agent.setCity(rs.getString("City"));
    		  
    			if(rs.getString("GENDER").toUpperCase().equals("MALE")){
    				agent.setGender(Gender.MALE);
    			}
    			if(rs.getString("GENDER").toUpperCase().equals("FEMALE")){
    				agent.setGender(Gender.FEMALE);
    			}
    		   agent.setMaritalStatus(rs.getInt("MaritalStatus"));
    		   agent.setPrimium(rs.getDouble("Premium"));
    		   agentList.add(agent);
    	   }return agentList;
       }
}
