package com.codurance.training.tasks;

import java.util.List;

/**
 * This interface allows the creation of new commands
 *
 */

public interface Command {

	public static String HelpString(){
		return "help method";
	}

	List<Project> execute(String commandLine, List<Project> projects);
	
}
