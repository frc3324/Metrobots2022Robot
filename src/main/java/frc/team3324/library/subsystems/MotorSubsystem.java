package frc.team3324.library.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3324.library.motorcontrollers.SmartMotorController;

public class MotorSubsystem extends SubsystemBase {
    SmartMotorController[] motorList;

    public MotorSubsystem(SmartMotorController[] motorList, double defaultSpeed) {
        for (SmartMotorController motor : motorList) {
            motor.setNeutralMode(SmartMotorController.MetroNeutralMode.BRAKE);
        }

        this.motorList = motorList;
    }

    public void setSpeed(double speed) {
        for (SmartMotorController motor : motorList) {
            motor.set(speed);
        }
    }

    public void setSpeed(double speed, int index) {
        motorList[index].set(speed);
    }

    public SmartMotorController getMotor(int index) {
        return motorList[index];
    }
}