package com.codurance.training.tasks;

import java.util.List;

public class Help implements Command {
	
	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		System.out.println(Today.HelpString()+"\n"+Deadline.HelpString()+"\n"+Delete.HelpString()+"\n"+Help.HelpString()+"\n");
		return projects;
	}
	
	public static String HelpString() {
		String retour = "  help : \n\t-displays the commands' list";
		return retour;
	}

}
