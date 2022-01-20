package frc.team3324.robot;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.*;
import io.github.oblarg.oblog.Logger;

public class Robot extends TimedRobot {

    private Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    private AnalogInput ultrasonic = new AnalogInput(1);
    private Double depRangeInches = ultrasonic.getValue() * 0.125;

    static DigitalOutput light = new DigitalOutput(1);
    static RobotContainer robotContainer = new RobotContainer();
    static PowerDistribution pdp = new PowerDistribution();

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
        robotContainer.driveTrain.resetEncoders();
    }

    @Override 
    public void teleopPeriodic() {
        // periodic stuff here
    }
}
