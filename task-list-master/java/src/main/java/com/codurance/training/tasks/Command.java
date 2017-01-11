package com.codurance.training.tasks;

import java.util.List;

public interface Command {

	@Override
	String toString();

	List<Project> execute(String commandLine, List<Project> projects);
	
}
