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

  @Log
  PIDController controller;

  public GyroTurnDiscrete(DriveTrain driveTrain, AHRS gyro, double angle) {
    addRequirements(driveTrain);

    this.gyro = gyro;
    this.driveTrain = driveTrain;

    this.angle = angle;
  }

  @Override
  public void initialize() {
    double kP = SmartDashboard.getNumber("GyroTurn P", 0.0);
    double kI = SmartDashboard.getNumber("GyroTurn I", 0.0);
    double kD = SmartDashboard.getNumber("GyroTurn D", 0.0);

    double angle = SmartDashboard.getNumber("angle", 0.0);
    this.goal = gyro.getAngle() + angle;

    controller = new PIDController(kP, kI, kD); // :eyes:
    controller.setTolerance(1.0);

    SmartDashboard.putNumber("Start Point", gyro.getAngle());
    SmartDashboard.putNumber("End Point", goal);
  }

  @Override
  public void execute() {
    double speed = controller.calculate(gyro.getAngle(), goal);

    driveTrain.curvatureDrive(speed, 0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
