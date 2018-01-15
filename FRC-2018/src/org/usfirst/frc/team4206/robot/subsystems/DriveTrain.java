package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.RobotMap;
import org.usfirst.frc.team4206.robot.commands.PlayerDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This class controls the robot's drive train
 */
public class DriveTrain extends Subsystem {

	static WPI_TalonSRX leftMaster;
	static WPI_TalonSRX leftSlave;
	static WPI_TalonSRX rightMaster;
	static WPI_TalonSRX rightSlave;
	static DifferentialDrive diffDrive;
	
	public DriveTrain() {
		leftMaster = new WPI_TalonSRX(RobotMap.leftMaster);
		leftSlave = new WPI_TalonSRX(RobotMap.leftSlave);
		rightMaster = new WPI_TalonSRX(RobotMap.rightMaster);
		rightSlave = new WPI_TalonSRX(RobotMap.rightSlave);
		
		leftSlave.set(ControlMode.Follower, RobotMap.leftMaster);
		rightSlave.set(ControlMode.Follower, RobotMap.rightMaster);
		
		leftMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		leftSlave.setNeutralMode(NeutralMode.Brake);
		rightSlave.setNeutralMode(NeutralMode.Brake);
		
		diffDrive = new DifferentialDrive(leftMaster, rightMaster);
	}
	
	public void arcadeDrive(double power, double rotate) {
		diffDrive.arcadeDrive(power, rotate);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new PlayerDrive());
    }
}