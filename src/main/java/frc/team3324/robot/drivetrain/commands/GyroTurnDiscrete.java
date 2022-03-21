package frc.team3324.robot.drivetrain.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;

public class GyroTurnDiscrete extends CommandBase {
  AHRS gyro;
  DriveTrain driveTrain;

  double goal; // total angle to turn to
  double angle; // relative angle to turn to, passed in

  PIDController controller;

  public GyroTurnDiscrete(DriveTrain driveTrain, AHRS gyro, double angle) {
    addRequirements(driveTrain);

    this.gyro = gyro;
    this.driveTrain = driveTrain;
    this.angle = angle;
  }

  @Override
  public void initialize() {
    this.goal = gyro.getAngle() + angle;

    SmartDashboard.putNumber("Start", gyro.getAngle());
    SmartDashboard.putNumber("End", goal);

    controller = new PIDController(
      SmartDashboard.getNumber("GyroTurn P", 0.0),
      SmartDashboard.getNumber("GyroTurn I", 0.0),
      SmartDashboard.getNumber("GyroTurn D", 0.0)
    );
    controller.setTolerance(1.0);
  }

  @Override
  public void execute() {
    double speed = controller.calculate(gyro.getAngle(), goal);

    SmartDashboard.putNumber("Drivetrain PID Speed", speed);

    driveTrain.curvatureDrive(-1.0 * speed, 0.0);
  }

  @Override
  public boolean isFinished() {
    // command designed to be called by whileHeld or similar
    return false;
  }
}
