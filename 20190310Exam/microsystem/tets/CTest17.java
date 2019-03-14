import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.StreamSupport;

public class CTest17 {
    private Microsystem microsystems;

    @Before
    public void init() {
        this.microsystems = new MicrosystemImpl();
    }

    @Test
    public void getAllFromBrand_should_return_correct_count() {

        Computer computer = new Computer(1, Brand.DELL, 2300, 15.6, "grey");
        Computer computer2 = new Computer(3, Brand.DELL, 2300, 15.6, "grey");
        Computer computer3 = new Computer(4, Brand.DELL, 2300, 15.6, "grey");
        Computer computer4 = new Computer(5, Brand.ACER, 2300, 15.6, "grey");

        //Act
        microsystems.createComputer(computer);
        microsystems.createComputer(computer2);
        microsystems.createComputer(computer3);
        microsystems.createComputer(computer4);
        int expectedCount = 3;
        int actualCount = (int)StreamSupport.stream(microsystems.getAllFromBrand(Brand.DELL).spliterator(),false).count();

        //Assert

        Assert.assertEquals("Count is not correct",expectedCount, actualCount);
    }
}
