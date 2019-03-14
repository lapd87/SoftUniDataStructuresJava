import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CTest10 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test
    public void payInvoice_correct_invoice_zero_subtotal() {

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

        Invoice inv3 = new Invoice("3",
                "SoftUni",
                1000D, Department.INCOMES,
                LocalDate.of(2017, 2, 12),
                LocalDate.of(2018, 3, 13));

        this.agency.create(inv1);
        this.agency.create(inv2);
        this.agency.create(inv3);

        final double expectedSubtotal = 0d;
        final double delta = 0.001d;

        this.agency.payInvoice(LocalDate.of(2018, 3, 12));
        Assert.assertEquals("Incorrect due date", expectedSubtotal, inv1.getSubtotal(), delta);
        Assert.assertEquals("Incorrect due date", expectedSubtotal, inv2.getSubtotal(), delta);
    }
}