package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CTREConfigs;
import frc.robot.Constants.IntakeConstants;



public class Intake extends SubsystemBase {
    
    // TODO: Set Can ID
    private TalonFX m_Intake;

    // Check ID?
    private PneumaticHub revph;

    // Set port
    public DoubleSolenoid pistons;

    public Intake() {
        this.revph = new PneumaticHub();
        this.pistons = revph.makeDoubleSolenoid(IntakeConstants.solenoidForwardID, IntakeConstants.solenoidBackwardID);

        this.m_Intake = new TalonFX(IntakeConstants.motorID);
        configIntakeMotor();
    }

    public void configIntakeMotor() {
        this.m_Intake.configFactoryDefault();
        this.m_Intake.configAllSettings(CTREConfigs.intakeTalonFXConfiguration);
        this.m_Intake.setInverted(IntakeConstants.motorInvert);
        this.m_Intake.setNeutralMode(IntakeConstants.motorNeutralMode);
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

