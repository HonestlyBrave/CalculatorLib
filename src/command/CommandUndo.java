package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmdUndo")
public class CommandUndo implements Command {

    @Override
    public void execute() {
        Facade.undo();
    }

    @Override
    public void undo() {

    }

}
