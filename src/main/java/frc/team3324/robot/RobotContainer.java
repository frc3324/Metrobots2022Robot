package frc.team3324.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team3324.library.commands.MotorCommand;
import frc.team3324.library.commands.MotorCommandContinuous;
import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.climber.Climber;
import frc.team3324.robot.climber.commands.Climb;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.Drive;
import frc.team3324.robot.intake.Intake;
import frc.team3324.robot.intake.commands.SetIntakePosition;
import frc.team3324.robot.shooter.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3324.robot.util.Consts;
import io.github.oblarg.oblog.Logger;

class RobotContainer {
    public DriveTrain driveTrain = new DriveTrain();
    public Intake intake = new Intake();
    public MotorSubsystem shooter = new Shooter();
    public Climber climber = new Climber();
    public MotorSubsystem feeder = new MotorSubsystem(new SmartMotorController[]{Consts.Shooter.FEEDER_MOTOR}, 0.0);
    public MotorSubsystem storage = new MotorSubsystem(new SmartMotorController[]{Consts.Storage.MOTOR}, 0.0);

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
        Logger.configureLoggingAndConfig(this, true);

        configureButtonBindings();
   }

    private void configureButtonBindings() {
        /*
         * PRIMARY CONTROLS
         */

        // Start accepting input from controller for drivetrain
        driveTrain.setDefaultCommand(new Drive(driveTrain, primaryController::getRightX, primaryController::getLeftY));
        
        
        // Climber commands / very sus
        climber.setDefaultCommand(new Climb(climber, primaryController, 0.8, 0.8));
        
        
        new JoystickButton(primaryController, Button.kRightBumper.value).whileHeld(new MotorCommand(climber.longHook, 0.8, true));
        new JoystickButton(primaryController, Button.kLeftBumper.value).whileHeld(new MotorCommand(climber.longHook, -0.8, true));



        // Vision Line Up Controls
        /*new JoystickButton(primaryController, Button.kY.value).whileHeld(new GyroTurnDiscrete(
            driveTrain, 
            driveTrain.getGyro(),
            Consts.DriveTrain.GyroTurn_P,
            Consts.DriveTrain.GyroTurn_I,
            Consts.DriveTrain.GyroTurn_D,
            90.0)
            );*/

        /*
         * SECONDARY CONTROLS
         */

        // Intake Controls
        new JoystickButton(secondaryController, Button.kB.value).whileHeld(new MotorCommand(intake, 0.8, true).alongWith(new MotorCommand(storage, -0.8, true).alongWith(new MotorCommand(feeder, -1.0, true)))); // intake
        new JoystickButton(secondaryController, Button.kY.value).whileHeld(new MotorCommand(intake, -0.8, true)); // outtake
        
        new JoystickButton(secondaryController, Button.kStart.value).whenPressed(new SetIntakePosition(intake, DoubleSolenoid.Value.kForward));
        new JoystickButton(secondaryController, Button.kBack.value).whenPressed(new SetIntakePosition(intake, DoubleSolenoid.Value.kReverse));

        // Storage Controls
        new JoystickButton(secondaryController, Button.kLeftBumper.value).whileHeld(new MotorCommand(storage, 0.8, true).alongWith(new MotorCommand(feeder, -1.0, true))); // goes down
        new JoystickButton(secondaryController, Button.kRightBumper.value).whileHeld(new MotorCommand(storage, -0.8, true).alongWith(new MotorCommand(feeder, -1.0, true))); // goes up

        // Shooter Controls
        new JoystickButton(secondaryController, Button.kX.value).toggleWhenPressed(new MotorCommandContinuous(shooter, () -> {return SmartDashboard.getNumber("Shooter Speed", 0.0);}, true));

        // Feeder Controls
        new JoystickButton(secondaryController, Button.kA.value).whileHeld(new MotorCommand(feeder, 1.0, true).alongWith(new MotorCommand(storage, -1.0, true)));

    }

    private void rumbleController(double rumbleLevel) {
        secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, rumbleLevel);
    }

    public void printControllerInputs() {
        SmartDashboard.putNumber("Primary Right X", primaryController.getRightX());
        SmartDashboard.putNumber("Primary Left Y", primaryController.getLeftY());
    }
}
