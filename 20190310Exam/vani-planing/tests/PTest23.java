import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PTest23 {

    private Generator generator;
    private Agency agency;

    @Before
    public void init() {
        this.agency = new AgencyImpl();
        this.generator = new Generator();
    }

    @Test
    public void throwInvoiceInPeriod_100000_elements() {

        List<Invoice> invoices = this.generator.generateInvoices(100000);

        invoices.forEach(i -> this.agency.create(i));

        Optional<Map.Entry<LocalDate, Long>> mostOccur = this.generator.getMostOccurrences(invoices,Invoice::getDueDate);

        LocalDate mostOccurDueDate = mostOccur.get().getKey();

        LocalDate minDueDate = mostOccurDueDate.minusYears(1L);
        LocalDate maxDueDate = mostOccurDueDate.plusYears(1L);

        long start = System.currentTimeMillis();
        try{
           this.agency.throwInvoiceInPeriod(minDueDate,maxDueDate);
        } catch (IllegalArgumentException ignored) {

        }
        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;
        // CHECK judge time
//   throw new IllegalArgumentException("Time: " + elapsedTime);

        Assert.assertTrue(elapsedTime <= 200);

    }

    static class Generator {
        private static final Random RANDOM = new Random();
        private static final String[] COMPANIES = {"HRS", "SoftUni", "Expedia", "SBTech", "Codexio", "VMWare", "Musala", "Chaos Group", "PaySafe", "Motion", "Locktrip"};

        List<Invoice> generateInvoices(int count) {
            List<Invoice> generated = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                String uuid = UUID.randomUUID().toString();
                String company = COMPANIES[Math.abs(RANDOM.nextInt()) % COMPANIES.length];
                double subTotal = RANDOM.nextDouble() * Math.abs(RANDOM.nextInt());
                Department department = Department.values()[Math.abs(RANDOM.nextInt()) % Department.values().length];
                LocalDate firstDate = LocalDate.ofEpochDay(getRandomDay(2010, 2015));
                LocalDate secondDate = LocalDate.ofEpochDay(getRandomDay(2018, 2020));
                generated.add(new Invoice(uuid, company, subTotal, department, firstDate, secondDate));

            }
            return generated;
        }

        <T> Optional<Map.Entry<T, Long>> getMostOccurrences(List<Invoice> invoices, Function<Invoice, T> function) {
            return invoices.stream()
                    .collect(Collectors.groupingBy(function, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue));
        }

        private long getRandomDay(int min, int max) {
            int minDay = (int) LocalDate.of(min, 1, 1).toEpochDay();
            int maxDay = (int) LocalDate.of(max, 1, 1).toEpochDay();
            return minDay + RANDOM.nextInt(maxDay - minDay);
        }
    }
}
