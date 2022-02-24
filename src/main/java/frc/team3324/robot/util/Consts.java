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
        public static final int L_MIDDLE_MOTOR = 12;
        public static final int L_FRONT_MOTOR = 3;
        public static final int L_BACK_MOTOR = 2;

        public static final int R_MIDDLE_MOTOR = 5;
        public static final int R_FRONT_MOTOR = 6;
        public static final int R_BACK_MOTOR = 9;

        // Encoder and Auto constants
        public static final double HIGH_GEAR_RATIO = 1.0 / (108800 / 12000);
        public static final double LOW_GEAR_RATIO = 1.0 / (160000 / 8160);

        // gears: Stage 1 {12:50}; Stage 2 {20:54}
        // public static final double GEAR_RATIO = 

        public static final double WHEEL_DIAMETER_METERS = 6.125 / 39.36;
        public static final double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER_METERS;
        public static final double PULSES = 1870; // 256 (pulses) * 4(quad, 4 ticks/pulse) * 3 * 25 (gear ratios)
        public static final double TICKS = PULSES * 4;
        public static final double DISTANCE_PER_PULSE_HIGH = CIRCUMFERENCE * HIGH_GEAR_RATIO;
        public static final double DISTANCE_PER_PULSE_LOW = CIRCUMFERENCE * LOW_GEAR_RATIO;
        public static final double DISTANCE_BETWEEN_WHEELS = 0.61;

        // PID Constants
        public static final double GyroTurn_P = 0.0061253324;
        public static final double GyroTurn_I = 0.00001;
        public static final double GyroTurn_D = 0.0;
    }

    public static class Shooter {
        public static final SmartMotorController LEFT_MOTOR = new MetroSparkMAX(21, CANSparkMaxLowLevel.MotorType.kBrushless, 40); // Number on the SparkMax is 0, CAN ID is 21 because it can't be 0
        public static final SmartMotorController RIGHT_MOTOR = new MetroSparkMAX(8, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
        public static final SmartMotorController FEEDER_MOTOR = new MetroSparkMAX(19, CANSparkMaxLowLevel.MotorType.kBrushless, 40);

    }

    public static class Storage {
        public static final SmartMotorController MOTOR = new MetroSparkMAX(17, CANSparkMaxLowLevel.MotorType.kBrushless, 25);
    }

    public static class Climber {
        public static final SmartMotorController RIGHT_LONG_HOOK = new MetroSparkMAX(15, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
        public static final SmartMotorController RIGHT_SHORT_HOOK = new MetroSparkMAX(18, CANSparkMaxLowLevel.MotorType.kBrushless, 40);

        public static final SmartMotorController LEFT_LONG_HOOK = new MetroSparkMAX(14, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
        public static final SmartMotorController LEFT_SHORT_HOOK = new MetroSparkMAX(13, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
    }

    public static class Intake {
        public static final SmartMotorController MOTOR = new MetroSparkMAX(16, CANSparkMaxLowLevel.MotorType.kBrushless, 30);
        public static final int SOLENOID_FORWARD_CHANNEL = 6;
        public static final int SOLENOID_REVERSE_CHANNEL = 7;
    }

    public static class Vision {
        // todo: vision stuff here
    }
}