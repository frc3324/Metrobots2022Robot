// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.intake;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.util.Consts;

public class Intake extends MotorSubsystem {
  private DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Consts.Intake.SOLENOID_FORWARD_CHANNEL, Consts.Intake.SOLENOID_REVERSE_CHANNEL);

  public Intake() {
    super(new SmartMotorController[]{Consts.Intake.MOTOR}, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void togglePosition() {
    solenoid.toggle();
  }

  public void setPosition(DoubleSolenoid.Value value) {
    solenoid.set(value);
  }
}
