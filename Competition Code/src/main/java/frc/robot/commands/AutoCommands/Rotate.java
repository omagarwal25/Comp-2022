// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import frc.robot.subsystems.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

public class Rotate extends CommandBase {

  SwerveRotaters rotators;
  SwerveSpinners spinners;
  Gyro gyro;
  double targetAngle, fR, fL, bR, bL;
  double turnDirection;

  // This command sets the position of the motors to a preset configuration.
  // Then the command rotates the robot untill it reaches a certain angle. 
  public Rotate(SwerveRotaters rotators, SwerveSpinners spinners, Gyro gyro, double targetAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.rotators = rotators;
    this.gyro = gyro;
    this.fR = rotators.angleToPulse(45);
    this.fL = rotators.angleToPulse(135);
    this.bR = rotators.angleToPulse(225);
    this.bL = rotators.angleToPulse(315);
    addRequirements(rotators, spinners);
    this.targetAngle = targetAngle;
    currentAngle = gyro.getYaw();
    // This calculates the turn direction of the swerve in this suto command 
    if (currentAngle>=180){
      boolean condition1 = (currentAngle<= targetAngle && targetAngle <=360);
      boolean condition2 = (targetAngle<= ((currentAngle+180)%360));
      if (condition1 || condition2) turnDirection = 1; //clockwise
      else turnDirection = -1; //counter-clockwise
    }
    else{
      if (currentAngle<=targetAngle && targetAngle<=currentAngle+180) turnDirection = 1; //clockwise
      else turnDirection = -1; //counter-clockwise
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotators.setWheelDirection(fR, fL, bR, bL);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rotators.setWheelDirection(fR, fL, bR, bL);
    if(rotators.reachedPosition(fR, fL, bR, bL)){
      spinners.autoRunSpinners(AUTO_ROTATE_SPEED*turnDirection);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rotators.stop();
    spinners.stop();
    System.out.println("Finished");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double cA = gyro.getYaw();
    double upperLimit = 360-ANGLE_ERROR_TOLERANCE;
    double lowerLimit = ANGLE_ERROR_TOLERANCE;
    boolean upperC, lowerC;
    if (targetAngle>=(upperLimit)){
      boolean c1 = cA >= targetAngle;
      boolean c2 = cA <= targetAngle-upperLimit;
      if (c1 == true || c2 == true) upperC = true;
      if (((targetAngle-ANGLE_ERROR_TOLERANCE)%360)<= cA) lowerC = true;
      if (upperC & lowerC) return true;
    }
    else if (targetAngle <= (lowerLimit)){
      boolean c1 = cA <= targetAngle;
      boolean c2 = cA >= ((targetAngle-lowerLimit)%360);
      if (c1 == true || c2 == true) upperC = true;
      if (((targetAngle+ANGLE_ERROR_TOLERANCE)%360)>= cA) lowerC = true;
      if (upperC & lowerC) return true;
    }
    else{ 
      return (((((targetAngle-ANGLE_ERROR_TOLERANCE)%360)<=cA) &&(cA<=((targetAngle+ANGLE_ERROR_TOLERANCE)%360))));
    }
  }
}
