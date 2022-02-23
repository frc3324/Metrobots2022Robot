package frc.team3324.robot.storage;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3324.library.motorcontrollers.MetroSparkMAX;
import frc.team3324.library.motorcontrollers.MetroTalonSRX;
import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.robot.util.Consts;

public class Storage extends SubsystemBase {
    
    SmartMotorController motor = Consts.Storage.MOTOR;

    public void moveMotor() {
        double speed = 0.7;

        motor.set(speed);
    }
    public void stopMotor() {
        double speed = 0;

        motor.set(speed);
    }
    
    public Storage() {}
}
