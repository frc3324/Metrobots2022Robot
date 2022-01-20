package frc.team3324.robot.drivetrain;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.team3324.robot.util.Consts;

import io.github.oblarg.oblog.annotations.Log;

public class DriveTrain extends SubsystemBase {

    /* 
     * INSTANCE VARIABLES
     */
    PIDController leftPIDController = new PIDController(2.95, 0.0, 0.0);
    PIDController rightPIDController = new PIDController(2.95, 0.0, 0.0);

    private AHRS gyro = new AHRS(SPI.Port.kMXP);

    // Motor Objects
    private CANSparkMax lmMotor = new CANSparkMax(Consts.DriveTrain.LM_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax luMotor = new CANSparkMax(Consts.DriveTrain.LU_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax ldMotor = new CANSparkMax(Consts.DriveTrain.LD_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);

    private CANSparkMax rmMotor = new CANSparkMax(Consts.DriveTrain.RM_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax ruMotor = new CANSparkMax(Consts.DriveTrain.RU_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax rdMotor = new CANSparkMax(Consts.DriveTrain.RD_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);

    private RelativeEncoder rightEncoder = rmMotor.getEncoder();
    private RelativeEncoder leftEncoder = lmMotor.getEncoder();

    private DifferentialDrive drive = new DifferentialDrive(rmMotor, lmMotor);

    private DifferentialDriveOdometry diffDriveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(-1.0 * gyro.getYaw()));

    double activeConversionRatio = Consts.DriveTrain.DISTANCE_PER_PULSE_LOW;

    /*
     * CONSTRUCTOR
     */
    public DriveTrain() {
        lmMotor.setOpenLoopRampRate(2.0);
        rmMotor.setOpenLoopRampRate(2.0);

        lmMotor.restoreFactoryDefaults();
        luMotor.restoreFactoryDefaults();
        ldMotor.restoreFactoryDefaults();

        rmMotor.restoreFactoryDefaults();
        ruMotor.restoreFactoryDefaults();
        rdMotor.restoreFactoryDefaults();

        rightEncoder.setPosition(0.0);
        leftEncoder.setPosition(0.0);

        rmMotor.setSmartCurrentLimit(40);
        lmMotor.setSmartCurrentLimit(40);
        rmMotor.setSecondaryCurrentLimit(40.0);
        lmMotor.setSecondaryCurrentLimit(40.0);

        ruMotor.follow(rmMotor, false);
        rdMotor.follow(rmMotor, false);

        luMotor.follow(lmMotor);
        ldMotor.follow(lmMotor);
        setBrakeMode();

        ruMotor.burnFlash();
        rmMotor.burnFlash();
        rdMotor.burnFlash();

        lmMotor.burnFlash();
        luMotor.burnFlash();
        ldMotor.burnFlash();

        drive.setSafetyEnabled(true);
    }

    /*
     * GETTERS/SETTERS
     */

    @Log
    public double getLeftEncoderSpeed() {
        return leftEncoder.getVelocity() * (1 / 60.0) * activeConversionRatio;
    }

    @Log
    public double getLeftEncoderPosition() {
        return leftEncoder.getPosition() * activeConversionRatio;
    }

    @Log
    public double getRightEncoderSpeed() {
        return rightEncoder.getVelocity() * (1 / 60.0) * activeConversionRatio;
    }

    @Log
    public double getRightEncoderPosition() {
        return rightEncoder.getPosition() * activeConversionRatio;
    }

    @Log
    public double getVelocity() {
        return (getRightEncoderSpeed() - getLeftEncoderSpeed()) / 2.0;
    }

    @Log
    public double getPosition() {
        return (getRightEncoderPosition() - getLeftEncoderPosition()) / 2.0;
    }

    @Log
    public double getYaw() {
        return -1.0 * gyro.getYaw();
    }

    @Log
    public double getFusedYaw() {
        return -1.0 * gyro.getFusedHeading();
    }

    public Pose2d getPose() {
        return diffDriveOdometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(getLeftEncoderSpeed(), getRightEncoderSpeed());
    }

    public DifferentialDriveWheelSpeeds getAutoWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(getLeftEncoderSpeed(), -1.0 * getRightEncoderSpeed());
    }

    public boolean getSafety() {
        return drive.isSafetyEnabled();
    }

    public void setSafety(boolean bool) {
        drive.setSafetyEnabled(bool);
    }


    private void setBrakeMode() {
        rmMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        ruMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rdMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        lmMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        luMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        ldMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void resetGyro() {
        gyro.reset();
    }

    public void resetOdometry(Pose2d newPose) {
        diffDriveOdometry.resetPosition(newPose, new Rotation2d(gyro.getYaw()));
    }

    public void resetEncoders() {
        rightEncoder.setPosition(0.0);
        leftEncoder.setPosition(0.0);
    }

    @Override 
    public void periodic() {
        diffDriveOdometry.update(Rotation2d.fromDegrees(-gyro.getYaw()), getLeftEncoderPosition(), -1.0 * getRightEncoderPosition());
        SmartDashboard.putString("Drivetrain Pose", getPose().toString());
        SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());

        double currentVelocity = getVelocity();
    }

    private void curvatureDrive(double xSpeed, double ySpeed, boolean quickTurn) {
        drive.curvatureDrive(xSpeed, ySpeed, quickTurn);
    }

    public void curvatureDrive(double xSpeed, double ySpeed) {
        if (xSpeed < 0.0025) {
            curvatureDrive(xSpeed, -ySpeed * 0.35, true);
        } else {
            curvatureDrive(xSpeed, -ySpeed * 0.7, false);
        }
    }


    public void setCoastMode() {
        rmMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        lmMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        ruMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        luMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);

    }

    private void tankDriveVolts(double leftVolts, double rightVolts) {
        drive.feed();
        lmMotor.setVoltage(leftVolts);
        rmMotor.setVoltage(-rightVolts);
    }
}
