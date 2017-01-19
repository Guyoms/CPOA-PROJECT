package com.codurance.training.tasks;


import java.util.ArrayList;
import java.util.List;

public class Project {

	String nom;
	List<Task> list;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Project(String name) {
		nom = name;
		list = new ArrayList<Task>();
	}

	/**
	 * 
	 * @return nom 
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * 
	 * @return list
	 */
	public List<Task> getList() {
		return list;
	}

}

