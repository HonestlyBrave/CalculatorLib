package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmd4")
public class Command4 implements Command {

    @Override
    public void execute() {
        Facade.updateInput("4");
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoDisplay();
    }

}
