package com.codurance.training.tasks;

import java.util.List;

/*
 * add a deadline to a task
 */
public class Deadline implements Command {

	public static String HelpString() {
		return  "  deadline <task ID> <dd/MM/yy> :\n"
        		+ "\t-set a deadline to a task";
	}

	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		
	 //Split the command     	
   	 String[] subcommandRest = commandLine.split(" ", 2);     
   	 
   	 //Cast the String to an integer
        int id = Integer.parseInt(subcommandRest[0]);
        
        for(int i=0; i<projects.size(); i++){
        	Project project = projects.get(i);
           for (Task task : project.getList()) {
                if (task.getId() == id) {
                    task.setDeadline(subcommandRest[1]);
                    return projects;
                }
            }
        }
		
		
		
		return projects;
	}

}
