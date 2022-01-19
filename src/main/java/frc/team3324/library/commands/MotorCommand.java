package frc.team3324.library.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.library.subsystems.MotorSubsystem;
import java.util.function.BooleanSupplier;

public class MotorCommand extends CommandBase {
    MotorSubsystem subsystem;
    double speed;
    int index; // the index of the motor to run. If -1, run every motor in the subsystem.
    boolean required;
    BooleanSupplier finishedCondition;

    // constructor with index for single motor control
    public MotorCommand(MotorSubsystem subsystem, double speed, int index, boolean required, BooleanSupplier finishedCondition) {
        this.subsystem = subsystem;
        this.speed = speed;
        this.index = index;
        this.required = required;
        this.finishedCondition = finishedCondition;

        if (required) {
            addRequirements(subsystem);
        }
    }

    // shorthand constructor without an index for whole-subsystem control
    public MotorCommand(MotorSubsystem subsystem, double speed, boolean required, BooleanSupplier finishedCondition) {
        this(subsystem, speed, -1, required, finishedCondition);
    }

    @Override 
    public void execute() {
        if (index != -1) {
            subsystem.setSpeed(speed, index);
        } else {
            subsystem.setSpeed(speed);
        }
    }

    @Override 
    public void end(boolean interrupted) {
        subsystem.setSpeed(0.0);
    }

    @Override 
    public boolean isFinished() {
        return finishedCondition.getAsBoolean();
    }
}
