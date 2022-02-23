package frc.team3324.library.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class PneumaticToggleCommand extends InstantCommand {
    DoubleSolenoid solenoid;

    public PneumaticToggleCommand(DoubleSolenoid solenoid) {
        this.solenoid = solenoid;
    }

    @Override 
    public void execute() {
        solenoid.toggle();
        /*switch (solenoid.get()) {
            case kForward:
                solenoid.set(DoubleSolenoid.Value.kReverse);
                break;
            case kReverse:
                solenoid.set(DoubleSolenoid.Value.kForward);
                break;
            case kOff:
                // solenoid is off, do nothing
                break;
        }*/
    }
}