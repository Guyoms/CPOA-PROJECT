package com.codurance.training.tasks;

import java.sql.Date;
import java.util.Observable;


public class Task{
    private final long id;
    private final String description;
    private boolean done;
    private String deadline;

    /**
     *  Constructeur paramétré
     * @param id
     * @param description
     * @param done
     */
    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = "pas de deadline";
    }
    
    /**
     * Constructeur paramétré
     * @param id
     * @param description
     * @param done
     * @param deadline
     */
    public Task(long id, String description, boolean done, String deadline) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = deadline;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
}
