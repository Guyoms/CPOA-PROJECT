package com.codurance.training.tasks;

import java.util.List;

/**
 * 
 * Allow to delete tasks
 *
 */
public class Delete implements Command {

	public List<Project> execute(String commandLine, List<Project> projects) {
		
		//cast the string in int
		int id = Integer.parseInt(commandLine);
    	for(int i=0; i<projects.size(); i++){
    		Project project = projects.get(i);
            for (Task task : project.getList()) {
                if (task.getId() == id) {
                	project.getList().remove(task);
                    return projects;
                }
            }
        }
        System.out.printf("Could not find a task with an ID of %d.", id);
        return projects;
		
	}

	public static String HelpString() {
		String retour = "  delete <task ID> :\n" + "\t-delete the task with the ID <task ID>";
		return retour;
	}

}
