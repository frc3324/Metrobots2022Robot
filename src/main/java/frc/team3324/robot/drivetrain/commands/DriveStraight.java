package frc.team3324.robot.drivetrain.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.util.Consts;

public class DriveStraight extends CommandBase {

    DriveTrain driveTrain;

    double anglekP;
    double anglekI;
    double anglekD;

    double distancekP;
    double distancekI;
    double distancekD;

    PIDController angleController;
    PIDController distanceController;

    public DriveStraight(DriveTrain driveTrain) {
        addRequirements(driveTrain);

        this.driveTrain = driveTrain;

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
        this.angleController = new PIDController(anglekP, anglekI, anglekD);
        this.distanceController = new PIDController(distancekP, distancekI, distancekD);

        driveTrain.resetGyro();

        this.angleController.setSetpoint(0.0);
        this.distanceController.setSetpoint(distanceToRevolutions(-2.45)); // 2.45 meters
    }

    @Override
    public void execute() {
        double alignment = angleController.calculate(driveTrain.getYaw());
        double speed = distanceController.calculate(driveTrain.getPosition());

        driveTrain.curvatureDrive(speed, alignment);
    }

    @Override
    public boolean isFinished() {
        return (distanceController.atSetpoint());
    }
}
