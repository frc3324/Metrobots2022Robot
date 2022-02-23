// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.climber.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team3324.robot.climber.Climber;

public class Climb extends CommandBase {
  /** Creates a new RunClimb. */
  private Climber climber;
  private XboxController controller;
  private double longHookSpeed;
  private double shortHookSpeed;


  public Climb(Climber climber, XboxController controller, double longHookSpeed, double shortHookSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    this.climber = climber;
    this.controller = controller;

    this.longHookSpeed = longHookSpeed;
    this.shortHookSpeed = shortHookSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // *HookAction makes the motors change directions 


    // moves primary hooks 
    int rightBumper = controller.getRightBumper() ? 1 : 0;
    int leftBumper = controller.getLeftBumper() ? 1 : 0;
    int longHookAction = rightBumper - leftBumper;
    
    climber.longHook.setSpeed(longHookAction * longHookSpeed);
    
    // moves secondary hooks
    int bKey = controller.getBButton() ? 1 : 0;
    int xKey = controller.getXButton() ? 1 : 0;
    int shortHookAction = bKey - xKey;

    climber.shortHook.setSpeed(shortHookAction * shortHookSpeed);
    
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
