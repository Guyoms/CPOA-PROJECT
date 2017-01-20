package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Add implements Command {

	private long lastId = 0;
	boolean test;
	
	@Override
	public List<Project> execute(String commandLine, List<Project> projects) {
		String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
        	String name = subcommandRest[1];
        	/*
        	 * Create a new project named "name" with an empty list of tasks
        	 */
        	projects.add(new Project(nextId(), name));
        }
        else if (subcommand.equals("task")) {
        	/*
        	 * Add a task to the tasks' list of a project with its description
        	 */
            String[] projectTask = subcommandRest[1].split(" ", 2);
            String parent = projectTask[0]; /*parent project*/
        	String description = projectTask[1]; /*the name of the subproject*/
            ArrayList<Task> projectTasks = null;
            for(Project project : projects){
            	projectTasks = project.getList();
        		if (project.getDescription().equals(parent)){
        			test = true;
        			projectTasks.add(new Task(nextId(), description, false));
        		}
        		else if (project instanceof Project){
        			projectTasks = AddInASubproject(parent, description, projectTasks, "task");
        		}
        		if (test){
        			return projects;
        		}
        	}
            
            if (projectTasks == null) {
                System.out.printf("Could not find a project with the name \"%s\".", projectTask[0]);
                System.out.println();
                return projects;
            }
           // projectTasks.add(new Task(nextId(), projectTask[1], false));
        }
        else if (subcommand.equals("subproject")) {
        	/*
        	 * Add a subproject to the tasks' list of a project
        	 */
        	String[] decoupe = subcommandRest[1].split(" ", 2);
        	String parent = decoupe[0]; /*parent project*/
        	String name = decoupe[1]; /*the name of the subproject*/
        	String[] projectTask = subcommandRest[1].split(" ", 2);
            ArrayList<Task> projectTasks = null;
            test = false;
            for(Project project : projects){
            	projectTasks = project.getList();
        		if (project.getDescription().equals(projectTask[0])){
        			test = true;
        			projectTasks.add(new Project(nextId(), name));
        		}
        		else if (project instanceof Project){
        			projectTasks = AddInASubproject(parent, name, projectTasks, "subproject");
        		}
        		if (test){
        			project.setList(projectTasks);
        			return projects;
        		}
        	}
            System.out.printf("Could not find a project with the name \"%s\".", parent);
            System.out.println();
            return projects;
        }
        return projects;
	}
	
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
	
	public ArrayList<Task> AddInASubproject(String parent, String name, ArrayList<Task> projectTasks, String mode){
		this.test = false;
		ArrayList<Task> subProject = new ArrayList<Task>();
		for(Task task : projectTasks){
			if(task instanceof Project){
				subProject = ((Project) task).getList();
	    		if (task.getDescription().equals(parent)){
	    			
	    			this.test = true;
	    			if(mode=="subproject"){
	    				subProject.add(new Project(nextId(), name));
	    			}
	    			else{
	    				subProject.add(new Task(nextId(), name, false));
	    			}
	    		}
	    		else{    			
	    			subProject = AddInASubproject(parent, name, subProject, mode);
	    		}
			}
    		
    		if (test){
    			((Project) task).setList(subProject);
    			return projectTasks;
    		}
    	}
		return projectTasks;
		
	}

}
