package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class ElevatorAllInOne
{
    public static class ElevatorSubsystem extends SubsystemBase
    {
        private final CANSparkMax m1 = new CANSparkMax(10, MotorType.kBrushless);
        private final CANSparkMax m2 = new CANSparkMax(11, MotorType.kBrushless);

        public ElevatorSubsystem()
        {
            m2.follow(m1);
        }

        public void raiseElevator()
        {
            m1.set(0.8);
        }

        public void lowerElevator()
        {
            m1.set(-0.8);
        }

        public void stopElevator()
        {
            m1.set(0);
        }
    }

    public static class RaiseElevator extends Command
    {
        private final ElevatorSubsystem e;

        public RaiseElevator(ElevatorSubsystem e)
        {
            this.e = e;
            addRequirements(e);
        }

        @Override
        public void initialize() {e.raiseElevator();}

        @Override
        public void end(boolean interrupted) {e.stopElevator();}

        @Override
        public boolean isFinished() {return false;}
    }

    public static class LowerElevator extends Command
    {
        private final ElevatorSubsystem e;

        public LowerElevator(ElevatorSubsystem e)
        {
            this.e = e;
            addRequirements(e);
        }

        @Override
        public void initialize() {e.lowerElevator();}

        @Override
        public void end(boolean interrupted) {e.stopElevator();}

        @Override
        public boolean isFinished() {return false;}
    }

    public static class StopElevator extends Command
    {
        private final ElevatorSubsystem e;

        public StopElevator(ElevatorSubsystem e)
        {
            this.e = e;
            addRequirements(e);
        }

        @Override
        public void initialize() {e.stopElevator();}

        @Override
        public boolean isFinished() {return true;}
    }

    private final ElevatorSubsystem e = new ElevatorSubsystem();
    private final CommandXboxController c = new CommandXboxController(0);

    public ElevatorAllInOne()
    {
        e.setDefaultCommand(new StopElevator(e));
        configureBindings();
    }

    private void configureBindings()
    {
        c.rightBumper().whileTrue(new RaiseElevator(e));
        c.leftBumper().whileTrue(new LowerElevator(e));
    }
}

