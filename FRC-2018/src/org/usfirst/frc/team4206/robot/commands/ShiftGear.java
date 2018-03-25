package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command shifts the robot's gear from high to low or low to high
 */
public class ShiftGear extends Command {

    public ShiftGear() {
        //requires(Robot.shifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if (Robot.drivetrain.getVelocity() < 1000) Robot.shifter.shift();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
}
