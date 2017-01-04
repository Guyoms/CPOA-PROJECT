package com.codurance.training.tasks;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Project implements Observer {

	String nom;
	List<Task> list;
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

