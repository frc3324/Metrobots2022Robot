// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.intake;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3324.library.motorcontrollers.MetroSparkMAX;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  MetroSparkMAX leftMotor = new MetroSparkMAX(9, MotorType.kBrushless, 30);
  MetroSparkMAX rightMotor = new MetroSparkMAX(10, MotorType.kBrushless, 30);

  public void moveMotor() {
    double speed = 0.7;

    leftMotor.set(speed);
    rightMotor.set(speed);
  }

  public void stopMotor() {
    double speed = 0;

    leftMotor.set(speed);
    rightMotor.set(speed);
  }

  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
