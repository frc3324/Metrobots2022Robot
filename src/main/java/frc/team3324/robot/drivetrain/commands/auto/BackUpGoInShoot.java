package frc.team3324.robot.drivetrain.commands.auto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.team3324.library.commands.MotorCommand;
import frc.team3324.library.subsystems.MotorSubsystem;
import frc.team3324.robot.drivetrain.DriveTrain;
import frc.team3324.robot.drivetrain.commands.Drive;
import frc.team3324.robot.drivetrain.commands.DriveStraight;
import frc.team3324.robot.intake.Intake;
import frc.team3324.robot.intake.commands.SetIntakePosition;

public class BackUpGoInShoot extends SequentialCommandGroup {

   public BackUpGoInShoot(DriveTrain driveTrain, Intake intake, MotorSubsystem storage, MotorSubsystem shooter, MotorSubsystem feeder) {
       addCommands(
           new SetIntakePosition(intake, DoubleSolenoid.Value.kForward),
           new DriveStraight(driveTrain, -1.5).deadlineWith(new MotorCommand(intake, 1.0, true).alongWith(new MotorCommand(storage, 0.10, true))),
           new Drive(driveTrain, () -> {return 0.0;}, () -> {return -0.5;}).withTimeout(5.5),
           new Drive(driveTrain, () -> {return 0.0;}, () -> {return -0.5;}).alongWith(new MotorCommand(shooter, 0.85, true)).withTimeout(0.75),
           new Drive(driveTrain, () -> {return 0.0;}, () -> {return -0.25;}).alongWith(new MotorCommand(shooter, 0.85, true).alongWith(new MotorCommand(storage, 0.45, true).alongWith(new MotorCommand(feeder, 1.0, true))))
       );
   }
}
