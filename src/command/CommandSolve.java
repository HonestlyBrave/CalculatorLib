package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmdSolve")
public class CommandSolve implements Command {

    @Override
    public void execute() {
        Facade.solve();
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoSolve();
    }

}
