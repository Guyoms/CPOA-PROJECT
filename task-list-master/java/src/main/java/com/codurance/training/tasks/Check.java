package com.codurance.training.tasks;

import java.util.List;


/**
 * Check the task with the id as "Done"
 *
 */

public class Check implements Command {

	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		
		
		 //Split the command
		 String[] commandRest = commandLine.split(" ", 2);
		 
		 //take the "yes/no" of the commandLine 
	     String choix = commandRest[0];
		
	    if (choix.equals("yes")){    
	    	setDone(commandRest[1], true, projects);
	    }else if(choix.equals("no")){
	    	setDone(commandRest[1], false, projects);
	    }
		return projects;
	}
	
    /**
     * Set a task to "done" or "to Do"
     * @param idString
     * @param done
     */
    private void setDone(String idString, boolean done, List<Project> projects) {
        int id = Integer.parseInt(idString);
        for(int i=0; i<projects.size(); i++){
        	Project project = projects.get(i);
            for (Task task : project.getList()) {
                if (task.getId() == id) {
                	//Calls the setDone method 
                    task.setDone(done);
                    return;
                }
            }
        }
        System.out.printf("Could not find a task with an ID of %d.", id);
        System.out.println();
    }
    
    
    
	public static String HelpString() {        
		String retour = "  check yes/no <task ID> :\n"
				+ "\t-set the task with the ID <Task ID> as Done or To Do";
		return retour;
	}
}
