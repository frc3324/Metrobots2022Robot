package frc.team3324.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.team3324.library.commands.MotorCommand;
import frc.team3324.library.motorcontrollers.SmartMotorController;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.climber.LongHooks;
import frc.team3324.robot.climber.ShortHooks;
import frc.team3324.robot.climber.commands.ClimbLongHooks;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.Drive;
import frc.team3324.robot.drivetrain.commands.auto.BackUpGoInShoot;
import frc.team3324.robot.drivetrain.commands.auto.ShootBackUp;
import frc.team3324.robot.drivetrain.commands.auto.ShootWaitBackUp;
import frc.team3324.robot.intake.Intake;
import frc.team3324.robot.intake.commands.RunIntake;
import frc.team3324.robot.intake.commands.SetIntakePosition;
import frc.team3324.robot.shooter.Shooter;
import frc.team3324.robot.shooter.commands.Shoot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3324.robot.util.Consts;
import io.github.oblarg.oblog.Logger;

class RobotContainer {
    public DriveTrain driveTrain = new DriveTrain();
    public Intake intake = new Intake();
    public MotorSubsystem shooter = new Shooter();
    
    public LongHooks longHooks = new LongHooks();
    public MotorSubsystem shortHook = new ShortHooks();

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

    SendableChooser<Command> autoChooser;
    
    public RobotContainer() {
        Logger.configureLoggingAndConfig(this, true);

        feeder.setDefaultCommand(new MotorCommand(feeder, -0.1, true));

        this.autoChooser = new SendableChooser<>();
        autoChooser.setDefaultOption("Back Up, Go In, Shoot 2", new BackUpGoInShoot(driveTrain, intake, storage, shooter, feeder));
        autoChooser.addOption("Shoot 1, Back Up", new ShootBackUp(driveTrain, intake, storage, shooter, feeder));
        autoChooser.addOption("Shoot 1, Wait 10s, Back Up", new ShootWaitBackUp(driveTrain, intake, storage, shooter, feeder));
        autoChooser.addOption("No Auto", new WaitCommand(15.0));

        SmartDashboard.putData(autoChooser);
    
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        /*
         * PRIMARY CONTROLS
         */

        // Start accepting input from controller for drivetrain
        driveTrain.setDefaultCommand(new Drive(driveTrain, primaryController::getRightX, primaryController::getLeftY));
        
        // Long Hook Controls
        new JoystickButton(primaryController, Button.kRightBumper.value).whileHeld(new ClimbLongHooks(longHooks, 0.8, true));
        new JoystickButton(primaryController, Button.kLeftBumper.value).whileHeld(new ClimbLongHooks(longHooks, -0.8, false));

        // Short Hook Controls
        new JoystickButton(primaryController, Button.kB.value).whileHeld(new MotorCommand(shortHook, -0.2, 0, true));
        new JoystickButton(primaryController, Button.kA.value).whileHeld(new MotorCommand(shortHook, 0.2, 0, true));
        new JoystickButton(primaryController, Button.kX.value).whileHeld(new MotorCommand(shortHook, 0.2, 1, true));
        new JoystickButton(primaryController, Button.kY.value).whileHeld(new MotorCommand(shortHook, -0.2, 1, true));

        new JoystickButton(primaryController, Button.kBack.value).whileHeld(new MotorCommand(shortHook, 0.3, true));
        new JoystickButton(primaryController, Button.kStart.value).whileHeld(new MotorCommand(shortHook, -0.3, true));
    
        // Vision Line Up Controls
        //new JoystickButton(primaryController, Button.kBack.value).whenPressed(new ShootThenBackUp(driveTrain, intake, storage, shooter, feeder));

        /*
         * SECONDARY CONTROLS
         */

        // Intake Controls
        // new JoystickButton(secondaryController, Button.kB.value).whileHeld(new MotorCommand(intake, 1.0, true).alongWith(new MotorCommand(storage, 0.8, true).alongWith(new MotorCommand(feeder, -1.0, true)))); // intake
        // new JoystickButton(secondaryController, Button.kY.value).whileHeld(new MotorCommand(intake, -1.0, true)); // outtake

        new JoystickButton(secondaryController, Button.kB.value).whileHeld(new RunIntake(intake, 1.0).alongWith(new MotorCommand(storage, 0.8, true).alongWith(new MotorCommand(feeder, -1.0, true)))); // intake
        new JoystickButton(secondaryController, Button.kY.value).whileHeld(new RunIntake(intake, -1.0)); // outtake
        
        new JoystickButton(secondaryController, Button.kStart.value).whenPressed(new SetIntakePosition(intake, DoubleSolenoid.Value.kForward));
        new JoystickButton(secondaryController, Button.kBack.value).whenPressed(new SetIntakePosition(intake, DoubleSolenoid.Value.kReverse));

        // Storage Controls
        new JoystickButton(secondaryController, Button.kLeftBumper.value).whileHeld(new MotorCommand(storage, -1.0, true).alongWith(new MotorCommand(feeder, -1.0, true))); // goes down
        new JoystickButton(secondaryController, Button.kRightBumper.value).whileHeld(new MotorCommand(storage, 1.0, true).alongWith(new MotorCommand(feeder, -1.0, true))); // goes up

        // Shooter Controls
        new JoystickButton(secondaryController, Button.kX.value).toggleWhenPressed(new Shoot(shooter, secondaryController, () -> {return SmartDashboard.getNumber("Shooter Speed", 0.0);}));

        // Feeder Controls
        new JoystickButton(secondaryController, Button.kA.value).whileHeld(new MotorCommand(feeder, 1.0, true).alongWith(new MotorCommand(storage, 1.0, true)));

    }

    private void rumbleController(double rumbleLevel) {
        secondaryController.setRumble(GenericHID.
        RumbleType.kRightRumble, rumbleLevel);
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
        // return new ShootBackUp(driveTrain, intake, storage, shooter, feeder);
        // return new BackUpGoInShoot(driveTrain, intake, storage, shooter, feeder);
    }

    public void printControllerInputs() {
        SmartDashboard.putNumber("Primary Right X", primaryController.getRightX());
        SmartDashboard.putNumber("Primary Left Y", primaryController.getLeftY());
    }
}
