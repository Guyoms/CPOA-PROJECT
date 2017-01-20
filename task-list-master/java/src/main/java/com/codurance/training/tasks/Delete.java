package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Delete implements Command {

	public List<Project> execute(String commandLine, List<Project> projects) {
		int id = Integer.parseInt(commandLine);
		boolean test1 = false, test2 = false;
		Iterator<Project> iterProj = projects.iterator();
		while(iterProj.hasNext()){
			Project project = iterProj.next();
			Iterator<Task> iterTask = project.getList().iterator();
            while(iterTask.hasNext()){
            	Task task = iterTask.next();
                if (task.getId() == id) {
                	iterTask.remove();
                	test1 = true;
                }
                else if(task instanceof Project){
                	test2 = DeleteInASubproject(id, ((Project) task).getList());
                }
            }
        }
		if ((!test1)&&(!test2)){
			System.out.printf("Could not find a task with an ID of %d.", id);
		}
        return projects;
		
	}

	private boolean DeleteInASubproject(int id, ArrayList<Task> list) {
		boolean test1 = false, test2 = false;
		Iterator<Task> iterTask = list.iterator();
        while(iterTask.hasNext()){
        	Task task = iterTask.next();
			if (task.getId() == id) {
				iterTask.remove();
				test1 = true;
			}
			else if(task instanceof Project){
				test2 = DeleteInASubproject(id, ((Project) task).getList());
			}
        }
        if((test1==true)||(test2==true)){
        	return true;
        }
        return false;
	}

	public static String HelpString() {
		String retour = "  delete <task ID> :\n" + "\t-delete the task with the ID <task ID>";
		return retour;
	}

}
