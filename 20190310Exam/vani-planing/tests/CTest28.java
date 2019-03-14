import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

public class CTest28 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test
    public void getAllFromDepartment_should_return_empty_collection() {


        Invoice inv1 = new Invoice("b48ab542-3aba-4588-8450-c00c2f58d276",
                "HRS",
                125D, Department.INCOMES,
                LocalDate.of(2018, 2, 12),
                LocalDate.of(2018, 3, 13));

        Invoice inv2 = new Invoice("43c96303-3032-4a7f-9924-054d148c3787",
                "SoftUni",
                1000D, Department.INCOMES,
                LocalDate.of(2017, 2, 12),
                LocalDate.of(2018, 3, 12));

        Invoice inv3 = new Invoice("a65e6062-4e0d-48e0-8018-b76095b71d52",
                "SoftUni",
                1000D, Department.SELLS,
                LocalDate.of(2017, 2, 12),
                LocalDate.of(2018, 3, 13));

        this.agency.create(inv1);
        this.agency.create(inv2);
        this.agency.create(inv3);

        int actualCount = (int) StreamSupport.stream(this.agency.getAllFromDepartment(Department.INTERNALS).spliterator(), false).count();
        final int expectedResult = 0;
        Assert.assertEquals("Returned collection is not empty", expectedResult, actualCount);
    }
}