package frc.team3324.robot

import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.wpilibj.*
import edu.wpi.first.wpilibj.XboxController.Button
import edu.wpi.first.wpilibj.controller.RamseteController
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward
import edu.wpi.first.wpilibj.controller.PIDController
import edu.wpi.first.wpilibj.geometry.Pose2d
import edu.wpi.first.wpilibj.geometry.Rotation2d
import edu.wpi.first.wpilibj.geometry.Translation2d
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.trajectory.Trajectory
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint
import edu.wpi.first.wpilibj.util.Units
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.RamseteCommand
import edu.wpi.first.wpilibj2.command.button.JoystickButton
import frc.team3324.library.commands.MotorCommand
import frc.team3324.robot.drivetrain.DriveTrain
import frc.team3324.robot.drivetrain.commands.teleop.Drive
// import frc.team3324.robot.intake.Intake
// import frc.team3324.robot.intake.Pivot
// import frc.team3324.robot.shooter.Shooter
// import frc.team3324.robot.shooter.commands.StopShooter
import frc.team3324.library.subsystems.MotorSubsystem
// import frc.team3324.robot.autocommands.GalacticAutoGroup
import frc.team3324.robot.drivetrain.commands.teleop.GyroTurn
// import frc.team3324.robot.shooter.commands.RunShooter
import frc.team3324.robot.util.*
import io.github.oblarg.oblog.Logger

class RobotContainer {
    //private val intake = Intake()
    //private val storage = MotorSubsystem(listOf(Consts.Storage.TOP_MOTOR, Consts.Storage.BOTTOM_MOTOR))
    val driveTrain = DriveTrain()
    //private val climber = MotorSubsystem(listOf(Consts.Climber.LEFT_MOTOR, Consts.Climber.RIGHT_MOTOR))
    //private val pivot = Pivot()
    //private val shooter = Shooter(Consts.Shooter.LEFT_MOTOR, Consts.Shooter.RIGHT_MOTOR)
    //private val frontCam = FrontCamera()
    //val trajectories = Trajectories()
    //private val rearCam = RearCamera()


    private val table = NetworkTableInstance.getDefault()

    private val primaryController = XboxController(0)
    private val secondaryController = XboxController(1)
    private val bongos = Joystick(2)

    private val primaryRightX: Double
        get() = primaryController.getX(GenericHID.Hand.kLeft)
    private val primaryLeftY: Double
        get() = primaryController.getY(GenericHID.Hand.kRight)

    private val primaryTriggerRight: Double
        get() = primaryController.getTriggerAxis(GenericHID.Hand.kRight)
    private val primaryTriggerLeft: Double
        get() = primaryController.getTriggerAxis(GenericHID.Hand.kLeft)

    private val secondaryRightX: Double
        get() = secondaryController.getX(GenericHID.Hand.kLeft)
    private val secondRightY: Double
        get() = secondaryController.getY(GenericHID.Hand.kRight)
    private val secondLeftY: Double
        get() = secondaryController.getY(GenericHID.Hand.kLeft)

    private val secondTriggerRight: Double
        get() = secondaryController.getTriggerAxis(GenericHID.Hand.kRight)
    private val secondTriggerLeft: Double
        get() = secondaryController.getTriggerAxis(GenericHID.Hand.kLeft)

    private val autoChooser = SendableChooser<Paths>()


    enum class Paths {
        GalacticA,
        GalacticB
    }



   init {
       Robot.light.set(true)
       Logger.configureLoggingAndConfig(this, true)
    //    Camera.schedule()
       driveTrain.defaultCommand = Drive(driveTrain, {primaryController.getY(GenericHID.Hand.kLeft)}, {primaryController.getX(GenericHID.Hand.kRight)})

    //    autoChooser.setDefaultOption("Galactic A", Paths.GalacticA)
    //    autoChooser.addOption("Galactic B", Paths.GalacticB)

       SmartDashboard.putData(autoChooser)

       //pivot.defaultCommand = MotorCommand(pivot, -0.10)
       configureButtonBindings()

   }

    private fun configureButtonBindings() {
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

    fun rumbleController(rumbleLevel: Double) {
        secondaryController.setRumble(GenericHID.RumbleType.kRightRumble, rumbleLevel)
    }

    // fun getAutoTrajectory(): Trajectory {
    //     val path = autoChooser.selected
    //     val color = rearCam.getRedOrBlue()
    //     var trajectory = trajectories.TestLine


    //     if (path == Paths.GalacticA) {
    //         SmartDashboard.putString("a OR b", "A")
    //         if (color == RearCamera.PathColor.RED) {
    //             trajectory = trajectories.GalacticAR
    //         } else {
    //             trajectory = trajectories.GalacticAB
    //         }
    //     } else if (path == Paths.GalacticB) {
    //         SmartDashboard.putString("a OR b", "B")
    //         if (color == RearCamera.PathColor.RED) {
    //             trajectory = trajectories.GalacticBR
    //         } else {
    //             trajectory = trajectories.GalacticBB
    //         }
    //     }

    //     return trajectory
    // }

    // fun getRamseteCommand(trajectory: Trajectory): Command {

    //     val disabledRamsete = object : RamseteController() {
    //         override fun calculate(currentPose: Pose2d, poseRef: Pose2d, linearVelocityRefMeters: Double, angularVelocityRefRadiansPerSecond: Double): ChassisSpeeds {
    //             return ChassisSpeeds(linearVelocityRefMeters, 0.0, angularVelocityRefRadiansPerSecond)
    //         }
    //     }

    //     val leftController = PIDController(0.1, 0.0, 0.0)
    //     val rightController = PIDController(0.1, 0.0, 0.0)

    //     val ramseteCommand = RamseteCommand(
    //             trajectory,
    //             driveTrain::pose,
    //             disabledRamsete, // RamseteController(Consts.DriveTrain.kRamseteB, Consts.DriveTrain.kRamseteZeta),
    //             SimpleMotorFeedforward(Consts.DriveTrain.ksVolts,
    //                     Consts.DriveTrain.LOW_GEAR_KV,
    //                     Consts.DriveTrain.LOW_GEAR_KA),
    //             Consts.DriveTrain.kDriveKinematics,
    //             driveTrain::autoWheelSpeeds,
    //             leftController,
    //             rightController,
    //             {leftVolts: Double, rightVolts: Double ->
    //                 driveTrain.tankDriveVolts(leftVolts, rightVolts)

    //                 SmartDashboard.putNumber("Left Measurement", driveTrain.autoWheelSpeeds.leftMetersPerSecond)
    //                 SmartDashboard.putNumber("Left Reference", leftController.setpoint)

    //                 SmartDashboard.putNumber("Right Measurement", driveTrain.autoWheelSpeeds.rightMetersPerSecond)
    //                 SmartDashboard.putNumber("Right Reference", rightController.setpoint)
    //             },
    //             arrayOf(driveTrain)
    //     )

    //     return ramseteCommand.andThen({driveTrain.tankDriveVolts(0.0, 0.0)}, arrayOf(driveTrain))
    // }

    // fun getAutoCommand(trajectory: Trajectory): Command {
    //     return GalacticAutoGroup(pivot, intake, storage, trajectory)
    // }
}
