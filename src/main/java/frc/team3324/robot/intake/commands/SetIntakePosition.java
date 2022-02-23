package frc.team3324.robot.intake.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.team3324.robot.intake.Intake;
import frc.team3324.robot.util.Consts;

public class SetIntakePosition extends InstantCommand {
    Intake intake;
    DoubleSolenoid.Value position;

    public SetIntakePosition(Intake intake, DoubleSolenoid.Value position) {
        this.intake = intake;
        this.position = position;
    }

    @Override 
    public void execute() {
        int pcmChannel = -1;
        switch (position) {
            case kReverse:
                pcmChannel = Consts.Intake.SOLENOID_REVERSE_CHANNEL;
                break;
            case kForward:
                pcmChannel = Consts.Intake.SOLENOID_FORWARD_CHANNEL;
                break;
            case kOff:
                pcmChannel = -1;
                break;
        }
        SmartDashboard.putNumber("Intake Solenoid PCM Channel", pcmChannel);

        intake.setPosition(position);
    }
}