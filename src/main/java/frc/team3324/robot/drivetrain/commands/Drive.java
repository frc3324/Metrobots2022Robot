package frc.team3324.robot.drivetrain.commands;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;

public class Drive extends CommandBase {
    DriveTrain driveTrain; 
    DoubleSupplier xSpeedSupplier; 
    DoubleSupplier ySpeedSupplier;

    public Drive(DriveTrain driveTrain, DoubleSupplier xSpeed, DoubleSupplier ySpeed) {
        addRequirements(driveTrain);
        this.driveTrain = driveTrain;
        this.xSpeedSupplier = xSpeed;
        this.ySpeedSupplier = ySpeed;
    }

    @Override
    public void execute() {
        double xSpeed = -1.0 * xSpeedSupplier.getAsDouble();
        double ySpeed = ySpeedSupplier.getAsDouble();

        driveTrain.curvatureDrive(xSpeed * xSpeed * sign(xSpeed), ySpeed + (sign(ySpeed) * 0.01));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public double sign(double speed) {
        if (speed >= 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
