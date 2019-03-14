import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

public class CTest19 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test
    public void getAllInvoiceInPeriod_return_empty_collection() {

        Invoice inv1 = new Invoice("1",
                "HRS",
                125D, Department.INCOMES,
                LocalDate.of(2017, 2, 12),
                LocalDate.of(2018, 3, 13));

        Invoice inv2 = new Invoice("2",
                "SoftUni",
                1000D, Department.INCOMES,
                LocalDate.of(2017, 2, 12),
                LocalDate.of(2018, 3, 12));

        Invoice inv3 = new Invoice("22",
                "SoftUni",
                1000D, Department.INCOMES,
                LocalDate.of(2017, 2, 12),
                LocalDate.of(2018, 3, 13));

        this.agency.create(inv1);
        this.agency.create(inv2);
        this.agency.create(inv3);

        final int expectedCount = 0;

        final int actualCount = (int) StreamSupport
                .stream(this
                        .agency
                        .getAllInvoiceInPeriod(LocalDate.of(2018, 3, 13),
                                LocalDate.of(2018, 3, 15))
                        .spliterator(), false)
                .count();

        Assert.assertEquals("Incorrect invoice count", expectedCount, actualCount);
    }
}