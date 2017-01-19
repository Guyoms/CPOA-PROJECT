package com.codurance.training.tasks;

import java.util.List;

public class Attach implements Command {

	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		
		//Retrieve the arguments (nomProjet and id String)
		String[] subcommandRest = commandLine.split(" ", 2);
		String nomprojet = subcommandRest[0];
        String idString = subcommandRest[1];
        
        // Convert the string (id) to an integer
        int id = Integer.parseInt(idString);
        
        //Run through the projects
		for(int i=0; i<projects.size(); i++){
        	Project project = projects.get(i);
        	
        	//Run through the tasks' of the selected project
            for (Task task : project.getList()) {
            	
            	//If the task has the needed id
                if (task.getId() == id) {
                	Project projectTasks = null;
                	
                	//Seek the project to attach the task
                    for(int r=0; r<projects.size();r++){
                    	
                    	//If the project has the good name, we keep it
                		if (projects.get(r).getNom().equals(nomprojet)){
                			projectTasks = projects.get(r);
                		}
                	}
                    
                    if (projectTasks == null) {
                        System.out.printf("Could not find a project with the name \"%s\".", nomprojet);
                        System.out.println();
                        return projects;
                    }
                    
                    projectTasks.getList().add(task);
        			return projects;
                }
            }
        }
		System.out.printf("Could not find a task with an ID of %d.", id);
        System.out.println();
		return projects;
	}
	
	public static String HelpString() {
	       return 	"  attach <project name> <task ID> :\n"
	        		+ "\t-attach a task to the project named <project name>";
		}

}
