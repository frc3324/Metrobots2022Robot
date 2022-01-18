package frc.team3324.robot;

import edu.wpi.first.wpilibj.RobotBase;

public class Main {
    private Main() {}

    public static void main(String[] args) { 
        RobotBase.startRobot(Robot::new);
    }
    
}
