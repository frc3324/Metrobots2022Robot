package frc.team3324.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import io.github.oblarg.oblog.Logger;

public class Robot extends TimedRobot {

    private Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    static RobotContainer robotContainer = new RobotContainer();

    // called in both teleopInit and autoInit, is called whenever robot is enabled
    private void enabledInit() {
        // reset drivetrain sensors
        robotContainer.driveTrain.getGyro().reset();
        robotContainer.driveTrain.resetEncoders();
    }

    @Override
    public void robotInit() {
        compressor.enableDigital();
        SmartDashboard.putNumber("Shooter Speed", 0.0);
    }

    @Override 
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        Logger.updateEntries();
    }

    @Override
    public void teleopInit() {
        SmartDashboard.putNumber("Drivetrain P", 0.0);
        SmartDashboard.putNumber("Drivetrain I", 0.0);
        SmartDashboard.putNumber("Drivetrain D", 0.0);

        enabledInit();
    }

    @Override
    public void autonomousInit() {
        enabledInit();
    }

    @Override 
    public void teleopPeriodic() {
        // periodic stuff here
        SmartDashboard.putNumber("NAVX Yaw", robotContainer.driveTrain.getGyro().getYaw());
        SmartDashboard.putNumber("NAVX Angle", robotContainer.driveTrain.getGyro().getAngle());
        SmartDashboard.putBoolean("Compressor On", compressor.enabled());
        robotContainer.driveTrain.dashboardGyroValue();
    }
}
