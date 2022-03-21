package frc.team3324.robot.util;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import frc.team3324.library.motorcontrollers.MetroSparkMAX;
import frc.team3324.library.motorcontrollers.MetroTalonSRX;
import frc.team3324.library.motorcontrollers.SmartMotorController;

public final class Consts {
    public static class DriveTrain {
        // Motor ports
        public static final int L_MIDDLE_MOTOR = 2;
        public static final int L_FRONT_MOTOR = 1;
        public static final int L_BACK_MOTOR = 21;

        public static final int R_MIDDLE_MOTOR = 8;
        public static final int R_FRONT_MOTOR = 6;
        public static final int R_BACK_MOTOR = 9;

        // Encoder and Auto constants
        // gears: Stage 1 {12:50}; Stage 2 {20:54}
        public static final double GEARBOX_STAGE1_RATIO = 12.0 / 50.0;
        public static final double GEARBOX_STAGE2_RATIO = 20.0 / 54.0;

        public static final double CONVERSION_RATIO = GEARBOX_STAGE1_RATIO * GEARBOX_STAGE2_RATIO;

        public static final double WHEEL_DIAMETER_METERS = 6.125 / 39.36;
        public static final double CIRCUMFERENCE_METERS = Math.PI * WHEEL_DIAMETER_METERS;

        // PID Constants
        public static final double GyroTurn_P = 0.00565;
        public static final double GyroTurn_I = 0.00000;
        public static final double GyroTurn_D = 0.0;

        public static final double DriveStraight_P = 0.28;
        public static final double DriveStraight_I = 0.03;
        public static final double DriveStraight_D = 0.0;
    }

    public static class Shooter {
        public static final SmartMotorController LEFT_MOTOR = new MetroSparkMAX(10, CANSparkMaxLowLevel.MotorType.kBrushless, 40); // Number on the SparkMax is 0, CAN ID is 21 because it can't be 0
        public static final SmartMotorController RIGHT_MOTOR = new MetroSparkMAX(7, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
        public static final SmartMotorController FEEDER_MOTOR = new MetroSparkMAX(19, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
    }

    public static class Storage {
        public static final SmartMotorController MOTOR = new MetroSparkMAX(4, CANSparkMaxLowLevel.MotorType.kBrushless, 25);
    }

    public static class Climber {
        // TODO: change climber ids
        public static final SmartMotorController RIGHT_LONG_HOOK = new MetroSparkMAX(17, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
        public static final SmartMotorController RIGHT_SHORT_HOOK = new MetroSparkMAX(15, CANSparkMaxLowLevel.MotorType.kBrushless, 40);

        public static final SmartMotorController LEFT_LONG_HOOK = new MetroSparkMAX(18, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
        public static final SmartMotorController LEFT_SHORT_HOOK = new MetroSparkMAX(14, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
    
        public static final DigitalInput RIGHT_LONG_HOOK_SWITCH = new DigitalInput(8);
        public static final DigitalInput LEFT_LONG_HOOK_SWITCH = new DigitalInput(1);

    }

    public static class Intake {
        public static final SmartMotorController MOTOR = new MetroSparkMAX(5, CANSparkMaxLowLevel.MotorType.kBrushless, 30);
        public static final int SOLENOID_FORWARD_CHANNEL = 0;
        public static final int SOLENOID_REVERSE_CHANNEL = 1;
    }

    public static class Vision {
        // todo: vision stuff here
    }
}