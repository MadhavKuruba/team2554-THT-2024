import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot
{
    private final CANSparkMax leftFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
    private final CANSparkMax leftRearMotor = new CANSparkMax(2, MotorType.kBrushless);
    private final CANSparkMax rightFrontMotor = new CANSparkMax(3, MotorType.kBrushless);
    private final CANSparkMax rightRearMotor = new CANSparkMax(4, MotorType.kBrushless);
    private final DifferentialDrive drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    private final Timer timer = new Timer();

    @Override
    public void robotInit()
    {
        leftRearMotor.follow(leftFrontMotor);
        rightRearMotor.follow(rightFrontMotor);
    }

    @Override
    public void teleopPeriodic()
    {
        // Idk actual joystick values so replace them here
        double speed = 0.5;
        double rotation = 0.3;
        drive.arcadeDrive(speed, rotation);
    }

    @Override
    public void autonomousInit()
    {
        timer.reset();
        timer.start();
    }

    @Override
    public void autonomousPeriodic()
    {
        if (timer.get() < 15.0)
        {
            drive.arcadeDrive(0.5, 0);
        }
        else if (timer.get() < 17.0)
        {
            drive.arcadeDrive(0, 0.5);
        }
        else
        {
            drive.stopMotor();
        }
    }
}
