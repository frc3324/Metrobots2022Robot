package frc.team3324.robot.drivetrain.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.drivetrain.DriveTrain;

public class AlignToHub extends CommandBase {
  DriveTrain driveTrain;

  PIDController controller;
  double kP;
  double kI;
  double kD;

  public AlignToHub(DriveTrain driveTrain, double kP, double kI, double kD) {
    addRequirements(driveTrain);

    this.driveTrain = driveTrain;

    this.kP = SmartDashboard.getNumber("AlignToHub kP", 0.0);
    this.kI = SmartDashboard.getNumber("AlignToHub kI", 0.0);
    this.kD = SmartDashboard.getNumber("AlignToHub kD", 0.0);
  }

  @Override
  public void initialize() {
    controller = new PIDController(
      SmartDashboard.getNumber("AlignToHub kP", 0.0),
      SmartDashboard.getNumber("AlignToHub kI", 0.0),
      SmartDashboard.getNumber("AlignToHub kD", 0.0)
    );
    controller.setTolerance(5.0);
  }

  @Override
  public void execute() {
    double pixelError = SmartDashboard.getNumber("Hub Pixel Offset", 0.0);

    // if camera can't see hub, don't move
    if (pixelError == 3324.0) {
      pixelError = 0.0;
    }

    double speed = controller.calculate(pixelError, 0.0);

    driveTrain.curvatureDrive(-1.0 * speed, 0.0); // pixel error and drivetrain go in opposite directions, invert

    if (controller.atSetpoint()) {
      System.out.println("Drivetrain Velocity: " + driveTrain.getVelocity());
    }
  }

  @Override
  public boolean isFinished() {
    double velocityTolerance = 0.0;
    // command designed to be called by whileHeld or similar
    return false;
    //return (controller.atSetpoint() && driveTrain.getVelocity() < velocityTolerance);
  }
}
