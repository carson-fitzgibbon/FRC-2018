package org.usfirst.frc.team4206.robot.commands;

import org.usfirst.frc.team4206.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command tests all subsystems and ensures they are working properly
 */
public class RunDiagnostics extends Command {

    public RunDiagnostics() {
        requires(Robot.drivetrain);
        requires(Robot.limelightvision);
        requires(Robot.navx);
        requires(Robot.shifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Timer.delay(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("DriveTrain: " + Robot.drivetrain.requestDiagnostic());
    	System.out.println("LimeLight: " + Robot.limelightvision.requestDiagnostic());
    	System.out.println("NavX: " + Robot.navx.requestDiagnostic());
    	System.out.println("Shifter: " + Robot.shifter.requestDiagnostic());
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
