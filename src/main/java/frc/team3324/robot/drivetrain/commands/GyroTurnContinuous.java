package frc.team3324.robot.drivetrain.commands;

import java.util.function.DoubleSupplier;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;

public class GyroTurnContinuous extends CommandBase {
  AHRS gyro;
  DriveTrain driveTrain;

  double goal; // total angle to turn to
  DoubleSupplier angleSupplier; // supplier for relative angle

  PIDController controller;
  double kP;
  double kI;
  double kD;

  public GyroTurnContinuous(DriveTrain driveTrain, AHRS gyro, double kP, double kI, double kD, DoubleSupplier angleSupplier) {
    addRequirements(driveTrain);

    this.gyro = gyro;
    this.driveTrain = driveTrain;
    this.angleSupplier = angleSupplier;

    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
  }

  @Override
  public void initialize() {
    controller = new PIDController(kP, kI, kD);
    controller.setTolerance(1.0);
  }

  @Override
  public void execute() {
    this.goal = gyro.getAngle() + angleSupplier.getAsDouble();

    double speed = controller.calculate(gyro.getAngle(), goal);

    driveTrain.curvatureDrive(speed, 0.0);
  }

  @Override
  public boolean isFinished() {
    // command designed to be called by whileHeld or similar
    return false;
  }
}
