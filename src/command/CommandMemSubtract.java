package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmdMemSub")
public class CommandMemSubtract implements Command {

    @Override
    public void execute() {
        Facade.subtractMemory();
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoSubtractMem();
    }

}
