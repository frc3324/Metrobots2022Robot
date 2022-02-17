package frc.team3324.robot.storage;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team3324.library.motorcontrollers.MetroSparkMAX;
import frc.team3324.library.motorcontrollers.MetroTalonSRX;
import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.robot.util.Consts;

public class Storage extends SubsystemBase {
    
    SmartMotorController topMotor = Consts.Storage.TOP_MOTOR;
    SmartMotorController botMotor = Consts.Storage.BOTTOM_MOTOR;

    public void moveMotor() {
        double speed = 0.7;

        topMotor.set(speed);
        botMotor.set(speed);
    }
    public void stopMotor() {
        double speed = 0;

        topMotor.set(speed);
        botMotor.set(speed);
    }
    
    public Storage() {}
}
