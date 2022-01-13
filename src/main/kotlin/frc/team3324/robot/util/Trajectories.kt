package frc.team3324.robot.util

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward
import edu.wpi.first.wpilibj.geometry.Pose2d
import edu.wpi.first.wpilibj.geometry.Rotation2d
import edu.wpi.first.wpilibj.geometry.Translation2d
import edu.wpi.first.wpilibj.trajectory.Trajectory
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint
import edu.wpi.first.wpilibj.util.Units

class Trajectories {

    private val autoVoltageConstraint = DifferentialDriveVoltageConstraint(
            SimpleMotorFeedforward(Consts.DriveTrain.ksVolts,
                    Consts.DriveTrain.LOW_GEAR_KV,
                    Consts.DriveTrain.LOW_GEAR_KA),
            Consts.DriveTrain.kDriveKinematics, 10.0) // maxVoltage of 10 allows for head room

    private val config = (TrajectoryConfig(1.0, 2.0)
            .setKinematics(Consts.DriveTrain.kDriveKinematics)
            .addConstraint(autoVoltageConstraint))

    val TestLine = TrajectoryGenerator.generateTrajectory(
            Pose2d(Units.feetToMeters(0.0), Units.feetToMeters(7.5), Rotation2d.fromDegrees(180.0)),
            listOf(Translation2d(Units.feetToMeters(5.0), Units.feetToMeters(7.5))),
            Pose2d(Units.feetToMeters(10.0), Units.feetToMeters(7.5), Rotation2d.fromDegrees(180.0)), config)

    fun initGalacticAR(config: TrajectoryConfig): Trajectory {
        val startPose = Pose2d(Units.feetToMeters(5.25), Units.feetToMeters(7.5), Rotation2d.fromDegrees(160.0)) // should x be half of robot length?

        val interiorPoses = listOf(
                Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(15.0-7.5)),  // ball 1
                Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(15.0-5.0)), // ball 2
                Translation2d(Units.feetToMeters(15.0), Units.feetToMeters(15.0-11.0)), // ball 3
                // Translation2d(Units.feetToMeters(24.5), Units.feetToMeters(15.0-7.5)) // line up for finish
        )

        val endPose = Pose2d(Units.feetToMeters(27.5), Units.feetToMeters(15.0-12.5), Rotation2d.fromDegrees(180.0)) // end at 25 to not slam into wall

        val trajectory = TrajectoryGenerator.generateTrajectory(startPose, interiorPoses, endPose, config)

        return trajectory
    }

    fun initGalacticAB(config: TrajectoryConfig): Trajectory {
        // private val startPose = Pose2d(0.0, Units.feetToMeters(15.0-2.5), Rotation2d.fromDegrees(0.0))
        val startPose = Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), Rotation2d.fromDegrees(-180.0)) // should x be half of robot length?

        val interiorPoses = listOf(
                // Translation2d(Units.feetToMeters(6.0), Units.feetToMeters(15.0-7.5)), // move out of start
                Translation2d(Units.feetToMeters(14.0), Units.feetToMeters(15.0-2.5)),  // ball 1
                Translation2d(Units.feetToMeters(16.5), Units.feetToMeters(15.0-9.5)), // ball 2
                Translation2d(Units.feetToMeters(21.0), Units.feetToMeters(15.0-6.5)) // ball 3
        )

        val endPose = Pose2d(Units.feetToMeters(25.0), Units.feetToMeters(15.0-6.5), Rotation2d.fromDegrees(-180.0))

        val trajectory = TrajectoryGenerator.generateTrajectory(startPose, interiorPoses, endPose, config)

        return trajectory
    }

    fun initGalacticBR(config: TrajectoryConfig): Trajectory {
        // private val startPose = Pose2d(0.0, Units.feetToMeters(15.0-10.0), Rotation2d.fromDegrees(0.0))
        val startPose = Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), Rotation2d.fromDegrees(-180.0)) // should x be half of robot length?

        val interiorPoses = listOf(
                // Translation2d(Units.feetToMeters(6.0), Units.feetToMeters(15.0-7.5)), // move out of start
                Translation2d(Units.feetToMeters(7.5), Units.feetToMeters(15.0-10.0)),  // ball 1
                Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(15.0-5.0)), // ball 2
                Translation2d(Units.feetToMeters(17.5), Units.feetToMeters(15.0-10.5)), // ball 3
                // Translation2d(Units.feetToMeters(24.5), Units.feetToMeters(15.0-7.5)) // line up for finish
        )

        val endPose = Pose2d(Units.feetToMeters(26.5), Units.feetToMeters(15.0-10.0), Rotation2d.fromDegrees(-180.0))

        val trajectory = TrajectoryGenerator.generateTrajectory(startPose, interiorPoses, endPose, TrajectoriesObject.config)

        return trajectory
    }

    fun initGalacticBB(config: TrajectoryConfig): Trajectory {
        //private val startPose = Pose2d(0.0, Units.feetToMeters(15.0-5.0), Rotation2d.fromDegrees(0.0))
        val startPose = Pose2d(Units.feetToMeters(2.5), Units.feetToMeters(7.5), Rotation2d.fromDegrees(-180.0)) // should x be half of robot length?


        val interiorPoses = listOf(
                // Translation2d(Units.feetToMeters(6.0), Units.feetToMeters(15.0-7.5)), // move out of start
                Translation2d(Units.feetToMeters(15.0), Units.feetToMeters(15.0-5.0)),  // ball 1
                Translation2d(Units.feetToMeters(17.5), Units.feetToMeters(15.0-10.0)), // ball 2
                Translation2d(Units.feetToMeters(22.5), Units.feetToMeters(15.0-5.0)), // ball 3
                // Translation2d(Units.feetToMeters(24.5), Units.feetToMeters(15.0-7.5)) // line up for finish
        )

        val endPose = Pose2d(Units.feetToMeters(25.0), Units.feetToMeters(15.0-5.0), Rotation2d.fromDegrees(-180.0))

        val trajectory = TrajectoryGenerator.generateTrajectory(startPose, interiorPoses, endPose, TrajectoriesObject.config)

        return trajectory
    }

    var GalacticAR = TestLine
    var GalacticAB = TestLine
    var GalacticBR = TestLine
    var GalacticBB = TestLine

    init {
        config.setReversed(true)
        System.out.println("Initializing Trajectories....")
        GalacticAR = initGalacticAR(config)
        System.out.println("Initialized AR......................................................")
        GalacticAB = initGalacticAB(config)
        System.out.println("Initialized AB......................................................")
        GalacticBR = initGalacticBR(config)
        System.out.println("Initialized BR......................................................")
        GalacticBB = initGalacticBB(config)
        System.out.println("Initialized BB......................................................")
    }
}