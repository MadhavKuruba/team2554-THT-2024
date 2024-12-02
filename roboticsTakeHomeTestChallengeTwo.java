import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Robot extends TimedRobot
{
    private final PneumaticHub pneumaticHub = new PneumaticHub(0);
    private final Solenoid clawSolenoid = pneumaticHub.makeSolenoid(1);
    private final XboxController controller = new XboxController(0);

    @Override
    public void robotInit()
    {
        pneumaticHub.enableCompressorAnalog(90, 120);
        configureButtonBindings();
    }

    private void configureButtonBindings()
    {
        new JoystickButton(controller, XboxController.Button.kA.value).whenPressed(() -> openClaw());
        new JoystickButton(controller, XboxController.Button.kB.value).whenPressed(() -> closeClaw());
    }

    public void openClaw() {clawSolenoid.set(true);}

    public void closeClaw() {clawSolenoid.set(false);}
}
