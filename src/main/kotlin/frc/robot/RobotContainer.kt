/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.RunCommand
import edu.wpi.first.wpilibj2.command.WaitCommand
import frc.robot.subsystems.DrivetrainSubsystem

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private val drivetrain = DrivetrainSubsystem

  private val joystick = Joystick(0)

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  init {
    // Configure the button bindings
    setDefaultCommands()
    configureButtonBindings()
  }

  private fun setDefaultCommands() {
    drivetrain.defaultCommand = RunCommand(
        { drivetrain.arcadeDrive(throttle = -joystick.y, twist = -joystick.z, squareInputs = true) } as Runnable,
        drivetrain)
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private fun configureButtonBindings() {
  }

  fun getAutonomousCommand(): Command {
    // Return the selected command
    return InstantCommand(drivetrain::fastRecalibrateIMU as Runnable).andThen(
        WaitCommand(0.2)
    )
  }
}
