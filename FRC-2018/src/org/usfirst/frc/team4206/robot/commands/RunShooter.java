package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunShooter extends Command {

	long time;
	
    public RunShooter() {
        requires(Robot.shooterfeeder);
        this.setTimeout(3);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterfeeder.setAngularVelocity(-1,-1);
    	if (time + 2000 <= System.currentTimeMillis()) Robot.shooterfeeder.setIntake(-1);
    	Timer.delay(0.005);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterfeeder.setAngularVelocity(0,0);
    	Robot.shooterfeeder.setIntake(0);
    }
}
