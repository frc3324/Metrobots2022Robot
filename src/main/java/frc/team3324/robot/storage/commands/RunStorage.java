package frc.team3324.robot.storage.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.storage.Storage;


public class RunStorage extends CommandBase {
    public RunStorage(Storage storage) {
        this.addRequirements(storage);
    }

    public void initialize() {

    }
    public void execute() {

    }
    public void end() {

    }
    public boolean isFinished() {
        return false;
    }
}