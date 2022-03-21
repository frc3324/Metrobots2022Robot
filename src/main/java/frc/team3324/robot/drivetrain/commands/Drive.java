package frc.team3324.robot.drivetrain.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;

public class Drive extends CommandBase {
    DriveTrain driveTrain; 
    DoubleSupplier xSpeedSupplier; 
    DoubleSupplier ySpeedSupplier;

    public Drive(DriveTrain driveTrain, DoubleSupplier xSpeedSupplier, DoubleSupplier ySpeedSupplier) {
        addRequirements(driveTrain);
        this.driveTrain = driveTrain;
        this.xSpeedSupplier = xSpeedSupplier;
        this.ySpeedSupplier = ySpeedSupplier;
    }

    private static double sign(double speed) {
        if (speed >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static double signedSquare(double number)  {
        return number * number * sign(number);
    }

    @Override
    public void execute() {
        double xSpeed = xSpeedSupplier.getAsDouble();
        double ySpeed = ySpeedSupplier.getAsDouble();

        driveTrain.curvatureDrive(xSpeed * 0.8, ySpeed);
    }

    @Override
    public boolean isFinished() {
        // We never want the robot to stop driving during a match, so drive is never finished
        return false;
    }

}
