import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest14 {
    private Microsystem microsystems;

    @Before
    public void init() {
        this.microsystems = new MicrosystemImpl();
    }

    @Test
    public void upgrade_ram_should_change_ram() {

        Computer computer = new Computer(1, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(3, Brand.DELL, 2300, 15.6, "grey");
        Computer computer3 = new Computer(4, Brand.DELL, 2300, 15.6, "grey");
        Computer computer4 = new Computer(5, Brand.ACER, 2300, 15.6, "grey");

        //Act
        microsystems.createComputer(computer);
        microsystems.createComputer(computer2);
        microsystems.createComputer(computer3);
        microsystems.createComputer(computer4);
        microsystems.upgradeRam(16, 1);
        int expectedRam = 16;
        int actualRam = microsystems.getComputer(1).getRAM();

        //Assert

        Assert.assertEquals("Ram is not upgraded",expectedRam, actualRam);
    }
}
