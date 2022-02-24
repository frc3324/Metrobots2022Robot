package frc.team3324.library.motorcontrollers;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;

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
                super.setIdleMode(IdleMode.kBrake);
                break;
            case COAST:
                super.setIdleMode(IdleMode.kCoast);
                break;
        }
    }

    @Override 
    public RelativeEncoder getEncoder() {
        return super.getEncoder();
    }

}