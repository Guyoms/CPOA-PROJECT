package com.codurance.training.tasks;


import java.util.ArrayList;
import java.util.List;

public class Project extends Task{

	ArrayList<Task> list;
	
	
	public Project(long id, String name) {
		super(id, name, false);
		list = new ArrayList<Task>();
	}

	public ArrayList<Task> getList() {
		return list;
	}
	
	public Task getByNom(String nom){
		for (Task task : this.getList()) {
			if (task.getDescription().equals(nom)){
				return task;
			}
		}
		return null;
	}

	public void setList(ArrayList<Task> list) {
		this.list = list;
	}

	

}

