import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CTest07 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test
    public void test_count() {

        final int expectedCount = 0;
        final int actualCount = this.agency.count();

        Assert.assertEquals("Incorrect count", expectedCount, actualCount);
    }
}