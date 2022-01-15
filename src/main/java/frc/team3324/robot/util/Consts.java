package frc.team3324.robot.util;

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
        public static final int LM_MOTOR = 1;
        public static final int LU_MOTOR = 2;
        public static final int LD_MOTOR = 3;
        public static final int RM_MOTOR = 4;
        public static final int RU_MOTOR = 5;
        public static final int RD_MOTOR = 6;

        // Encoder and Auto constants
        public static final double HIGH_GEAR_RATIO = 1.0 / (108800 / 12000);
        public static final double LOW_GEAR_RATIO = 1.0 / (160000 / 8160);
        public static final double WHEEL_DIAMETER_METERS = 6.125 / 39.36;
        public static final double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER_METERS;
        public static final double PULSES = 1870; // 256 (pulses) * 4(quad, 4 ticks/pulse) * 3 * 25 (gear ratios)
        public static final double TICKS = PULSES * 4;
        public static final double DISTANCE_PER_PULSE_HIGH = CIRCUMFERENCE * HIGH_GEAR_RATIO;
        public static final double DISTANCE_PER_PULSE_LOW = CIRCUMFERENCE * LOW_GEAR_RATIO;
        public static final double DISTANCE_BETWEEN_WHEELS = 0.61;

        public static final double ksVolts = 0.189;
        public static final double LOW_GEAR_KV = 5.22;
        public static final double LOW_GEAR_KA = 0.892;

        public static final double TRACK_WIDTH = 0.6446045;

        public static final double OPTIMAL_KP = 1.64;

        public static final double kRamseteB = 2.0;
        public static final double kRamseteZeta = 0.7;

        public static final double LOW_GEAR_MAX_VELOCITY = 12.0 / LOW_GEAR_KV;
        public static final double LOW_GEAR_MAX_ACCELERATION = 12.0 / LOW_GEAR_KA;

        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(TRACK_WIDTH);


        public static final int GEARSHIFTER_FORWARD = 6;
        public static final int GEARSHIFTER_REVERSE = 7;

        public static final DoubleSolenoid.Value HIGH_GEAR = DoubleSolenoid.Value.kReverse;
        public static final DoubleSolenoid.Value LOW_GEAR = DoubleSolenoid.Value.kForward;

    }

    public static class Shooter {
        SmartMotorController LEFT_MOTOR = new MetroSparkMAX(11, CANSparkMaxLowLevel.MotorType.kBrushless, 40);
        SmartMotorController RIGHT_MOTOR = new MetroSparkMAX(10, CANSparkMaxLowLevel.MotorType.kBrushless, 40);

        public static final double GEAR_RATIO = 1.25;

        public static final double Kv = 0.107 / 60; // Char tool gives Kv in terms of RPS so /60
        public static final double Ks = 0.101;
        public static final double Kp = 1.06e-5;
    }

    public static class Storage {
        public static final SmartMotorController TOP_MOTOR = new MetroTalonSRX(21, 20);
        public static final SmartMotorController BOTTOM_MOTOR = new MetroTalonSRX(7, 25);
    }

    public static class Climber {
        public static final SmartMotorController LEFT_MOTOR = new MetroTalonSRX(1, 40);
        public static final SmartMotorController RIGHT_MOTOR = new MetroTalonSRX(25, 40);
    }

    public static class Intake {
        public static final SmartMotorController MOTOR = new MetroSparkMAX(9, CANSparkMaxLowLevel.MotorType.kBrushless, 30);
    }

    public static class Pivot {
        public static final DigitalInput UPPER_LIMIT_SWITCH = new DigitalInput(6);
        public static final DigitalInput LOWER_LIMIT_SWITCH = new DigitalInput(5);

        public static final SmartMotorController MOTOR = new MetroSparkMAX(8, CANSparkMaxLowLevel.MotorType.kBrushless, 20);
    }

    public static class Vision {
        public static final double TARGET_HEIGHT_FT = 7.5625; // height of target center
        public static final double FRONT_CAMERA_ANGLE_DEG = 60.0;
        public static final double FRONT_CAMERA_HEIGHT_FT = 31.0/12.0;

        public static final double WIDTH = 320; // in pixels
        public static final double HEIGHT = 180;

        public static final double VERTICAL_FOV_DEG = 34.3; // lifecam
        public static final double HORIZONTAL_FOV_DEG = 61.0;

        public static final double HORIZONTAL_APP = HORIZONTAL_FOV_DEG / WIDTH; // horizontal angle per pixel
        public static final double VERTICAL_APP = VERTICAL_FOV_DEG / HEIGHT; // horizontal angle per pixel

    }
}