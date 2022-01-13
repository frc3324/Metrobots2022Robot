package frc.team3324.robot.util

import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics
import frc.team3324.library.motorcontrollers.MetroSparkMAX
import frc.team3324.library.motorcontrollers.MetroTalonSRX

object Consts {
    object DriveTrain {
        // Motor ports
        const val LM_MOTOR = 1
        const val LU_MOTOR = 2
        const val LD_MOTOR = 3
        const val RM_MOTOR = 4
        const val RU_MOTOR = 5
        const val RD_MOTOR = 6

        // Encoder and Auto constants
        const val HIGH_GEAR_RATIO = 1.0 / (108800 / 12000)
        const val LOW_GEAR_RATIO = 1.0 / (160000 / 8160)
        const val WHEEL_DIAMETER_METERS = 6.125 / 39.36
        const val CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER_METERS
        const val PULSES = 1870 // 256 (pulses) * 4(quad, 4 ticks/pulse) * 3 * 25 (gear ratios)
        const val TICKS = PULSES * 4
        const val DISTANCE_PER_PULSE_HIGH = CIRCUMFERENCE * HIGH_GEAR_RATIO
        const val DISTANCE_PER_PULSE_LOW = CIRCUMFERENCE * LOW_GEAR_RATIO
        const val DISTANCE_BETWEEN_WHEELS = 0.61

        const val ksVolts = 0.189
        const val LOW_GEAR_KV = 5.22
        const val LOW_GEAR_KA = 0.892

        const val TRACK_WIDTH = 0.6446045

        const val OPTIMAL_KP = 1.64

        const val kRamseteB = 2.0
        const val kRamseteZeta = 0.7

        const val LOW_GEAR_MAX_VELOCITY = 12.0 / LOW_GEAR_KV
        const val LOW_GEAR_MAX_ACCELERATION = 12.0 / LOW_GEAR_KA

        val kDriveKinematics = DifferentialDriveKinematics(TRACK_WIDTH)


        const val GEARSHIFTER_FORWARD = 6
        const val GEARSHIFTER_REVERSE = 7

        val HIGH_GEAR = DoubleSolenoid.Value.kReverse
        val LOW_GEAR = DoubleSolenoid.Value.kForward

    }

    object Shooter {
        val LEFT_MOTOR = MetroSparkMAX(11, CANSparkMaxLowLevel.MotorType.kBrushless, 40)
        val RIGHT_MOTOR = MetroSparkMAX(10, CANSparkMaxLowLevel.MotorType.kBrushless, 40)

        const val GEAR_RATIO = 1.25

        const val Kv = 0.107 / 60 // Char tool gives Kv in terms of RPS so /60
        const val Ks = 0.101
        const val Kp = 1.06e-5
    }

    object Storage {
        val TOP_MOTOR = MetroTalonSRX(21, 20)
        val BOTTOM_MOTOR = MetroTalonSRX(7, 25)
    }

    object Climber {
        val LEFT_MOTOR = MetroTalonSRX(1, 40)
        val RIGHT_MOTOR = MetroTalonSRX(25, 40)
    }

    object Intake {
        val MOTOR = MetroSparkMAX(9, CANSparkMaxLowLevel.MotorType.kBrushless, 30)
    }

    object Pivot {
        val UPPER_LIMIT_SWITCH = DigitalInput(6)
        val LOWER_LIMIT_SWITCH = DigitalInput(5)

        val MOTOR = MetroSparkMAX(8, CANSparkMaxLowLevel.MotorType.kBrushless, 20)
    }

    object Vision {
        val TARGET_HEIGHT_FT = 7.5625 // height of target center
        val FRONT_CAMERA_ANGLE_DEG = 60.0
        val FRONT_CAMERA_HEIGHT_FT = 31.0/12.0

        val WIDTH = 320 // in pixels
        val HEIGHT = 180

        val VERTICAL_FOV_DEG = 34.3 // lifecam
        val HORIZONTAL_FOV_DEG = 61.0

        val HORIZONTAL_APP = HORIZONTAL_FOV_DEG / WIDTH // horizontal angle per pixel
        val VERTICAL_APP = VERTICAL_FOV_DEG / HEIGHT // horizontal angle per pixel

    }
}