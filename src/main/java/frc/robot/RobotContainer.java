// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Turret;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick driver =
      new Joystick(OperatorConstants.kDriverControllerPort);

  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  private final Swerve s_Swerve = new Swerve(new File(Filesystem.getDeployDirectory(),
  "swerve"));
  private final Intake s_Intake = new Intake();
  private final Indexer s_Indexer = new Indexer();
  private final Turret s_Turret = new Turret();

  private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kX.value);

  private final JoystickButton toggleCompressor = new JoystickButton(driver, XboxController.Button.kY.value);
  private final JoystickButton pistonExtend = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
  private final JoystickButton pistonRetract = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

  private final Trigger turretLeft = new POVButton(driver, 270);
  private final Trigger turretRight = new POVButton(driver, 90);


  private final JoystickButton flexibleButton = new JoystickButton(driver, XboxController.Button.kA.value);
  private final JoystickButton intakeButton = new JoystickButton(driver, XboxController.Button.kB.value);
  
  private final Command toggleIntake = new InstantCommand(() -> s_Intake.toggleIntake());
  private final Command defaultIndex = new ParallelCommandGroup(
    new InstantCommand(() -> s_Indexer.defaultRun()), 
    new InstantCommand(() -> s_Turret.stopFlywheel())
  );
  private final Command shoot = new ParallelCommandGroup(
    new InstantCommand(() -> s_Indexer.shootRun()), 
    new InstantCommand(() -> s_Turret.runFlywheel())
  );
  private final Command stopAll = new ParallelCommandGroup(
    new InstantCommand(() -> s_Intake.stopMotor()),
    new InstantCommand(() -> s_Indexer.stopAll()),
    new InstantCommand(() -> s_Turret.stopFlywheel()));

    private final Command defaultIntake = new ParallelCommandGroup(
      defaultIndex, 
      new InstantCommand(() -> s_Intake.intakeBall()));
    
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    s_Swerve.setDefaultCommand(new TeleopSwerve(s_Swerve, 
      () -> -driver.getRawAxis(translationAxis)
    , () -> -driver.getRawAxis(strafeAxis)
    , () -> -driver.getRawAxis(rotationAxis) 
    ));

    zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

    toggleCompressor.onTrue(new InstantCommand(() -> s_Intake.toggleCompressor()));
    pistonExtend.onTrue(new InstantCommand(() -> s_Intake.pistonsExtend()));
    pistonRetract.onTrue(new InstantCommand(() -> s_Intake.pistonsRetract()));

    turretLeft.onTrue(new InstantCommand(() -> s_Turret.runTurretCCW(1)));
    turretLeft.onFalse(new InstantCommand(() -> s_Turret.stopTurret()));
    turretRight.onTrue(new InstantCommand(() -> s_Turret.runTurretCW(1)));
    turretRight.onFalse(new InstantCommand(() -> s_Turret.stopTurret()));

    flexibleButton.onTrue(shoot);
    flexibleButton.onFalse(stopAll);

    intakeButton.onTrue(defaultIntake);
    intakeButton.onFalse(stopAll);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
