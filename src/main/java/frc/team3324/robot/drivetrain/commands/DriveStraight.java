package frc.team3324.robot.drivetrain.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.util.Consts;

public class DriveStraight extends CommandBase {
    DriveTrain driveTrain;
    double distance;

    PIDController distanceController;

    public DriveStraight(DriveTrain driveTrain, double distance) {
        addRequirements(driveTrain);

        this.driveTrain = driveTrain;
        this.distance = distance;
    }

    @Override
    public void initialize() {
        double goal = driveTrain.getDistance() + distance;
        SmartDashboard.putNumber("DriveStraight Goal", goal);

        this.distanceController = new PIDController(
            Consts.DriveTrain.DriveStraight_P,
            Consts.DriveTrain.DriveStraight_I,
            Consts.DriveTrain.DriveStraight_D
        );

        // this.distanceController = new PIDController(
        //     SmartDashboard.getNumber("Distance P", 0.0),
        //     SmartDashboard.getNumber("Distance I", 0.0),
        //     SmartDashboard.getNumber("Distance D", 0.0)
        // );

        this.distanceController.setSetpoint(goal);

        driveTrain.setMaxOutput(0.55);
    }

    @Override
    public void execute() {
        double speed = distanceController.calculate(driveTrain.getDistance());

        SmartDashboard.putNumber("Distance Speed", speed);

        driveTrain.curvatureDrive(0.0, (-1.0 * speed));
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.setMaxOutput(1.0);
    }

    @Override
    public boolean isFinished() {
        return (distanceController.atSetpoint());
    }
}
