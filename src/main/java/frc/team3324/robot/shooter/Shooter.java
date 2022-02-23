package frc.team3324.robot.shooter;

import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.library.motorcontrollers.SmartMotorController.MetroNeutralMode;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.util.Consts;

public class Shooter extends MotorSubsystem {

    public Shooter() {
        super(new SmartMotorController[] {Consts.Shooter.LEFT_MOTOR, Consts.Shooter.RIGHT_MOTOR}, 0.0);
        this.getMotor(1).setInverted(true);
        
        this.getMotor(0).setNeutralMode(MetroNeutralMode.COAST);
        this.getMotor(1).setNeutralMode(MetroNeutralMode.COAST);
    }

}
