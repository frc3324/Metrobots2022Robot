package frc.team3324.robot.storage.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.storage.Storage;


public class RunStorage extends CommandBase {
    double botXSpeed;
    double topXSpeed;
    double botYSpeed;
    double topYSpeed;

    double joystickX;
    double joystickY;

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