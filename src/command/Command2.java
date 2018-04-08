package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmd2")
public class Command2 implements Command {

    @Override
    public void execute() {
        Facade.updateInput("2");
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoDisplay();
    }

}
