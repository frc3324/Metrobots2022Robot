// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.climber.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.climber.Climber;

public class RunClimb extends CommandBase {
  /** Creates a new RunClimb. */
  private Climber climber;
  private XboxController controller;

  public RunClimb(XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controller = controller;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean weAreClimbing = true;
    if(weAreClimbing) {
      // moves primary hooks with left and right triggers
      System.out.print("working");
      double longHookSpeed = controller.getRightTriggerAxis() - controller.getLeftTriggerAxis();
      climber.longHook.setSpeed(longHookSpeed);

      // moves secondary hooks with right stick
      double shortHookSpeed = controller.getRightY();
      climber.shortHook.setSpeed(shortHookSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
