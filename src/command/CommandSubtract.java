package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmdSubtract")
public class CommandSubtract implements Command {

    @Override
    public void execute() {
        if (Facade.subtract()) {
            Facade.pushComand(this);
        }
    }

    @Override
    public void undo() {
        Facade.undoOperation();
    }

}
