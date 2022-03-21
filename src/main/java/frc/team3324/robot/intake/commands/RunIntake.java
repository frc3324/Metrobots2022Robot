package frc.team3324.robot.intake.commands;

import frc.team3324.library.commands.MotorCommand;
import frc.team3324.robot.intake.Intake;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class RunIntake extends MotorCommand {
    Intake intake;

    public RunIntake(Intake intake, double speed) {
        super(intake, speed, true);
        this.intake = intake;
    }

    @Override
    public void execute() {
        if (intake.getPosition() == DoubleSolenoid.Value.kForward) {
            super.execute();
        }
    }
    
}
