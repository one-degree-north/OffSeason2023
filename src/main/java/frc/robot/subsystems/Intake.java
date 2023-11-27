package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;



public class Intake extends SubsystemBase {
    
    // TODO: Set Can ID
    private TalonFX m_Intake;
    private TalonFXConfiguration intakeTalonFXConfiguration;

    // Check ID?
    private PneumaticHub revph;

    // Set port
    public DoubleSolenoid pistons;

    public Intake() {
        this.m_Intake = new TalonFX(IntakeConstants.motorID);
        this.revph = new PneumaticHub();
        this.pistons = revph.makeDoubleSolenoid(IntakeConstants.solenoidForwardID, IntakeConstants.solenoidBackwardID);

        m_Intake.configFactoryDefault();
        intakeTalonFXConfiguration = new TalonFXConfiguration();
        SupplyCurrentLimitConfiguration intakeSupplyLimit =
            new SupplyCurrentLimitConfiguration(true, 25, 40, 0.1);
        intakeTalonFXConfiguration.supplyCurrLimit = intakeSupplyLimit;
    }


    public void enableCompressor() {
        revph.enableCompressorAnalog(100, 120);
    }
    
    public void disableCompressor() {
        revph.disableCompressor();
    }

    public void intakeBall(){
        m_Intake.set(ControlMode.PercentOutput, IntakeConstants.kIntakeSpeed);
    }

    public void outputBall(){
        m_Intake.set(ControlMode.PercentOutput, -IntakeConstants.kIntakeSpeed);
    }

    public void pistonsExtend(){
        pistons.set(DoubleSolenoid.Value.kForward);
    }
    
    public void pistonsRetract(){
        pistons.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void togglePistons(){
        pistons.toggle();
    }
}

