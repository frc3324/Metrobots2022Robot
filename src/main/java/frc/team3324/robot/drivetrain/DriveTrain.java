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
    // old values for PID are below, delete the new ones if they end up causing issues
    PIDController leftPIDController = new PIDController(2.95, 0.0, 0.0);
    PIDController rightPIDController = new PIDController(2.95, 0.0, 0.0);
    // PIDController leftPIDController = new PIDController(0.00521, 0.000012, 0);
    // PIDController rightPIDController = new PIDController(0.00521, 0.000012, 0); 
    private AHRS gyro = new AHRS(SPI.Port.kMXP);

    // Motor Objects
    private CANSparkMax lmMotor = new CANSparkMax(Consts.DriveTrain.L_MIDDLE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax luMotor = new CANSparkMax(Consts.DriveTrain.L_FRONT_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax ldMotor = new CANSparkMax(Consts.DriveTrain.L_BACK_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);

    private CANSparkMax rmMotor = new CANSparkMax(Consts.DriveTrain.R_MIDDLE_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax ruMotor = new CANSparkMax(Consts.DriveTrain.R_FRONT_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANSparkMax rdMotor = new CANSparkMax(Consts.DriveTrain.R_BACK_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);

    private RelativeEncoder rightEncoder = rmMotor.getEncoder();
    private RelativeEncoder leftEncoder = lmMotor.getEncoder();

    private DifferentialDrive drive = new DifferentialDrive(rmMotor, lmMotor);

    private DifferentialDriveOdometry diffDriveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(-1.0 * gyro.getYaw()));

    /*
     * CONSTRUCTOR
     */
    public DriveTrain() {

        // Initialize DT Motors
        lmMotor.restoreFactoryDefaults();
        luMotor.restoreFactoryDefaults();
        ldMotor.restoreFactoryDefaults();

        rmMotor.restoreFactoryDefaults();
        ruMotor.restoreFactoryDefaults();
        rdMotor.restoreFactoryDefaults();

        rightEncoder.setPosition(0.0);
        leftEncoder.setPosition(0.0);

        // Set encoder converstion ratio
        rightEncoder.setPositionConversionFactor(Consts.DriveTrain.CONVERSION_RATIO);
        leftEncoder.setPositionConversionFactor(Consts.DriveTrain.CONVERSION_RATIO);

        lmMotor.setOpenLoopRampRate(0.25);
        rmMotor.setOpenLoopRampRate(0.25);

        // Current Limits
        rmMotor.setSmartCurrentLimit(40);
        lmMotor.setSmartCurrentLimit(40);
        rmMotor.setSecondaryCurrentLimit(40.0);
        lmMotor.setSecondaryCurrentLimit(40.0);

        // Set followers
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

    public AHRS getGyro() {
        return this.gyro;
    }

    @Log
    public double getLeftEncoderVelocity() {
        return leftEncoder.getVelocity();
    }

    @Log
    public double getLeftEncoderPosition() {
        return leftEncoder.getPosition();
    }

    @Log
    public double getRightEncoderVelocity() {
        return rightEncoder.getVelocity();
    }

    @Log
    public double getRightEncoderPosition() {
        return rightEncoder.getPosition();
    }

    @Log
    public double getVelocity() {
        return (getLeftEncoderVelocity() - getRightEncoderVelocity()) / 2.0;
    }

    @Log
    public double getPosition() {
        // subtracted because one is negative, position is in terms of output shaft due to conversion ratio
        return (getLeftEncoderPosition() - getRightEncoderPosition()) / 2.0;
    }

    public double getDistance() {
        return this.getPosition() * Consts.DriveTrain.CIRCUMFERENCE_METERS;
    }

    @Log
    public double getYaw() {
        return gyro.getYaw();
    }

    @Log
    public double getFusedYaw() {
        return gyro.getFusedHeading();
    }

    public Pose2d getPose() {
        return diffDriveOdometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(getLeftEncoderVelocity(), getRightEncoderVelocity());
    }

    public DifferentialDriveWheelSpeeds getAutoWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(getLeftEncoderVelocity(), -1.0 * getRightEncoderVelocity());
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

    public void setMaxOutput(double output) {
        drive.setMaxOutput(output);
    }

    @Override 
    public void periodic() {
        diffDriveOdometry.update(Rotation2d.fromDegrees(-gyro.getYaw()), getLeftEncoderPosition(), -1.0 * getRightEncoderPosition());
        // SmartDashboard.putString("Drivetrain Pose", getPose().toString());
        // SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());

        SmartDashboard.putNumber("DT Distance", this.getDistance());

        double currentVelocity = getVelocity();
    }

    private void curvatureDrive(double xSpeed, double ySpeed, boolean quickTurn) {
        drive.curvatureDrive(xSpeed, ySpeed, quickTurn);
    }

    public void curvatureDrive(double xSpeed, double ySpeed) {
        curvatureDrive(xSpeed, ySpeed, true);
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

    public void dashboardGyroValue() {
        SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());
        SmartDashboard.putNumber("Gyro Roll", gyro.getRoll());
        SmartDashboard.putNumber("Gyro Pitch", gyro.getPitch());
    }

}
