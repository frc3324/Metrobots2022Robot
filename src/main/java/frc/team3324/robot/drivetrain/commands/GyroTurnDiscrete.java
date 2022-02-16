package frc.team3324.robot.drivetrain.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;

import io.github.oblarg.oblog.annotations.Log;

public class GyroTurnDiscrete extends CommandBase {
  AHRS gyro;
  DriveTrain driveTrain;

  double goal; // total angle to turn to
  double angle; // relative angle to turn to, passed in

  PIDController controller;
  double kP;
  double kI;
  double kD;

  public GyroTurnDiscrete(DriveTrain driveTrain, AHRS gyro, double kP, double kI, double kD, double angle) {
    addRequirements(driveTrain);

    this.gyro = gyro;
    this.driveTrain = driveTrain;
    this.angle = angle;

    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
  }

  @Override
  public void initialize() {
    this.goal = gyro.getAngle() + angle;

    controller = new PIDController(kP, kI, kD); // :eyes:
    controller.setTolerance(1.0);
  }

  @Override
  public void execute() {
    double speed = controller.calculate(gyro.getAngle(), goal);

    driveTrain.curvatureDrive(speed, 0.0);
  }

  @Override
  public boolean isFinished() {
    // command designed to be called by whileHeld or similar
    return false;
  }
}
