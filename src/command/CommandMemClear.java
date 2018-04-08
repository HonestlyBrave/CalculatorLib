package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmdMemClear")
public class CommandMemClear implements Command {

    @Override
    public void execute() {
        Facade.clearMemory();
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoClearMem();
    }

}
