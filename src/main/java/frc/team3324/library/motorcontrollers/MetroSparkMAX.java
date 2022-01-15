package frc.team3324.library.motorcontrollers;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;

public class MetroSparkMAX extends CANSparkMax implements SmartMotorController {

    public MetroSparkMAX(int deviceID, MotorType type, int currentLimit) {
        super(deviceID, type);

        super.restoreFactoryDefaults();
        setCurrentLimit(currentLimit);
    }

    @Override 
    public void follow(SmartMotorController motor, boolean invert) {
        super.follow((CANSparkMax) motor, invert);
    }

    @Override 
    public void setCurrentLimit(int value) {
        super.setSmartCurrentLimit(value);
    }

    @Override 
    public double getCurrentDraw() {
        return this.getOutputCurrent();
    }

    @Override 
    public void setNeutralMode(SmartMotorController.MetroNeutralMode mode) {
        switch (mode) {
            case BRAKE:
                this.setIdleMode(IdleMode.kBrake);
            case COAST:
                this.setIdleMode(IdleMode.kCoast);
        }
    }

    @Override 
    public RelativeEncoder getEncoder() {
        return super.getEncoder();
    }

}