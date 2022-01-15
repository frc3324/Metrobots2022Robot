package frc.team3324.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.team3324.library.commands.MotorCommand;
import frc.team3324.robot.drivetrain.DriveTrain;
// import frc.team3324.robot.drivetrain.commands.teleop.Drive;
// import frc.team3324.library.subsystems.MotorSubsystem;
// import frc.team3324.robot.drivetrain.commands.teleop.GyroTurn;
// import frc.team3324.robot.util.*;
// import io.github.oblarg.oblog.Logger;
import frc.team3324.robot.commands.*;

class RobotContainer {
    // TODO: make drivetrain class
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

    private SendableChooser<Paths> autoChooser = new SendableChooser<>();

    public enum Paths {
        GalacticA,
        GalacticB
    }
    
    public RobotContainer() {
        Robot.light.set(true);
        //Logger.configureLoggingAndConfig(this, true);
        // TODO: Find out why defaultCommand and Drive() are bad (probably an easy fix I'm just dumb lol)
        driveTrain.defaultCommand = Drive();
        // ? It does not like the hand enum for some reason and I cannot figure out why
        driveTrain.setDefaultCommand(driveTrain, primaryController.getY(GenericHID.Hand.kLeft), primaryController.getX(GenericHID.Hand.kRight));
        
       SmartDashboard.putData(autoChooser);

       configureButtonBindings();

   }

    private void configureButtonBindings() {
//        JoystickButton(primaryController, Button.kBumperLeft.value).whenPressed(MotorCommand(pivot, -0.4, finishedCondition = {!pivot.lowerLimitSwitch.get()}).andThen(MotorCommand(pivot, -0.15)))
//        JoystickButton(primaryController, Button.kBumperRight.value).whenPressed(MotorCommand(pivot, 0.4, finishedCondition = {!pivot.upperLimitSwitch.get()}).andThen(MotorCommand(pivot, 0.15)))


        // JoystickButton(primaryController, Button.kX.value).whenPressed(rearCam::getRedOrBlue)

        // JoystickButton(primaryController, Button.kA.value).whileHeld(MotorCommand(climber, -1.0, 0)) // run the left climber motor
        // JoystickButton(primaryController, Button.kB.value).whileHeld(MotorCommand(climber, -1.0, 1)) // run the right climber motor
        // JoystickButton(primaryController, Button.kStickLeft.value).whileHeld(MotorCommand(climber, 1.0, 0, false))
        // JoystickButton(primaryController, Button.kStickRight.value).whileHeld(MotorCommand(climber,  1.0, 1, false))
        // JoystickButton(primaryController, Button.kY.value).whileHeld(GyroTurn(
        //         driveTrain,
        //         1.0/70.0,
        //         (Consts.DriveTrain.ksVolts + 0.3)/12,
        //         {frontCam.lineUpAngle()},
        //         {input -> driveTrain.curvatureDrive(0.0, input, true)}
        // ))


        // JoystickButton(secondaryController, Button.kX.value).whenPressed(RunShooter(shooter, frontCam,false))
        // JoystickButton(secondaryController, Button.kY.value).whenPressed(RunShooter(shooter, frontCam,true))
        // JoystickButton(secondaryController, Button.kBumperLeft.value).whileHeld(MotorCommand(storage, 0.799999998, 0, false).alongWith(MotorCommand(storage, 0.6, 1, false)))
        // JoystickButton(secondaryController, Button.kBumperRight.value).whileHeld(MotorCommand(storage, -0.799999998, 0, false).alongWith(MotorCommand(storage, -0.6, 1, false)))

        // JoystickButton(secondaryController, Button.kA.value).whileHeld(MotorCommand(intake, -1.0))
        // JoystickButton(secondaryController, Button.kB.value).whileHeld(MotorCommand(intake, 1.0))
        // JoystickButton(secondaryController, Button.kStart.value).whenPressed(StopShooter(shooter))
        /*JoystickButton(bongos, 1).whileHeld(MotorCommand(storage, 1.066666664, 0, false).alongWith(MotorCommand(storage, 0.8, 1, false)))
        JoystickButton(bongos, 2).whileHeld(MotorCommand(storage, -1.066666664, 0, false).alongWith(MotorCommand(storage, -0.8, 1, false)))
        JoystickButton(bongos, 4).whileHeld(MotorCommand(intake, 1.0))
        JoystickButton(bongos, 3).whileHeld(MotorCommand(intake, -1.0))
        JoystickButton(bongos, 10).whenPressed(RunShooter(shooter, {cameraTable.getEntry("targetArea").getDouble(3800.0)}, false).withTimeout(   10.0))
*/
    }

    private void rumbleController(double rumbleLevel) {
        secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, rumbleLevel);
    }
}
