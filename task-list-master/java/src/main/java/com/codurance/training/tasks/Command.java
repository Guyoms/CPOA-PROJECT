package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Command {
	 private void execute(String commandLine) {
	        String[] commandRest = commandLine.split(" ", 2);
	        String command = commandRest[0];
	        switch (command) {
	            case "show":
	                show();
	                break;
	            case "add":
	                add(commandRest[1]);
	                break;
	            case "check":
	                check(commandRest[1]);
	                break;
	            case "uncheck":
	                uncheck(commandRest[1]);
	                break;
	            case "help":
	                help();
	                break;
	            case "delete":
	            	delete(commandRest[1]);
	            	break;
	            default:
	                error(command);
	                break;
	        }
	    }
	    
	    /**
	     * Shows the tasks' list of each project 
	     */

	    private void show() {
	        List<Project> tasks;
			for (Project project : tasks.) {
	            out.println(project.getKey());
	            for (Task task : project.getValue()) {
	                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
	            }
	            out.println();
	        }
	    }

	    
	    private void add(String commandLine) {
	        String[] subcommandRest = commandLine.split(" ", 2);
	        String subcommand = subcommandRest[0];
	        if (subcommand.equals("project")) {
	            addProject(subcommandRest[1]);
	        } else if (subcommand.equals("task")) {
	            String[] projectTask = subcommandRest[1].split(" ", 2);
	            addTask(projectTask[0], projectTask[1]);
	        }
	    }

	    /**
	     * Create a new project named "name" with an empty list of tasks
	     * @param name
	     */
	    private void addProject(String name) {
	        tasks.put(name, new ArrayList<Task>());
	    }

	    /**
	     * Add a task to the tasks' list of a project with its description
	     * @param project
	     * @param description
	     */
	    private void addTask(String project, String description) {
	        List<Task> projectTasks = tasks.get(project);
	        if (projectTasks == null) {
	            out.printf("Could not find a project with the name \"%s\".", project);
	            out.println();
	            return;
	        }
	        projectTasks.add(new Task(nextId(), description, false));
	    }

	    /**
	     * Check the task with the id as "Done"
	     * @param idString
	     */
	    private void check(String idString) {
	        setDone(idString, true);
	    }

	    /**
	     * Check the task with the id as "To Do"
	     * @param idString
	     */
	    private void uncheck(String idString) {
	        setDone(idString, false);
	    }

	    /**
	     * Set a task to "done" or "to Do"
	     * @param idString
	     * @param done
	     */
	    private void setDone(String idString, boolean done) {
	        int id = Integer.parseInt(idString);
	        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
	            for (Task task : project.getValue()) {
	                if (task.getId() == id) {
	                	//Calls the setDone method in the class Task.java
	                    task.setDone(done);
	                    return;
	                }
	            }
	        }
	        out.printf("Could not find a task with an ID of %d.", id);
	        out.println();
	    }

	    /**
	     * Displays commands' list
	     */
	    private void help() {
	        out.println("Commands:");
	        out.println("  show :\n"
	        		+ "\t-shows the tasks' list of each project");
	        out.println("  add project <project name> :\n"
	        		+ "\t-create a new project named <project name> with an empty list of tasks");
	        out.println("  add task <project name> <task description> :\n"
	        		+ "\t-add a task to the tasks' list of the project <project name> with a description");
	        out.println("  check <task ID> :\n"
	        		+ "\t-set the task with the ID <task ID> as Done");
	        out.println("  uncheck <task ID> :\n"
	        		+ "\t-set the task with the ID <task ID> as To Do");
	        out.println("  help : \n"
							+ "\t-displays the commands' list \n"
						    + "  quit :\n"
						    + "\t-close the application");
	        
	    }
	    
	    /*
	     * 
	     */
	    private boolean delete(String idString){
	    	int id = Integer.parseInt(idString);
	        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
	            for (Task task : project.getValue()) {
	                if (task.getId() == id) {
	                    task = null;
	                    return true;
	                }
	            }
	        }
	        out.printf("Could not find a task with an ID of %d.", id);
	        return false;
	    }

	    private void error(String command) {
	        out.printf("I don't know what the command \"%s\" is.", command);
	        out.println();
	    }
}