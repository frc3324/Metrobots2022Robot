package frc.team3324.robot.shooter.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team3324.library.commands.MotorCommandContinuous;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;

public class Shoot extends MotorCommandContinuous {

    XboxController secondaryController;

    public Shoot(MotorSubsystem shooter, XboxController secondary, DoubleSupplier speedSupplier) {
        super(shooter, speedSupplier, true);

        this.secondaryController = secondary;
    }

    @Override
    public void execute() {
        super.execute();

        secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, 1.0);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, 0.0);
    }
    
}
