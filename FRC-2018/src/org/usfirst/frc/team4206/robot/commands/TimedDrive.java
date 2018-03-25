package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command autonomously drives the robot forward at a fixed level of power for a given amount of time
 */
public class TimedDrive extends Command {

	private long time = 0;
	private double duration, pwr;
	
    public TimedDrive(double length, double power) {
        requires(Robot.drivetrain);
        requires(Robot.shooterfeeder);
        //requires(Robot.navx);
        this.setTimeout(7);
        pwr = power;
        duration = length * 1000;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivetrain.velocityDrive(pwr, -Robot.navx.getGyro()/90);
    	if (System.currentTimeMillis() < time + duration) Robot.drivetrain.arcadeDrive(-pwr, 0);
    	else if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'q') {
    		Robot.drivetrain.arcadeDrive(0, 0);
    		Robot.shooterfeeder.setAngularVelocity(-0.5, -0.5);
    		Robot.shooterfeeder.setIntake(-1);
    	}
    	Timer.delay(0.005);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (this.isTimedOut()) return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.arcadeDrive(0, 0);
    	Robot.shooterfeeder.setAngularVelocity(0, 0);
		Robot.shooterfeeder.setIntake(0);
    }
}
