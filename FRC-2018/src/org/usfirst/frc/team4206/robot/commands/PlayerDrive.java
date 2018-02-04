package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.OI;
import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot with the driver joystick
 */
public class PlayerDrive extends Command {

    public PlayerDrive() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.velocityDrive(OI.driver.getRawAxis(OI.leftY), OI.driver.getRawAxis(OI.rightX));
    	Timer.delay(0.005);
	}
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
