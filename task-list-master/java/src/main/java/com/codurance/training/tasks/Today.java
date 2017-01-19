package com.codurance.training.tasks;

import java.util.List;

/**
 * 
 * Display all of the tasks which the deadline is today
 * 
 */
public class Today implements Command {

	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		
		//Collect of the today's date and formatting
		String format = "dd/MM/yy";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format); 
        java.util.Date date = new java.util.Date();
        
        for(int i=0; i<projects.size(); i++){
         	Project project = projects.get(i);
            for (Task task : project.getList()) {
            	//If the deadline is today
                if (task.getDeadline().equals(formater.format(date))) {
                	//Display of the date
                	System.out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                    return projects;
                }
            }
        }
        return projects;
	}

	public static String HelpString() {
		String retour = "  today :\n" + "\t-displays all of the tasks which the deadline is today";
		return retour;
	}

}
