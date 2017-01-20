package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Attach implements Command {

	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		/*
		 * On recupere les arguments (nomprojet et id String)
		 */
		String[] subcommandRest = commandLine.split(" ", 2);
		String nomprojet = subcommandRest[0];
        String idString = subcommandRest[1];
        
        /*
         * On convertit l'id en int
         */
        int id = Integer.parseInt(idString);
        Task t = null;
        //On parcours les projets
        for(Project project : projects){
        	//On parcours les taches du projet selectionne
        	for (Task task : project.getList()) {
        		//Si on trouve la tache avec le bon id on la retient
        		if (task.getId() == id) {
        			t = task;
        		}
        		else if (task instanceof Project){
        			//methode recursive
        			t = getTaskInASubproject(nomprojet, id, (Project) task);
        		}
        		if(t!=null){
        			break;
        		}
        	}
        	if(t!=null){
    			break;
    		}
        }
        if (t!=null){
        	Project projectTasks = null;
        	//On cherche maintenant le projet a qui on rattachera la tache
        	for(Project project : projects){
            	//Si le projet a le bon nom, on le memorise
        		if (project.getDescription().equals(nomprojet)){
        			projectTasks = project;
        			projectTasks.getList().add(t);
        		}
        		else if (project instanceof Project){
        			//methode recursive
        			projectTasks = AttachInASubproject(nomprojet, id, project.getList(), t);
        		}
        		if(projectTasks!=null){
        			return projects;
        		}
            }
        	if (projectTasks == null) {
                System.out.printf("Could not find a project with the name \"%s\".", nomprojet);
                System.out.println();
            }
    		return projects;
        }
        else{
        	System.out.printf("Could not find a task with an ID of %d.", id);
            System.out.println();
        }
		return projects;
        
        /*
        //On parcours les projets
		for(int i=0; i<projects.size(); i++){
        	Project project = projects.get(i);
        	//On parcours les taches du projet selectionne
            for (Task task : project.getList()) {
            	//Si la tache a l'id recherche
                if (task.getId() == id) {
                	Project projectTasks = null;
                	//On cherche maintenant le projet a qui on rattachera la tache
                    for(int r=0; r<projects.size();r++){
                    	//Si le projet a le bon nom, on le memorise
                		if (projects.get(r).getDescription().equals(nomprojet)){
                			projectTasks = projects.get(r);
                		}
                		else if (projects.get(r) instanceof Project){
                			projectTasks = AttachInASubproject(nomprojet, id, projectTasks);
                		}
                	//	if (test){
                		//	return projects;
                		//}
                	}
                    //
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
		return projects;*/
	}
	
	private Task getTaskInASubproject(String nomprojet, int id, Project project) {
    	Task t=null;
		for (Task task : project.getList()) {
    		if (task.getId() == id) {
    			t = task;
    			return t;
    		}
    		else if (task instanceof Project){
    			//methode recursive
    			t = getTaskInASubproject(nomprojet, id, (Project) task);
    			if (t!=null){
    				return t;
    			}
    		}
    	}
		return null;
	}

	private Project AttachInASubproject(String nomprojet, int id, ArrayList<Task> Tasks, Task t) {
		Project projectTasks = null;
		for (Task task : Tasks){
			if (task instanceof Project){
	        	//Si le projet a le bon nom, on le memorise
	    		if (task.getDescription().equals(nomprojet)){
	    			projectTasks = (Project) task;
	    			projectTasks.getList().add(t);
	    		}
	    		else {
	    			//methode recursive
	    			projectTasks = AttachInASubproject(nomprojet, id, ((Project) task).getList(), t);
	    		}
			}
    		return projectTasks;
        }
		return null;
	}

	public static String HelpString() {
	       return 	"  attach <project name> <task ID> :\n"
	        		+ "\t-attach a task to the project named <project name>\n";
		}

}
