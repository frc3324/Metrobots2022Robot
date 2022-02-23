package frc.team3324.robot.intake.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team3324.robot.intake.Intake;

public class ToggleIntakePosition extends InstantCommand {
    Intake intake;

    public ToggleIntakePosition(Intake intake) {
        this.intake = intake;
    }

    @Override 
    public void execute() {
        intake.togglePosition();
    }
}