package frc.team3324.library.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class PneumaticShiftCommand extends InstantCommand {
    DoubleSolenoid solenoid;
    DoubleSolenoid.Value value;

    public PneumaticShiftCommand(DoubleSolenoid solenoid, DoubleSolenoid.Value value) {
        this.solenoid = solenoid;
        this.value = value;
    }

    @Override 
    public void execute() {
        solenoid.set(value);
    }
}