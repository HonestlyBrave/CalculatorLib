package command;

import model.Facade;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhammad Diallo Thomas - muhammaddiallo.thomas@gmail.com
 */
@Component("cmdMemRecall")
public class CommandMemRecall implements Command {

    @Override
    public void execute() {
        Facade.recallMemory();
        Facade.pushComand(this);
    }

    @Override
    public void undo() {
        Facade.undoRecallMem();
    }

}
