package frc.team3324.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.DriveStraight;
import frc.team3324.robot.drivetrain.commands.auto.ShootBackUp;
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

        SmartDashboard.putNumber("Shooter Speed", 0.85);


    }

    @Override 
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        Logger.updateEntries();
    }

    @Override
    public void teleopInit() {
        if (autoCommand != null) {
            autoCommand.cancel();
        }

        SmartDashboard.putNumber("GyroTurn P", Consts.DriveTrain.GyroTurn_P);
        SmartDashboard.putNumber("GyroTurn I", Consts.DriveTrain.GyroTurn_I);
        SmartDashboard.putNumber("GyroTurn D", Consts.DriveTrain.GyroTurn_D);

        SmartDashboard.putNumber("DriveStraight P", Consts.DriveTrain.DriveStraight_P);
        SmartDashboard.putNumber("DriveStraight I", Consts.DriveTrain.DriveStraight_I);
        SmartDashboard.putNumber("DriveStraight D", Consts.DriveTrain.DriveStraight_D);

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

        robotContainer.longHooks.dashboardLimitSwitches();
    }
}
