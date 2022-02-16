// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.intake.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.intake.Intake;


public class SuckIn extends CommandBase {
  Intake intake;
  XboxController controller;

  /** Creates a new GoClimp. */
  public SuckIn(Intake intake, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double trigger = controller.getRightTriggerAxis();
    if (trigger > 0) {
      intake.moveMotor();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stopMotor();
  }

  // Returns true when the command should end
  @Override
  public boolean isFinished() {
    return false;
  }
}
