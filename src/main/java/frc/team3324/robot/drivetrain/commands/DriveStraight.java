package frc.team3324.robot.drivetrain.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.util.Consts;

public class DriveStraight extends CommandBase {

    DriveTrain driveTrain;
    double goal;

    double anglekP;
    double anglekI;
    double anglekD;

    double distancekP;
    double distancekI;
    double distancekD;

    PIDController angleController;
    PIDController distanceController;

    public DriveStraight(DriveTrain driveTrain, double distance) {
        addRequirements(driveTrain);

        this.driveTrain = driveTrain;
        this.goal = driveTrain.getDistance() + distance;

        this.anglekP = SmartDashboard.getNumber("anglekP", 0.0);
        this.anglekI = SmartDashboard.getNumber("anglekI", 0.0);
        this.anglekD = SmartDashboard.getNumber("anglekD", 0.0);

        this.distancekP = SmartDashboard.getNumber("distancekP", 0.0);
        this.distancekI = SmartDashboard.getNumber("distancekI", 0.0);
        this.distancekD = SmartDashboard.getNumber("distancekD", 0.0);
    }

    /**
     * Takes in a distance in meters, converts to number of revolutions of the wheel required
     */
    private static double distanceToRevolutions(double distance) {
        return (distance / Consts.DriveTrain.CIRCUMFERENCE_METERS);
    }

    @Override
    public void initialize() {
        this.angleController = new PIDController(
            SmartDashboard.getNumber("anglekP", 0.0),
            SmartDashboard.getNumber("anglekI", 0.0),
            SmartDashboard.getNumber("anglekD", 0.0)
        );

        this.distanceController = new PIDController(
            SmartDashboard.getNumber("distancekP", 0.0),
            SmartDashboard.getNumber("distancekI", 0.0),
            SmartDashboard.getNumber("distancekD", 0.0)
        );

        driveTrain.resetGyro();

        this.angleController.setSetpoint(0.0);
        this.distanceController.setSetpoint(goal);
    }

    @Override
    public void execute() {
        double alignment = angleController.calculate(driveTrain.getYaw());
        double speed = distanceController.calculate(driveTrain.getDistance());

        SmartDashboard.putNumber("Alignment Speed", alignment);
        SmartDashboard.putNumber("Distance Speed", speed);

        driveTrain.curvatureDrive(alignment, speed);
    }

    @Override
    public boolean isFinished() {
        return (distanceController.atSetpoint());
    }
}
