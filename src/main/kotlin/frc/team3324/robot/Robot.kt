package frc.team3324.robot

import edu.wpi.first.wpilibj.livewindow.LiveWindow
import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj.*
import frc.team3324.robot.util.TrajectoriesObject
import io.github.oblarg.oblog.Logger

class Robot: TimedRobot() {

    private val compressor = Compressor()
    private val ultrasonic = AnalogInput(1)
    private val depRangeInches: Double
        get() = ultrasonic.value.toDouble() * 0.125

    companion object {
        val light = DigitalOutput(1)
        val robotContainer = RobotContainer()
        val pdp = PowerDistributionPanel()
    }

    override fun robotInit() {
        LiveWindow.disableAllTelemetry()
        compressor.start()
    }

    fun enabledInit() {
    }

    override fun robotPeriodic() {
        CommandScheduler.getInstance().run()
        Logger.updateEntries()
        Logger.updateEntries()
    }

    override fun autonomousInit() {
        // val trajectory = robotContainer.getAutoTrajectory()
        //val trajectory = robotContainer.trajectories.GalacticAR
        robotContainer.driveTrain.resetEncoders()
        //robotContainer.driveTrain.resetOdometry(trajectory.initialPose)
        //robotContainer.getRamseteCommand(robotContainer.getAutoTrajectory()).schedule()
        //robotContainer.getAutoCommand(robotContainer.getAutoTrajectory()).schedule()
    }

    override fun teleopInit() {
        robotContainer.driveTrain.resetEncoders()
        robotContainer.driveTrain.resetOdometry(TrajectoriesObject.TestLine.trajectory.initialPose)
        enabledInit()
    }

    override fun teleopPeriodic() {
    }
}
