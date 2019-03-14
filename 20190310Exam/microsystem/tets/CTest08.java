import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest08 {
    private Microsystem microsystem;

    @Before
    public void init() {
        this.microsystem = new MicrosystemImpl();
    }

    @Test
    public void remove_should_work_correctly() {

        Computer computer1 = new Computer(2, Brand.ACER, 1120, 15.6, "grey");
        Computer computer = new Computer(1, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(5, Brand.HP, 2400, 13.6, "red");


        this.microsystem.createComputer(computer);
        this.microsystem.createComputer(computer1);
        this.microsystem.createComputer(computer2);
        this.microsystem.remove(2);

        Assert.assertFalse("Item is not remove correctly", this.microsystem.contains(2));
    }
}
