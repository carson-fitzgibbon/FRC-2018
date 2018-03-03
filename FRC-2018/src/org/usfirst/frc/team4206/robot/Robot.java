/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4206.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import org.usfirst.frc.team4206.robot.commands.CenterAuto;
import org.usfirst.frc.team4206.robot.commands.LeftAuto;
import org.usfirst.frc.team4206.robot.commands.RightAuto;
import org.usfirst.frc.team4206.robot.commands.TimedDrive;
import org.usfirst.frc.team4206.robot.commands.VisionDrive;
import org.usfirst.frc.team4206.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4206.robot.subsystems.LimelightVision;
import org.usfirst.frc.team4206.robot.subsystems.NavigationSensor;
import org.usfirst.frc.team4206.robot.subsystems.PDP;
import org.usfirst.frc.team4206.robot.subsystems.Shifter;
import org.usfirst.frc.team4206.robot.subsystems.ShooterFeeder;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static RobotMap map = new RobotMap();
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Shifter shifter = new Shifter();
	public static LimelightVision limelightvision;// = new LimelightVision();
	public static final NavigationSensor navx = new NavigationSensor();
	public static final PDP pdp = new PDP();
	public static final ShooterFeeder shooterfeeder = new ShooterFeeder();
	public static OI oi;

	Command autonomousCommand = new TimedDrive(3, .5); // default autonomous will cross the baseline
	
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		chooser.addDefault("Baseline", new TimedDrive(3, 0.5));
		chooser.addObject("Left", new LeftAuto());
		chooser.addObject("Right", new RightAuto());
		chooser.addObject("Center", new CenterAuto());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//navx.zeroGyro();
		autonomousCommand = chooser.getSelected();
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
