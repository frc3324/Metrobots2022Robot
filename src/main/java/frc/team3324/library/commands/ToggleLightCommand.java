package frc.team3324.library.commands;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.InstantCommand;

class ToggleLightCommand extends InstantCommand {
    DigitalOutput light;

    public ToggleLightCommand(DigitalOutput light) {
        this.light = light;
    }

    @Override 
    public void execute() {
        light.set(!light.get());
    }
}