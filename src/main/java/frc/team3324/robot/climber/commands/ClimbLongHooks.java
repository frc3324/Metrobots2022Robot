// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team3324.robot.climber.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.team3324.robot.climber.LongHooks;

public class ClimbLongHooks extends ParallelCommandGroup {
    LongHooks longHooks;
    double speed;
    boolean stopAtLimits;

    public ClimbLongHooks(LongHooks longHooks, double speed, boolean stopAtLimits) {
        addRequirements(longHooks);

        this.longHooks = longHooks;
        this.speed = speed;
        this.stopAtLimits = stopAtLimits;
    }

    @Override
    public void execute() {
        // NOTE: LIMIT SWITCHES ARE INVERTED

        // run left hook until limit switch pressed
        if (!stopAtLimits || longHooks.getLeftLimitSwitch()) {
            longHooks.setLeftSpeed(speed);
        } else {
            longHooks.setLeftSpeed(0.0);
        }

        // run right hook until limit switch pressed
        if (!stopAtLimits || longHooks.getRightLimitSwitch()) {
            longHooks.setRightSpeed(speed);
        } else {
            longHooks.setRightSpeed(0.0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        longHooks.setLeftSpeed(0.0);
        longHooks.setRightSpeed(0.0);
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this one index
        return (stopAtLimits && (!longHooks.getLeftLimitSwitch() && !longHooks.getRightLimitSwitch()));
    }
}
