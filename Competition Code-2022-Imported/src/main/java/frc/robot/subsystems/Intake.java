package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import com.ctre.phoenix.motorcontrol.ControlMode;
<<<<<<< HEAD:Competition Code-2022-Imported/src/main/java/frc/robot/subsystems/Intake.java
// import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
=======
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
// import edu.wpi.first.wpilibj.VictorSP;
>>>>>>> e7b221481df768cafef19f6b651731b8e63a3896:Competition Code/src/main/java/frc/robot/subsystems/Intake.java

import static frc.robot.Constants.*;

public class Intake extends SubsystemBase {
  // These are the variables that need to be created for Intake.
  private DoubleSolenoid intakePiston1, intakePiston2;
  private VictorSPX rollerMotor;

  // This is the constructor.
  public Intake() {
<<<<<<< HEAD:Competition Code-2022-Imported/src/main/java/frc/robot/subsystems/Intake.java
    rollerMotor = new WPI_TalonSRX(INTAKE_MOTOR_PORT);
    intakePiston1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, INTAKE_PISTON_PORT_1, INTAKE_PISTON_PORT_2);
    intakePiston2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, INTAKE_PISTON_PORT_3, INTAKE_PISTON_PORT_4);
=======
    rollerMotor = new VictorSPX(INTAKE_MOTOR_PORT);
    // intakePiston1 = new DoubleSolenoid(INTAKE_PISTON_PORT_1, INTAKE_PISTON_PORT_2);
    // intakePiston2 = new DoubleSolenoid(INTAKE_PISTON_PORT_3, INTAKE_PISTON_PORT_4);
>>>>>>> e7b221481df768cafef19f6b651731b8e63a3896:Competition Code/src/main/java/frc/robot/subsystems/Intake.java
  }
  /**
   * Sets the speed of the motor
   *
   * @param speed [-1.0, 1.0]
   */

  // This method sets the intake motor's speed to a value.
  public void intake() {
    rollerMotor.set(ControlMode.PercentOutput, -0.5);
  }

  // This method pushes the Intake piston forward.
   public void pistonForward() {
    intakePiston1.set(DoubleSolenoid.Value.kForward);
    intakePiston2.set(DoubleSolenoid.Value.kForward);
  }

  // This method pulls the Intake piston back.
  public void pistonReverse() {
    intakePiston1.set(DoubleSolenoid.Value.kReverse);
    intakePiston2.set(DoubleSolenoid.Value.kReverse);
  }

  // This method checks if the pistons are engaged
  public boolean getPistonState() {
    return intakePiston1.get() == DoubleSolenoid.Value.kForward;
  }

  // This method turns off the intake motor.
  public void off() {
    rollerMotor.set(ControlMode.PercentOutput, 0);
  }
}
