import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.StreamSupport;

public class CTest21 {
    private Microsystem microsystems;

    @Before
    public void init() {
        this.microsystems = new MicrosystemImpl();
    }

    @Test
    public void getAllWithScreenSize_should_return_correct_order() {

        Computer computer = new Computer(7, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(3, Brand.DELL, 2200, 15.6, "grey");
        Computer computer3 = new Computer(6, Brand.DELL, 2800, 15.6, "grey");
        Computer computer4 = new Computer(5, Brand.ACER, 2300, 15.6, "grey");


        microsystems.createComputer(computer);
        microsystems.createComputer(computer2);
        microsystems.createComputer(computer3);
        microsystems.createComputer(computer4);

        Computer[] actualComputer =StreamSupport.stream(microsystems.getAllWithScreenSize(15.6).spliterator(),false).
                toArray(Computer[]::new);

        Assert.assertEquals("Incorrect order",7, actualComputer[0].getNumber());
        Assert.assertEquals("Incorrect order",6, actualComputer[1].getNumber());
        Assert.assertEquals("Incorrect order",5, actualComputer[2].getNumber());
        Assert.assertEquals("Incorrect order",3, actualComputer[3].getNumber());
    }
}
