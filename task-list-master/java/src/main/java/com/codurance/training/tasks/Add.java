package com.codurance.training.tasks;

import java.util.List;

/**
 * 
 *  This class manages the creation of new tasks or projects
 *
 */
public class Add implements Command {

	private long lastId = 0;
	
	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		
		// Split the command
		
		String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        
        // if the parameter is project
        if (subcommand.equals("project")) {
        	String name = subcommandRest[1];
        	/*
        	 * Create a new project named "name" with an empty list of tasks
        	 */
        	projects.add(new Project(name));
        	
        // if the parameter is task
        } else if (subcommand.equals("task")) {
        	/*
        	 * Add a task to the tasks' list of a project with its description
        	 */
            String[] projectTask = subcommandRest[1].split(" ", 2);
            List<Task> projectTasks = null;
            for(int i=0; i<projects.size();i++){
        		if (projects.get(i).getNom().equals(projectTask[0])){
        			projectTasks = projects.get(i).getList();
        		}
        	}
            
            // if the project is not found
            if (projectTasks == null) {
                System.out.printf("Could not find a project with the name \"%s\".", projectTask[0]);
                System.out.println();
                return projects;
            }
            
            // Add a new task in the project
            projectTasks.add(new Task(nextId(), projectTask[1], false));
        }
        return projects;
	}
	
	/**
	 * 
	 * @return lastId
	 */
	private long nextId() {
        return ++lastId;
    }
	
	public static String HelpString() {
		
		String retour = "  add project <project name> :\n"
						+"\t-create a new project named <project name> with an empty list of tasks\n"
						+ "  add task <project name> <task description> :\n"
						+"\t-add a task to the tasks' list of the project <project name> with a description";
		return retour;
	}

}
