package command;

/**
 * Interface for executable commands.
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
public interface Command {

    /**
     * Execute a command.
     */
    void execute();

    /**
     * Undo a command.
     */
    void undo();
}
