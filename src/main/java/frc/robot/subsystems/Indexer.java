package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
  

  private CANSparkMax m_Indexer = new CANSparkMax(1, MotorType.kBrushless);
  private TalonFX m_Belt = new TalonFX(2); 
  private TalonFX m_Storage = new TalonFX(3);
  
  //move this to constants
  private final double indexerSpeed = 1;
  private final double beltSpeed = 1;
  private final double storageSpeed = 1;

  public void beltRun(){
    m_Belt.set(ControlMode.PercentOutput, beltSpeed);
  }

  public void intakeRun(){
    m_Indexer.set(indexerSpeed);
  }

  public void storageRun(){
    m_Storage.set(ControlMode.PercentOutput, storageSpeed);
  }

  public void beltReverseRun(){
    m_Belt.set(ControlMode.PercentOutput, -storageSpeed);
  }
}
