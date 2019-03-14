import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CTest06 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test
    public void test_count() {
        Invoice inv1 = new Invoice("1",
                "HRS",
                125D,Department.INCOMES,
                LocalDate.of(2018,2,12),
                LocalDate.of(2018,3,12));

        Invoice inv2 = new Invoice("2",
                "SoftUni",
                1000D,Department.INCOMES,
                LocalDate.of(2019,2,12),
                LocalDate.of(2019,3,12));

        this.agency.create(inv1);
        this.agency.create(inv2);

        final int expectedCount = 2;
        final int actualCount = this.agency.count();

        Assert.assertEquals("Incorrect count",expectedCount,actualCount);
    }
}
