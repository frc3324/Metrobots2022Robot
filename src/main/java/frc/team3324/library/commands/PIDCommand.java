package frc.team3324.library.commands;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class PIDCommand extends CommandBase {
    private double integral = 0.0;
    private double lastPosition = 0.0;
    private Notifier notifier = new Notifier(this::executePID);

    double kP;
    double kI;
    double kD;
    double goal;
    double dt;
    Subsystem subsystem;
    DoubleSupplier measurement;
    DoubleConsumer useOutput;

    public PIDCommand(double kP, double kI, double kD, double goal, double dt, Subsystem subsystem, DoubleSupplier measurement, DoubleConsumer useOutput) {
        // assign constructor parameters to data members
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.goal = goal;
        this.dt = dt;
        this.subsystem = subsystem;
        this.measurement = measurement;
        this.useOutput = useOutput;

        addRequirements(subsystem);
    }

    @Override 
    public void initialize() {
        integral = 0.0;
        notifier.startPeriodic(dt);
    }

    private void executePID() {
        double position = measurement.getAsDouble();
        double error = goal - position;
        double deriv = position - lastPosition;

        integral += error;
        lastPosition = position;

        double output = (error * kP) + (integral * kI) - (deriv * kD);
        useOutput.accept(output);
    }

    @Override 
    public void end(boolean interrupted) {
        stopNotifier();
    }

    private void stopNotifier() {
        notifier.stop();
    }

    @Override 
    public boolean isFinished() {
        return false;
    }

}
