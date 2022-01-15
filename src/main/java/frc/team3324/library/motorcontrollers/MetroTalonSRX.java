package frc.team3324.library.motorcontrollers;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class MetroTalonSRX extends WPI_TalonSRX implements SmartMotorController {

    public MetroTalonSRX(int deviceNumber, int currentLimit) {
        super(deviceNumber);
        super.enableCurrentLimit(true);

        setCurrentLimit(currentLimit);
    }

    @Override 
    public void follow(SmartMotorController motor, boolean invert) {
        super.follow((WPI_TalonSRX) motor);
    }

    @Override 
    public void setCurrentLimit(int value) {
        super.configContinuousCurrentLimit(value);
    }

    @Override 
    public double getCurrentDraw() {
        return this.getStatorCurrent();
    }

    @Override 
    public void setNeutralMode(SmartMotorController.MetroNeutralMode mode) {
        switch (mode) {
            case BRAKE:
                super.setNeutralMode(NeutralMode.Brake);
            case COAST:
                super.setNeutralMode(NeutralMode.Coast);
        }
    }
}