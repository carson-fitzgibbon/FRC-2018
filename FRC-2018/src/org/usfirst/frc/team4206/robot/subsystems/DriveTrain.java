package org.usfirst.frc.team4206.robot.subsystems;

import org.usfirst.frc.team4206.robot.Robot;
import org.usfirst.frc.team4206.robot.RobotMap;
import org.usfirst.frc.team4206.robot.commands.PlayerDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
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
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
		leftSlave = new WPI_TalonSRX(RobotMap.leftSlave);
		rightMaster = new WPI_TalonSRX(RobotMap.rightMaster);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 1000);
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

	public String requestDiagnostic() {
		System.out.println("\n----STARTING DRIVETRAIN DIAGNOSTIC----\n\n");
		String report1 = "\nLeft Master\n", report2 = "\nLeft Slave\n", report3 = "\nRight Master\n", report4 = "\nRight Slave\n";
		
		System.out.println("Checking firmware versions...");
		if (leftMaster.getFirmwareVersion() != 0x0303) report1 += "Firmware Out of Date!\n";
		if (leftSlave.getFirmwareVersion() != 0x0303) report2 += "Firmware Out of Date!\n";
		if (rightMaster.getFirmwareVersion() != 0x0303) report3 += "Firmware Out of Date!\n";
		if (rightSlave.getFirmwareVersion() != 0x0303) report4 += "Firmware Out of Date!\n";
		
		leftSlave.set(ControlMode.PercentOutput, 0);
		rightSlave.set(ControlMode.PercentOutput, 0);
		
		System.out.println("Testing left master motor...");
		leftMaster.set(0.5);
		Timer.delay(2);
		if (Robot.pdp.portCurrent(RobotMap.leftMasterPWR)==0) report1 += "Not Powered!\n";
		if (leftMaster.getSelectedSensorVelocity(0) == 0) report1 += "No Motion Detected!\n";
		leftMaster.set(0);
		System.out.println("Testing right master motor...");
		rightMaster.set(0.5);
		Timer.delay(2);
		if (Robot.pdp.portCurrent(RobotMap.rightMasterPWR)==0) report2 += "Not Powered!\n";
		if (rightMaster.getSelectedSensorVelocity(0) == 0) report2 += "No Motion Detected!\n";
		rightMaster.set(0);
		System.out.println("Testing left slave motor...");
		leftSlave.set(0.5);
		Timer.delay(2);
		if (Robot.pdp.portCurrent(RobotMap.leftSlavePWR)==0) report3 += "Not Powered!\n";
		if (leftMaster.getSelectedSensorVelocity(0) == 0) report3 += "No Motion Detected!\n";
		leftSlave.set(0);
		System.out.println("Testing right slave motor...");
		rightSlave.set(0.5);
		Timer.delay(2);
		if (Robot.pdp.portCurrent(RobotMap.rightSlavePWR)==0) report4 += "Not Powered!\n";
		if (rightMaster.getSelectedSensorVelocity(0) == 0) report4 += "No Motion Detected!\n";
		rightSlave.set(0);
		
		
		
		return report1+report2+report3+report4;
	}
}