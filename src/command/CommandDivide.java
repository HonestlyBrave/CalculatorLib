package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmdDivide")
public class CommandDivide implements Command {

    @Override
    public void execute() {
        if (Facade.operator(4)) {
            Facade.pushComand(this);
        }
    }

    @Override
    public void undo() {
        Facade.undoOperation();
    }

}
