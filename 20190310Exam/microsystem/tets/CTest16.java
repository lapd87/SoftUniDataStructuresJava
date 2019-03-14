import org.junit.Before;
import org.junit.Test;

public class CTest16 {
    private Microsystem microsystems;

    @Before
    public void init() {
        this.microsystems = new MicrosystemImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void upgrade_ram_should_throw_exception_with_invalid_number() {

        Computer computer = new Computer(1, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(3, Brand.DELL, 2300, 15.6, "grey");
        Computer computer3 = new Computer(4, Brand.DELL, 2300, 15.6, "grey");
        Computer computer4 = new Computer(5, Brand.ACER, 2300, 15.6, "grey");

        //Act
        microsystems.createComputer(computer);
        microsystems.createComputer(computer2);
        microsystems.createComputer(computer3);
        microsystems.createComputer(computer4);

        //Assert

        microsystems.upgradeRam(16, 12);
    }
}
