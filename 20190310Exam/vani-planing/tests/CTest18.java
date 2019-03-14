import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CTest18 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test
    public void getAllInvoiceInPeriod_return_invoices_correctly() {

        Invoice inv1 = new Invoice("1",
                "HRS",
                125D, Department.INCOMES,
                LocalDate.of(2018, 2, 12),
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

        List<Invoice> allInvoiceInPeriod = StreamSupport.stream(this.agency.getAllInvoiceInPeriod(LocalDate.of(2017, 2, 12), LocalDate.of(2017, 3, 12)).spliterator(), false)
                .collect(Collectors.toList());


        for (int i = 0; i < allInvoiceInPeriod.size(); i++) {
            Invoice invoice = allInvoiceInPeriod.get(i);
            Assert.assertEquals("Incorrect order", i == 0 ? "2" : "22", invoice.getNumber());
        }

    }
}