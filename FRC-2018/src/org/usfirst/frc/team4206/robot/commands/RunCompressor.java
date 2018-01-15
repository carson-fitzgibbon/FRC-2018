package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command runs the compressor
 */
public class RunCompressor extends Command {

    public RunCompressor() {
        requires(Robot.pneumatics);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pneumatics.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.pneumatics.getSwitchValue()) Robot.pneumatics.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pneumatics.setClosedLoopControl(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pneumatics.setClosedLoopControl(true);
    }
}
