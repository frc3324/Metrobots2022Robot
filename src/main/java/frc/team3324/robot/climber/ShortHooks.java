package frc.team3324.robot.climber;

import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.library.motorcontrollers.SmartMotorController.MetroNeutralMode;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.util.Consts;

public class ShortHooks extends MotorSubsystem {
    
    public ShortHooks() {
        super(new SmartMotorController[] {Consts.Climber.LEFT_SHORT_HOOK, Consts.Climber.RIGHT_SHORT_HOOK}, 0.0);

        this.getMotor(0).setNeutralMode(MetroNeutralMode.BRAKE);
        this.getMotor(1).setNeutralMode(MetroNeutralMode.BRAKE);

        this.getMotor(1).setInverted(true);
    }
}
