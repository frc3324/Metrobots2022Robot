package frc.team3324.library.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj2.command.InstantCommand;

class ToggleRelayCommand extends InstantCommand {
    Relay relay;

    public ToggleRelayCommand(Relay relay) {
        this.relay = relay;
    }

    @Override 
    public void execute() {
        switch (relay.get()) {
            case kOn:
                relay.set(Relay.Value.kOff);
                break;
            case kOff:
                relay.set(Relay.Value.kOn);
                break;
            case kForward:
                relay.set(Relay.Value.kReverse);
                break;
            case kReverse:
                relay.set(Relay.Value.kForward);
                break;
        }
    }
}