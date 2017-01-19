package com.codurance.training.tasks;

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
                		if (projects.get(r).getNom().equals(nomprojet)){
                			projectTasks = projects.get(r);
                		}
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
		return projects;
	}
	
	public static String HelpString() {
	       return 	"  attach <project name> <task ID> :\n"
	        		+ "\t-attach a task to the project named <project name>\n";
		}

}
