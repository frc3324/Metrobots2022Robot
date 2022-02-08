package frc.team3324.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import io.github.oblarg.oblog.Logger;

public class Robot extends TimedRobot {

    private Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    private AnalogInput ultrasonic = new AnalogInput(1);
    private Double depRangeInches = ultrasonic.getValue() * 0.125;


    static DigitalOutput light = new DigitalOutput(1);
    static RobotContainer robotContainer = new RobotContainer();
    static PowerDistribution pdp = new PowerDistribution();

    // called in both teleopInit and autoInit, is called whenever robot is enabled
    private void enabledInit() {
        // reset gyro
        robotContainer.driveTrain.getGyro().reset();
    }

    @Override 
    public void robotInit() {
        LiveWindow.disableAllTelemetry();

    }

    @Override 
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        Logger.updateEntries();
        Logger.updateEntries();
    }

    @Override 
    public void teleopInit() {
        enabledInit();

        SmartDashboard.putNumber("GyroTurn P", 0.0061253324);
        SmartDashboard.putNumber("GyroTurn I", 0.00001);
        SmartDashboard.putNumber("GyroTurn D", 0.0);

        robotContainer.driveTrain.resetEncoders();
    }

    @Override 
    public void teleopPeriodic() {
        // periodic stuff here
        SmartDashboard.putNumber("NAVX Yaw", robotContainer.driveTrain.getGyro().getYaw());
        SmartDashboard.putNumber("NAVX Angle", robotContainer.driveTrain.getGyro().getAngle());
    }
}
