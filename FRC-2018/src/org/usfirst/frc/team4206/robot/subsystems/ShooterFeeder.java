package org.usfirst.frc.team4206.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4206.robot.Robot;
import org.usfirst.frc.team4206.robot.commands.IntakeElevator;
import org.usfirst.frc.team4206.robot.commands.RunElevator;
import org.usfirst.frc.team4206.robot.commands.RunIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

/**
 * This subsystem is for the robot's shooter-feeder mechanism
 */
public class ShooterFeeder extends Subsystem {

	DigitalInput grabberSqueezed;
	DigitalInput grabberStretched;
	
	WPI_TalonSRX elevatorMaster;
	WPI_TalonSRX elevatorSlave;
	WPI_TalonSRX shooterLeft;
	WPI_TalonSRX shooterRight;
	public WPI_TalonSRX grabber;
	WPI_TalonSRX rearIntakeLeft;
	WPI_TalonSRX rearIntakeRight;
	
	public ShooterFeeder() {
		//angleSensor = new AnalogPotentiometer(0);
		//cubeSwitch = new DigitalInput(0);
		
		elevatorMaster = new WPI_TalonSRX(Robot.map.elevatorMaster);
			elevatorMaster.setNeutralMode(NeutralMode.Brake);
		elevatorSlave = new WPI_TalonSRX(Robot.map.elevatorSlave);
			elevatorSlave.setNeutralMode(NeutralMode.Brake);
			elevatorSlave.follow(elevatorMaster);
		shooterLeft = new WPI_TalonSRX(Robot.map.shooterLeft);
			shooterLeft.setNeutralMode(NeutralMode.Coast);
			shooterLeft.setInverted(true);
			//shooterLeft.configOpenloopRamp(0.5, 0);
			//shooterLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Robot.map.kTO);
		shooterRight = new WPI_TalonSRX(Robot.map.shooterRight);
			shooterRight.setNeutralMode(NeutralMode.Coast);
			//shooterRight.configOpenloopRamp(0.5, 0);
			//shooterRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Robot.map.kTO);
			//shooterRight.follow(shooterLeft); // Let's give this a try
		grabber = new WPI_TalonSRX(Robot.map.grabber);
			grabber.setNeutralMode(NeutralMode.Coast);
			//grabber.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Robot.map.kTO);
		rearIntakeLeft = new WPI_TalonSRX(Robot.map.rearIntakeLeft);
			rearIntakeLeft.setNeutralMode(NeutralMode.Coast);
			rearIntakeLeft.setInverted(true);
		rearIntakeRight = new WPI_TalonSRX(Robot.map.rearIntakeRight);
			rearIntakeRight.setNeutralMode(NeutralMode.Coast);
			
	}

    public void elevate(double power) {
    	/*
    	if (isUp()) closeLatch();
    	if (power < 0) {
    		openLatch();
    		if (isDown()) return;
    	} else if (power > 0 & isUp()) {
    		return;
    	}
    	*/
    	elevatorMaster.set(power);
    }
    
    public void controlGrabber(double speed) {
    	//grabber.set(ControlMode.Position, grabberOpen);
    	grabber.set(speed);
    }
    
    public void setAngularVelocity(double left, double right) {
    	shooterLeft.set(left);
    	shooterRight.set(right);
    }

    public void setIntake(double power) {
    	rearIntakeLeft.set(ControlMode.PercentOutput, power);
    	rearIntakeRight.set(ControlMode.PercentOutput, power);
    }

    public boolean grabberTripped() {
    	return false;
    	//return grabberSqueezed.get() | grabberStretched.get();
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new RunElevator());
    }
}

