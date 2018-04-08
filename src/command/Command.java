package command;

/**
 * Interface to regroup all executable commands.
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
