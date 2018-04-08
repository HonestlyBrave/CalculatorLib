package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmd8")
public class Command8 implements Command {

    @Override
    public void execute() {
        Facade.updateInput("8");
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoDisplay();
    }

}
