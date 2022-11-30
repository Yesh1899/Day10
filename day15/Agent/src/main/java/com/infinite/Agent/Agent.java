package com.infinite.Agent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class Agent {
       private int agentId;
       private String name;
       private String city;
       private Gender gender;
       private int maritalStatus;
       private double primium;
       
       
	@Override
	public String toString() {
		return "Agent [agentId=" + agentId + ", name=" + name + ", city=" + city + ", gender=" + gender
				+ ", MaritalStatus=" + maritalStatus + ", primium=" + primium + "]";
	}


	public Agent(int agentId, String name, String city, Gender gender, int maritalStatus, double primium) {
		super();
		this.agentId = agentId;
		this.name = name;
		this.city = city;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.primium = primium;
	}


	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getAgentId() {
		return agentId;
	}


	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public int getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(int maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public double getPrimium() {
		return primium;
	}


	public void setPrimium(double primium) {
		this.primium = primium;
	}
       
       
}
