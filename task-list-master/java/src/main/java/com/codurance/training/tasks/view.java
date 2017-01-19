package com.codurance.training.tasks;

import java.util.List;

/**
 * Shows the tasks' list of each project 
 */
public class view implements Command {

	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		
		switch(commandLine){
			case "by project":
				for(int i=0; i<projects.size(); i++){
		        	Project project = projects.get(i);
		        	System.out.println(project.getNom());
		            for (Task task : project.getList()) {
		            	System.out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
		            }
		            System.out.println();
		        }
				break;
			/*case "by date":
				System.out.println("TODO DATE");
				break;*/
			case "by dead line":
				System.out.println("TODO DEADLINE");
				break;
		}
		return projects;
	}

	
	public static String HelpString() {
       return 	"  view by :\n"
        		+ "\t-project : shows the tasks' list of each project\n"
        		+ "\t-date : shows the tasks' list by date\n"
        		+ "\t-dead line : shows the tasks' list of each project\n"
        		;
	}

}
