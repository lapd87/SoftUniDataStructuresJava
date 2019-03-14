import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest02 {
    private Microsystem microsystem;

    @Before
    public void init() {
        this.microsystem = new MicrosystemImpl();
    }

    @Test
    public void createComputer_should_increase_count() {

        Computer computer1 = new Computer(1, Brand.ACER, 1120, 15.6, "grey");
        Computer computer2 = new Computer(2, Brand.ASUS, 2000, 15.6, "red");
        //Act

        this.microsystem.createComputer(computer1);
        this.microsystem.createComputer(computer2);
        final int expectedCount = 2;
        final int actualCount = this.microsystem.count();

        //Assert
        Assert.assertEquals("Incorrect count",expectedCount, actualCount);


    }
}
