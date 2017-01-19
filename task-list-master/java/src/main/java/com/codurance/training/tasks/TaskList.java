package com.codurance.training.tasks;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.ListCellRenderer;
import javax.swing.text.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    
    private List<Project> tasks = new ArrayList<Project>();
    //List of project with a list of their tasks
    private static Map<String, Command> ListCommands = new HashMap<String, Command>();
    public static void InitiateListCommands() {
		ListCommands.put("delete", new Delete());
		ListCommands.put("add", new Add());
		ListCommands.put("check", new Check());	
		ListCommands.put("today", new Today());
		ListCommands.put("help", new Help());
		ListCommands.put("deadline", new Deadline());
		ListCommands.put("view", new view());
	}

	//private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    public static void main(String[] args) throws Exception {
    	InitiateListCommands();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
    	Help.HelpString();
    	 ListCommands.get("help").execute("random", tasks);
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

    private void execute(String commandLine) throws ArrayIndexOutOfBoundsException {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        String arguments;
        if (command.length()==commandLine.length()){
        	arguments="chaine pour passer le try/catch";
        }
        else{
        	arguments = commandRest[1];
        }
        
        try{

        	tasks = ListCommands.get(command).execute(arguments, tasks);
        }
        catch(Exception e){
        	error(command);
        }
    }





    /**
     * Add a task to the tasks' list of a project with its description
     * @param project
     * @param description
     */

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
        for(int i=0; i<tasks.size(); i++){
        	Project project = tasks.get(i);
            for (Task task : project.getList()) {
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

    /**
     * Dislays an error when the command is not recognized
     * @param command
     */
    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
