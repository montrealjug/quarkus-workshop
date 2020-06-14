package org.montrealjug.api;

import java.util.Objects;

public class Todo {
    private String title;
    private boolean completed;


    public Todo() {
    }

    public Todo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return isCompleted() == todo.isCompleted() &&
                Objects.equals(getTitle(), todo.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isCompleted());
    }
}
