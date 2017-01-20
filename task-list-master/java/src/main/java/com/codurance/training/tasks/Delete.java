package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Delete implements Command {

	public List<Project> execute(String commandLine, List<Project> projects) {
		int id = Integer.parseInt(commandLine);
		//boolean test = false;
    	for(int i=0; i<projects.size(); i++){
    		Project project = projects.get(i);
            for (Task task : project.getList()) {
                if (task.getId() == id) {
                	project.getList().remove(task);
                    //return projects;
                }
                else if(task instanceof Project){
                	//test = DeleteInASubproject(id, ((Project) task).getList());
    				/*if(test){
    					return projects;
    				}*/
                	DeleteInASubproject(id, ((Project) task).getList());
                }
            }
        }
        System.out.printf("Could not find a task with an ID of %d.", id);
        return projects;
		
	}

	private boolean DeleteInASubproject(int id, ArrayList<Task> list) {
		boolean test = false;
		for(Task task : list){
			if (task.getId() == id) {
				list.remove(task);
				return true;
			}
			else if(task instanceof Project){
				/*test = DeleteInASubproject(id, ((Project) task).getList());
				if(test){
					return true;
				}*/
				DeleteInASubproject(id, ((Project) task).getList());
			}
        }
		return false;
	}

	public static String HelpString() {
		String retour = "  delete <task ID> :\n" + "\t-delete the task with the ID <task ID>";
		return retour;
	}

}
