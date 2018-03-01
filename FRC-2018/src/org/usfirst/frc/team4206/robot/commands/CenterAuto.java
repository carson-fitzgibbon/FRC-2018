package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterAuto extends Command {

    public CenterAuto() {
        requires(Robot.drivetrain);
        requires(Robot.shooterfeeder);
        requires(Robot.navx);
    }

    String plateConfig = "";
    long startTime = 0;
    boolean complete = false;
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	plateConfig = DriverStation.getInstance().getGameSpecificMessage();
    	startTime = System.currentTimeMillis();
    	this.setTimeout(6);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (plateConfig.charAt(0) == 'L') {
    		// do left switch
    		if (System.currentTimeMillis() < startTime + 2000) {
    			Robot.drivetrain.velocityDrive(.5, (-35-Robot.navx.getGyro())/35); // try this in position mode
    		} else {
    			Robot.drivetrain.setAngle(-35);
    			Robot.shooterfeeder.setAngularVelocity(0.5, 0.5);
    	    	Robot.shooterfeeder.setIntake(1);
    		}
    	} else if (plateConfig.charAt(0) == 'R'){
    		// do switch
    		if (System.currentTimeMillis() < startTime + 2000) {
    			Robot.drivetrain.velocityDrive(.5, (35-Robot.navx.getGyro())/35); // try this in position mode
    		} else {
    			Robot.drivetrain.setAngle(35);
    			Robot.shooterfeeder.setAngularVelocity(0.5, 0.5);
    	    	Robot.shooterfeeder.setIntake(1);
    		}
    	} else new TimedDrive(3, .5);
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
    	Robot.drivetrain.arcadeDrive(0, 0);
    }
}
