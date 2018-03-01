package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntake extends Command {
	
    public RunIntake(double left, double right) {
        requires(Robot.shooterfeeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterfeeder.setAngularVelocity(Robot.oi.operator.getRawAxis(Robot.oi.leftY) * 0.5, Robot.oi.operator.getRawAxis(Robot.oi.rightY) * 0.5);
    	Robot.shooterfeeder.setIntake((Robot.oi.operator.getRawAxis(Robot.oi.leftY) + Robot.oi.operator.getRawAxis(Robot.oi.rightY)) / 2);
    	Timer.delay(0.005);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterfeeder.setAngularVelocity(0,0);
    	Robot.shooterfeeder.setIntake(0);
    }
}
