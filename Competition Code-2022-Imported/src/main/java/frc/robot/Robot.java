/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutomatedCommands.*;

import frc.robot.commands.*;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import static frc.robot.Constants.*;

import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private Command driveForward;
  private Command climbChild;

  private final TalonFX BRR = new TalonFX(8);

  private RobotContainer rCon;
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    CameraServer.startAutomaticCapture();
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.


    var BRR = new TalonFX(BACK_RIGHT_MODULE_STEER_MOTOR);
    var BLR = new TalonFX(BACK_LEFT_MODULE_STEER_MOTOR);
    var FRR = new TalonFX(FRONT_RIGHT_MODULE_STEER_MOTOR);
    var FLR = new TalonFX(FRONT_LEFT_MODULE_STEER_MOTOR);

    BRR.setSelectedSensorPosition(0, 0, 20);
    BLR.setSelectedSensorPosition(0, 0, 20);
    FRR.setSelectedSensorPosition(0, 0, 20);
    FLR.setSelectedSensorPosition(0, 0, 20);

    rCon = new RobotContainer();
    // SmartDashboard.putData("Auto choices", autoChooser);
    // SmartDashboard.putNumber("Auto Wait Time", 0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = new AutoSequence(
        rCon.getDrive(),

        rCon.getGyro(),
        rCon.getCatapult(),
        rCon.getIntake());
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  /*
   * public void getAutoCommand() {
   * m_autonomousCommand = autoChooser.getSelected();
   * }
   */

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    System.out.println(BRR.getSelectedSensorPosition());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
  /*
   * public void initailizeAutoChooser() {
   * climbChild = new ClimbSequence(rCon.getClimber(), rCon.getRotaters(),
   * rCon.getSpinners());
   * driveForward =
   * new AutoSequence(
   * rCon.getRotaters(),
   * rCon.getSpinners(),
   * rCon.getGyro(),
   * rCon.getCatapult(),
   * rCon.getIntake());
   * autoChooser.addOption("Climb Squence", climbChild);
   * autoChooser.addOption("Drive Forward", driveForward);
   * }
   */
}
