package com.codurance.training.tasks;


import java.util.ArrayList;
import java.util.List;

public class Project {

	String nom;
	List<Task> list;
	
	
	public Project(String name) {
		nom = name;
		list = new ArrayList<Task>();
	}


	public String getNom() {
		return nom;
	}


	public List<Task> getList() {
		return list;
	}

}

