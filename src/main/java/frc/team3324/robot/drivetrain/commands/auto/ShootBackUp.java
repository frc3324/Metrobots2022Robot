package frc.team3324.robot.drivetrain.commands.auto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team3324.library.commands.MotorCommand;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.DriveStraight;
import frc.team3324.robot.intake.Intake;
import frc.team3324.robot.intake.commands.SetIntakePosition;

public class ShootBackUp extends SequentialCommandGroup {

   public ShootBackUp(DriveTrain driveTrain, Intake intake, MotorSubsystem storage, MotorSubsystem shooter, MotorSubsystem feeder) {
       addCommands(
           new MotorCommand(shooter, 0.9, true).withTimeout(1.0),
           new MotorCommand(shooter, 0.9, true).alongWith(new MotorCommand(storage, 0.8, true)).alongWith(new MotorCommand(feeder, 1.0, true)).withTimeout(2.0),
           new DriveStraight(driveTrain, -2.5).deadlineWith(new MotorCommand(intake, -1.0, true)).deadlineWith(new MotorCommand(storage, 0.2, true)).deadlineWith(new MotorCommand(feeder, -1.0, true))
       );
   }
}
