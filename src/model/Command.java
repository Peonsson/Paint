package model;

/**
 * Created by robin on 8/6/16.
 */
public interface Command {
    public void doCommand();
    public void undoCommand();
}
