package com.codurance.training.tasks;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    //List of project with a list of their tasks
    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
    	help();
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

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
            case "deadline":
            	deadLine(commandRest[1]);
            	break;
            case "today": 
            	today();
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
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
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
        out.println("  deadline <task ID> <dd/MM/yy> :\n"
        		+ "\t-set a deadline to a task");
        out.println("  today :\n"
        		+ "\t-displays all of the tasks which the deadline is today");
        out.println("  help : \n"
						+ "\t-displays the commands' list \n"
					    + "  quit :\n"
					    + "\t-close the application");
        
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    private long nextId() {
        return ++lastId;
    }
    
    /**
     * add a deadline to a task
     * @param Id
     * @param date
     */
    private void deadLine(String finCommande){
    	
    	 String[] subcommandRest = finCommande.split(" ", 2);         
         int id = Integer.parseInt(subcommandRest[0]);
         
         for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
             for (Task task : project.getValue()) {
                 if (task.getId() == id) {
                     task.setDeadline(subcommandRest[1]);
                     return;
                 }
             }
         }
    }
    
    /**
     * Displays all of the tasks which the deadline is today
     */
    private void today(){
    	// We take today's date
    	String format = "dd/MM/yy";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format); 
        java.util.Date date = new java.util.Date();
        
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
            	
            	//Si la deadline est aujourd'hui 
                if (task.getDeadline().equals(formater.format(date))) {
                	//On affiche la tache
                	out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                    return;
                }
            }
        }
        
    }
}
