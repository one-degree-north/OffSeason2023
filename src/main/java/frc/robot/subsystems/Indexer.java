package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
  

  private CANSparkMax m_Indexer = new CANSparkMax(1, MotorType.kBrushless);
  private TalonFX m_Belt1 = new TalonFX(2);
  private TalonFX m_Belt2 = new TalonFX(3);
  
  private final double intakeSpeed = 1;

  public void index(){
    m_Indexer.set(intakeSpeed);
  }

  public void stopIndex(){
    m_Indexer.set(0);
  }

  public void outIndex(){
    m_Indexer.set(-intakeSpeed);  
  }

  public void runBelt(){
    m_belt
  }
}
