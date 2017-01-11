package com.codurance.training.tasks;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Project implements Observer {

	String nom;
	List<Task> list;
	
	
	public Project(String name) {
		nom = name;
		list = new ArrayList<Task>();
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}


	public String getNom() {
		return nom;
	}


	public List<Task> getList() {
		return list;
	}

}

