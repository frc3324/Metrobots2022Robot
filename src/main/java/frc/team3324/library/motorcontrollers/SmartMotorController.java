package frc.team3324.library.motorcontrollers;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public interface SmartMotorController extends MotorController {
    enum MetroNeutralMode {BRAKE, COAST}
    void setCurrentLimit(int value);
    double getCurrentDraw();
    void follow(SmartMotorController motor, boolean invert);
    void setNeutralMode(MetroNeutralMode mode);
}