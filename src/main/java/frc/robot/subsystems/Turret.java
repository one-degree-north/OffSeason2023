// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  /** Creates a new Turret. */
  TalonFX rotationMotor;
  TalonFX flywheelMotor;
  TalonFX flywheelFollowerMotor;
  public Turret(TalonFX rotationMotor, TalonFX flywheelMotor, TalonFX flywheelFollowerMotor) {
    this.rotationMotor = rotationMotor;
    this.flywheelMotor = flywheelMotor;
    this.flywheelFollowerMotor = flywheelFollowerMotor;
  }

  public void setFlywheel(double speed){
    flywheelMotor.set(ControlMode.PercentOutput, speed);
    flywheelFollowerMotor.set(ControlMode.PercentOutput, speed);
  }

  public void rotateTurret(double speed){
    rotationMotor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
