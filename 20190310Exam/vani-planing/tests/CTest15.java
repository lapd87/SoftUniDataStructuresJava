import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

public class CTest15 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test
    public void throwInvoice_remove_correctly() {

        Invoice inv1 = new Invoice("1",
                "HRS",
                125D, Department.INCOMES,
                LocalDate.of(2018, 2, 12),
                LocalDate.of(2018, 3, 12));

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

        this.agency.throwInvoice("1");

        double expectedSubtotal = 2000D;
        double actualSubtotal = StreamSupport.stream(this.agency.searchByNumber("2").spliterator(), false)
                .mapToDouble(Invoice::getSubtotal)
                .sum();

        double delta = 0.001;

        Assert.assertEquals("Incorrect remaining invoices", expectedSubtotal, actualSubtotal, delta);

    }
}