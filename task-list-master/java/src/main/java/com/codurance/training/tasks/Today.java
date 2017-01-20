package com.codurance.training.tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Displays all of the tasks which the deadline is today
 */
public class Today implements Command {

	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		String format = "dd/MM/yy";
        SimpleDateFormat formater = new java.text.SimpleDateFormat(format); 
        Date date = new java.util.Date();
        int recursivite = 1;
        for(int i=0; i<projects.size(); i++){
         	Project project = projects.get(i);
            for (Task task : project.getList()) {
            	//Si la deadline est aujourd'hui 
                if (task.getDeadline().equals(formater.format(date))) {
                	//On affiche la tache
                	System.out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                	if(task instanceof Project){
                		DisplayAllTasksInSubproject(((Project) task).getList(), recursivite);
                	}
                }
                else if (task instanceof Project){
                	DisplayRecursif((Project) task, recursivite, formater, date);
                }
            }
        }
        return projects;
	}
	
	private void DisplayAllTasksInSubproject(ArrayList<Task> list, int recursivite) {
		String tabulation ="";
		for (int i=0; i<recursivite;i++){
			tabulation+="\t";
		}
		recursivite++;
		for(Task task : list){
			System.out.printf(tabulation + "    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
			if(task instanceof Project){
        		DisplayAllTasksInSubproject(((Project) task).getList(), recursivite);
        	}
		}
	}

	public void DisplayRecursif(Project project, int recursivite, SimpleDateFormat formater, Date date){
		String tabulation ="";
		for (int i=0; i<recursivite;i++){
			tabulation+="\t";
		}
		recursivite++;
		for (Task task : project.getList()) {
        	//Si la deadline est aujourd'hui 
            if (task.getDeadline().equals(formater.format(date))) {
            	//On affiche la tache
            	System.out.printf(tabulation + "    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            	if(task instanceof Project){
            		DisplayAllTasksInSubproject(((Project) task).getList(), recursivite);
            	}
            }
            else if (task instanceof Project){
            	DisplayRecursif((Project) task, recursivite, formater, date);
            }
        }
	}

	public static String HelpString() {
		String retour = "  today :\n" + "\t-displays all of the tasks which the deadline is today";
		return retour;
	}

}
