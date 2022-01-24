package frc.team3324.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.Drive;
import io.github.oblarg.oblog.Logger;

class RobotContainer {
    public DriveTrain driveTrain = new DriveTrain();

    private NetworkTableInstance table = NetworkTableInstance.getDefault();

    private XboxController primaryController = new XboxController(0);
    private XboxController secondaryController = new XboxController(1);

    private double primaryRightX = primaryController.getLeftX();
    private double primaryLeftY = primaryController.getLeftY();

    private double primaryTriggerRight = primaryController.getRightTriggerAxis();
    private double primaryTriggerLeft = primaryController.getLeftTriggerAxis();

    private double secondaryRightX = secondaryController.getLeftX();
    private double secondRightY = secondaryController.getRightY();
    private double secondLeftY = secondaryController.getLeftY();

    private double secondTriggerRight = secondaryController.getRightTriggerAxis();
    private double secondTriggerLeft = secondaryController.getLeftTriggerAxis();
    
    public RobotContainer() {
        Robot.light.set(true); // turn on robot light
        Logger.configureLoggingAndConfig(this, true);

       configureButtonBindings();
   }

    private void configureButtonBindings() {
        // Start accepting input from controller for drivetrain
        driveTrain.setDefaultCommand(new Drive(driveTrain, primaryController::getRightX, primaryController::getLeftY));
    }

    private void rumbleController(double rumbleLevel) {
        secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, rumbleLevel);
    }
}
