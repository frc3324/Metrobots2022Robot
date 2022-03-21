package frc.team3324.robot.drivetrain.commands.auto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.team3324.library.commands.MotorCommand;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.DriveStraight;
import frc.team3324.robot.intake.Intake;
import frc.team3324.robot.intake.commands.SetIntakePosition;

public class ShootWaitBackUp extends SequentialCommandGroup {

   public ShootWaitBackUp(DriveTrain driveTrain, Intake intake, MotorSubsystem storage, MotorSubsystem shooter, MotorSubsystem feeder) {
       addCommands(
           new MotorCommand(shooter, 0.9, true).withTimeout(1.0),
           new SetIntakePosition(intake, DoubleSolenoid.Value.kForward).alongWith(new MotorCommand(shooter, 0.8, true).alongWith(new MotorCommand(storage, 0.9, true)).alongWith(new MotorCommand(feeder, 1.0, true)).withTimeout(2.0)),
           new WaitCommand(10.0),
           new DriveStraight(driveTrain, -2.5)
       );
   }
}
