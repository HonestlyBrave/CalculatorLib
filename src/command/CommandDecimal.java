package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmdDecimal")
public class CommandDecimal implements Command {

    @Override
    public void execute() {
        Facade.updateInput(".");
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoDisplay();
    }

}
