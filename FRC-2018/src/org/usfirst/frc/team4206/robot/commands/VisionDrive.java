package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class VisionDrive extends Command {

	private static final double kP = 0.025;
	private double offsetX;
	
    public VisionDrive() {
        requires(Robot.drivetrain);
        requires(Robot.limelightvision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	offsetX = Robot.limelightvision.getTargetOffsetX();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(Robot.limelightvision.getTargetOffsetX());
    	if (Robot.limelightvision.hasValidTarget() == 1) {
    		offsetX = Robot.limelightvision.getTargetOffsetX();
    		System.out.println("Chasing!");
    		Robot.drivetrain.arcadeDrive(0, (offsetX * kP)*(offsetX * kP));
    	} else {
    		Robot.drivetrain.arcadeDrive(0, (Math.signum(offsetX) * 0.5)*(Math.signum(offsetX) * 0.5)); // If the target leaves our FOV or becomes undetected, 
    					  // the robot will continue turning in the last direction 
    					  // it was moving at a reduced speed, intelligently hunting for the target
    		System.out.println("Hunting!");
    	}
    	
    	Robot.drivetrain.arcadeDrive(0, offsetX * kP);
    	System.out.println(offsetX * kP + ", " + Robot.limelightvision.getTargetOffsetX());
    	Timer.delay(0.005);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (offsetX < .1 & offsetX > -.1 & Robot.limelightvision.hasValidTarget()==1) {
        	System.out.println("Natural ending");
        	return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println(Robot.limelightvision.getTargetOffsetX());
    	Robot.drivetrain.arcadeDrive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println(Robot.limelightvision.getTargetOffsetX());
    	Robot.drivetrain.arcadeDrive(0,0);
    }
}
