package frc.team3324.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team3324.library.commands.MotorCommand;
import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.Drive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.Drive;
import frc.team3324.robot.drivetrain.commands.GyroTurnDiscrete;
import frc.team3324.robot.shooter.commands.RunShoot;
import frc.team3324.robot.intake.Intake;
import frc.team3324.robot.intake.commands.SuckIn;
import frc.team3324.robot.util.Consts;
import io.github.oblarg.oblog.Logger;

class RobotContainer {
    public DriveTrain driveTrain = new DriveTrain();
    //public Shoot shooter = new Shoot();
    // public Intake intake = new Intake();

    public MotorSubsystem intake = new MotorSubsystem(new SmartMotorController[]{Consts.Intake.LEFT_MOTOR}, 0.0);

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

        // Intake Controls
        new JoystickButton(primaryController, Button.kX.value).whileHeld(new MotorCommand(intake, 1.0, true, () -> {return false;}));
        new JoystickButton(primaryController, Button.kA.value).whileHeld(new MotorCommand(intake, -1.0, true, () -> {return false;}));

        // X: Turn robot 5 degrees
        new JoystickButton(primaryController, Button.kY.value).whileHeld(new GyroTurnDiscrete(
            driveTrain, 
            driveTrain.getGyro(),
            Consts.DriveTrain.GyroTurn_P,
            Consts.DriveTrain.GyroTurn_I,
            Consts.DriveTrain.GyroTurn_D,
            90.0)
            );
        
        //shooter.setDefaultCommand(new RunShoot(shooter, secondaryController));
        //intake.setDefaultCommand(new SuckIn(intake, secondaryController));
    }

    private void rumbleController(double rumbleLevel) {
        secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, rumbleLevel);
    }

    private void printControllerInputs() {
        SmartDashboard.putNumber("Primary Right X", primaryController.getRightX());
        SmartDashboard.putNumber("Primary Left Y", primaryController.getLeftY());
    }
}
