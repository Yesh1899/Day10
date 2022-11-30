package com.Infinite.Example1;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class SumBean implements Serializable{
	private int firstNumber;
	private int secondNumber;
	
	
   public int getFirstNumber() {
		return firstNumber;
	}


	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}


	public int getSecondNumber() {
		return secondNumber;
	}


	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}


public int show(int a,int b){
	   return a+b;
   }
}
