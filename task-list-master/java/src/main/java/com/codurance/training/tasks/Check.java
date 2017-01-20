package com.codurance.training.tasks;

import java.util.List;


/**
 * Check the task with the id as "Done"
 *
 */

public class Check implements Command {

	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {

		 String[] commandRest = commandLine.split(" ", 2);
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
    	boolean test = false;
        int id = Integer.parseInt(idString);
        for(int i=0; i<projects.size(); i++){
        	Project project = projects.get(i);
            for (Task task : project.getList()) {
                if (task.getId() == id) {
                	//Calls the setDone method in the class Task.java
                    task.setDone(done);
                    if ((task instanceof Project)&&(done==true)){
                		int idBoucle;
                		for(Task task2 : ((Project) task).getList()) {
                			idBoucle = (int) task2.getId();
                			setDoneBoucle(idBoucle, (Project) task, done);
                		}
                	}
                    return;
                }
                else{
                	if (task instanceof Project){
                		Project task2 = (Project) task;
                		test = setDoneBoucle(id, task2, done);
                		if ((test)&&(done==false)){
                			setDoneBoucle((int) task.getId(), project, done);
                		}
                		
                		if (test){
                			return;
                		}
                	}
                }
            }
        }
        System.out.printf("Could not find a task with an ID of %d.", id);
        System.out.println();
    }
    
    private boolean setDoneBoucle(int id, Project projects, boolean done) {
    	boolean test = false;
        for (Task task : projects.getList()) {
            if (task.getId() == id) {
            	task.setDone(done);
            	if ((task instanceof Project)&&(done==true)){
            		int idBoucle;
            		for(Task task2 : ((Project) task).getList()) {
            			idBoucle = (int) task2.getId();
            			setDoneBoucle(idBoucle, (Project) task, done);
            		}
            	}
                return true;
            }
            else{
            	if (task instanceof Project){
            		Project task2 = (Project) task;
            		test = setDoneBoucle(id, task2, done);
            		if ((test)&&(done==false)){
            			setDoneBoucle((int) task.getId(), projects, done);
            		}
                }
            }
        }
		return test;
    }
    
    
    
	public static String HelpString() {        
		String retour = "  check yes/no <task ID> :\n"
				+ "\tset the task with the ID <Task ID> as Done or To Do";
		return retour;
	}
}
