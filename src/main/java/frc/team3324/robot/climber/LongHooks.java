// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.climber;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3324.library.commands.MotorCommand;
import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.util.Consts;



public class LongHooks extends SubsystemBase {
    private SmartMotorController leftHook = Consts.Climber.LEFT_LONG_HOOK;
    private SmartMotorController rightHook = Consts.Climber.RIGHT_LONG_HOOK;
    
    private DigitalInput leftLimitSwitch = Consts.Climber.LEFT_LONG_HOOK_SWITCH;
    private DigitalInput rightLimitSwitch = Consts.Climber.RIGHT_LONG_HOOK_SWITCH;

    public LongHooks() {
        this.leftHook.setInverted(true);
        leftHook.setNeutralMode(SmartMotorController.MetroNeutralMode.BRAKE);
        rightHook.setNeutralMode(SmartMotorController.MetroNeutralMode.BRAKE);
    }

    public void setLeftSpeed(double speed) {
      leftHook.set(speed);
    }

    public void setRightSpeed(double speed) {
      rightHook.set(speed);
    }

    public boolean getLeftLimitSwitch() {
      return this.leftLimitSwitch.get();
    }

    public boolean getRightLimitSwitch() {
      return this.rightLimitSwitch.get();
    }

    public void dashboardLimitSwitches() {
        SmartDashboard.putBoolean("Left Limit Switch", leftLimitSwitch.get());
        SmartDashboard.putBoolean("Right Limit Switch", rightLimitSwitch.get());
    }
}
