// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3324.library.commands.MotorCommand;
import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.util.Consts;



public class Climber extends SubsystemBase {
  /** Creates a new Climber. */

  public MotorSubsystem longHook = new MotorSubsystem(new SmartMotorController[] {Consts.Climber.LEFT_LONG_HOOK, Consts.Climber.RIGHT_LONG_HOOK}, 0.0);
  public MotorSubsystem shortHook = new MotorSubsystem(new SmartMotorController[] {Consts.Climber.LEFT_SHORT_HOOK, Consts.Climber.RIGHT_SHORT_HOOK}, 0.0);
  
  

  public void stop(){
    int stop = 0;

    longHook.setSpeed(stop); 
    shortHook.setSpeed(stop);
  }
  
  public Climber() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
