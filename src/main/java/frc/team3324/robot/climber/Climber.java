// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.climber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3324.library.motorcontrollers.MetroSparkMAX;
import frc.team3324.robot.util.Consts;
import com.revrobotics.CANSparkMaxLowLevel;

import io.github.oblarg.oblog.annotations.Log;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */

  private final MetroSparkMAX leftForwardMotor = new MetroSparkMAX(15, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
  private final MetroSparkMAX leftBackMotor = new MetroSparkMAX(14, CANSparkMaxLowLevel.MotorType.kBrushless, 40);

  private final MetroSparkMAX rightForwardMotor = new MetroSparkMAX(13, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
  private final MetroSparkMAX rightBackMotor = new MetroSparkMAX(12, CANSparkMaxLowLevel.MotorType.kBrushless, 40);

  public void extendMotors(double speed) {
    double armsRatio = 0.5;
    leftBackMotor.set(speed * 1);
    rightBackMotor.set(speed * 1);

    leftForwardMotor.set(speed * armsRatio);
    rightForwardMotor.set(speed * armsRatio);
  }

  public void retractMotors(double speed) {
    double armsRatio = 0.5;
    speed = -speed;
    leftBackMotor.set(speed * 1);
    rightBackMotor.set(speed * 1);

    leftForwardMotor.set(speed * armsRatio);
    rightForwardMotor.set(speed * armsRatio);
  }


  public Climber() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
