package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeIn extends Command {

    public CubeIn() {
        requires(Robot.shooterfeeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterfeeder.setIntake(-1);
    	Robot.shooterfeeder.setAngularVelocity(-100);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.shooterfeeder.hasCube();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterfeeder.setIntake(0);
    	Robot.shooterfeeder.setAngularVelocity(0);
    }
}
