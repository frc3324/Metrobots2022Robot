package frc.team3324.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.DriveStraight;
import frc.team3324.robot.util.Consts;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import io.github.oblarg.oblog.Logger;

public class Robot extends TimedRobot {

    private Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    static RobotContainer robotContainer = new RobotContainer();

    private Command autoCommand;

    // called in both teleopInit and autoInit, is called whenever robot is enabled
    private void enabledInit() {
        // reset drivetrain sensors
        robotContainer.driveTrain.getGyro().reset();
        robotContainer.driveTrain.resetEncoders();
    }

    @Override
    public void robotInit() {
        compressor.enableDigital();
        //compressor.disable();

        SmartDashboard.putNumber("Shooter Speed", 0.9);
    }

    @Override 
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        Logger.updateEntries();

        robotContainer.feeder.dashboardMotorCurrentDraw();
        robotContainer.intake.dashboardMotorCurrentDraw();
        //robotContainer.longHooks.dashboardMotorCurrentDraw();
        robotContainer.shortHook.dashboardMotorCurrentDraw();
        robotContainer.shooter.dashboardMotorCurrentDraw();
        robotContainer.storage.dashboardMotorCurrentDraw();
    }

    @Override
    public void teleopInit() {
        if (autoCommand != null) {
            autoCommand.cancel();
        }

        SmartDashboard.putNumber("AlignToHub kP", 0.002);
        SmartDashboard.putNumber("AlignToHub kI", 0.0);
        SmartDashboard.putNumber("AlignToHub kD", 0.0);

        SmartDashboard.putNumber("anglekP", 0.0);
        SmartDashboard.putNumber("anglekI", 0.0);
        SmartDashboard.putNumber("anglekD", 0.0);

        SmartDashboard.putNumber("distancekP", 0.0);
        SmartDashboard.putNumber("distancekI", 0.0);
        SmartDashboard.putNumber("distancekD", 0.0);

        enabledInit();
    }

    @Override
    public void autonomousInit() {
        enabledInit();

        autoCommand = robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (autoCommand != null) {
            autoCommand.schedule();
        }


    }

    @Override 
    public void teleopPeriodic() {
        // periodic stuff here
        SmartDashboard.putNumber("NAVX Yaw", robotContainer.driveTrain.getGyro().getYaw());
        SmartDashboard.putNumber("NAVX Angle", robotContainer.driveTrain.getGyro().getAngle());
        SmartDashboard.putBoolean("Compressor On", compressor.enabled());
        SmartDashboard.putNumber("Drivetrain Distance", robotContainer.driveTrain.getDistance());
        robotContainer.driveTrain.dashboardGyroValue();
    }
}
