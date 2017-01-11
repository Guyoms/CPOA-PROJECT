package com.codurance.training.tasks;

import java.util.List;

public class Delete implements Command {

	public List<Project> execute(String commandLine, List<Project> projects) {
		int id = Integer.parseInt(commandLine);
    	for(int i=0; i<projects.size(); i++){
    		Project project = projects.get(i);
            for (Task task : project.getList()) {
                if (task.getId() == id) {
                    task = null;
                    return projects;
                }
            }
        }
        System.out.printf("Could not find a task with an ID of %d.", id);
        return projects;
		
	}

}
