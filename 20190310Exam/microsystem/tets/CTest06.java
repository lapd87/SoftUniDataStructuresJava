import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest06 {
    private Microsystem microsystem;

    @Before
    public void init() {
        this.microsystem = new MicrosystemImpl();
    }

    @Test
    public void createComputer_should_return_false_with_valid_number() {

        Computer computer1 = new Computer(2, Brand.ACER, 1120, 15.6, "grey");
        Computer computer = new Computer(1, Brand.DELL, 2300,15.6, "grey");


        this.microsystem.createComputer(computer);
        this.microsystem.createComputer(computer1);

        Assert.assertFalse("Incorrect return value",this.microsystem.contains(3));
        Assert.assertFalse("Incorrect return value",this.microsystem.contains(4));
    }
}
