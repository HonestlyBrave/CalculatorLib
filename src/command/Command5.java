package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmd5")
public class Command5 implements Command {

    @Override
    public void execute() {
        Facade.updateInput("5");
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoDisplay();
    }

}
