package com.codurance.training.tasks;

import java.util.List;

/*
 * Displays commands' list
 */
public class Help implements Command {
	
	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		System.out.println(Add.HelpString()+"\n"
						+view.HelpString()+"\n"
						+Check.HelpString()+"\n"
						+Deadline.HelpString()+"\n"
						+Today.HelpString()+"\n"
						+Delete.HelpString()+"\n"
						+Help.HelpString()+"\n"
						+"  quit :\n"
					    +"\t-close the application");
		return projects;
	}
	
	public static String HelpString() {
		String retour = "  help : \n\t-displays the commands' list";
		return retour;
	}

}
