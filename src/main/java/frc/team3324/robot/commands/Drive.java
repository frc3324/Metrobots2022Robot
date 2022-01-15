package frc.team3324.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;

public class Drive extends CommandBase {
    DriveTrain driveTrain; 
    double xSpeed; 
    double ySpeed;

    public Drive(DriveTrain driveTrain, double xSpeed, double ySpeed) {
        addRequirements(driveTrain);
        this.driveTrain = driveTrain;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public void execute() {
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
