// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.shooter.subsystem;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3324.library.motorcontrollers.MetroSparkMAX;
//import frc.team3324.robot.util.Consts;

public class Shoot extends SubsystemBase {
  /** Creates a new Shoot. */

  MetroSparkMAX rightMotor = new MetroSparkMAX(10, MotorType.kBrushless, 40);
  MetroSparkMAX leftMotor = new MetroSparkMAX(11, MotorType.kBrushless, 40);
  MetroSparkMAX flyWheel = new MetroSparkMAX(13, MotorType.kBrushless, 40);

  public void moveMotor() {
    double speed = 0.7;

    rightMotor.set(speed);
    leftMotor.set(speed);
    flyWheel.set(speed);
  }

  public void stopMotor() {
    double stop = 0;

    rightMotor.set(stop);
    leftMotor.set(stop);
    flyWheel.set(stop);
  }

  public Shoot() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
