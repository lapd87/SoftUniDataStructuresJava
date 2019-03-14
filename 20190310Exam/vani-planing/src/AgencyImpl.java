import com.google.common.collect.Collections2;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AgencyImpl implements Agency {

    private Map<String, Invoice> byIds;
    private Map<LocalDate, Set<Invoice>> byDueDate;
    private List<Invoice> paidInvoices;

    public AgencyImpl() {
        this.byIds = new HashMap<>();
        this.byDueDate = new TreeMap<>();
        this.paidInvoices = new ArrayList<>();
    }

    @Override
    public void create(Invoice invoice) {

        if (this.byIds.containsKey(invoice.getNumber())) {
            this.throwException();
        }

        this.byIds.put(invoice.getNumber(), invoice);

        if (!this.byDueDate.containsKey(invoice.getDueDate())) {
            this.byDueDate.put(invoice.getDueDate(), new HashSet<>());
        }

        this.byDueDate.get(invoice.getDueDate()).add(invoice);

        if (invoice.getSubtotal() == 0) {
            this.paidInvoices.add(invoice);
        }
    }

    @Override
    public boolean contains(String number) {
        return this.byIds.containsKey(number);
    }

    @Override
    public int count() {
        return this.byIds.size();
    }

    @Override
    public void payInvoice(LocalDate dueDate) {
        Collection<Invoice> filtered = this.filterByDueDate(dueDate);

        filtered.forEach(f -> {
            f.setSubtotal(0);
            this.paidInvoices.add(f);
        });
    }

    @Override
    public void throwInvoice(String number) {
        this.throwIfNotExistentId(number);

        Invoice removed = this.byIds.remove(number);

        this.byDueDate.get(removed.getDueDate()).remove(removed);

        if (removed.getSubtotal() == 0) {
            this.paidInvoices.remove(removed);
        }
    }

    @Override
    public void throwPayed() {
        this.paidInvoices.forEach(i -> {
            this.byDueDate.get(i.getDueDate()).remove(i);
            this.byIds.remove(i.getNumber());
        });

        this.paidInvoices = new ArrayList<>();
    }

    @Override
    public Iterable<Invoice> getAllInvoiceInPeriod(LocalDate startDate, LocalDate endDate) {

        return Collections2.filter(this.byIds.values(),
                i -> {
                    assert i != null;
                    return i.getIssueDate().compareTo(startDate) >= 0
                            && i.getIssueDate().compareTo(endDate) <= 0;
                }).stream()
                .sorted(Comparator.comparing(Invoice::getIssueDate)
                        .thenComparing(Invoice::getDueDate))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Invoice> searchByNumber(String number) {

        List<Invoice> filtered = new ArrayList<>();

        Collections2.filter(this.byIds.keySet(),
                i -> {
                    assert i != null;
                    return i.contains(number);
                }).stream()
                .sorted()
                .forEach(k -> filtered.add(this.byIds.get(k)));

        if (filtered.size() <= 0) {
            this.throwException();
        }

        return filtered;
    }

    @Override
    public Iterable<Invoice> throwInvoiceInPeriod(LocalDate startDate, LocalDate endDate) {
        List<Invoice> filtered = Collections2.filter(this.byIds.values(),
                i -> {
                    assert i != null;
                    return i.getDueDate().compareTo(startDate) > 0
                            && i.getDueDate().compareTo(endDate) < 0;
                }).stream()
                .sorted(Comparator.comparing(Invoice::getIssueDate)
                        .thenComparing(Invoice::getDueDate))
                .collect(Collectors.toList());


        if (filtered.size() <= 0) {
            throwException();
        }

        filtered.forEach(i -> {
            this.byIds.remove(i.getNumber());
            this.byDueDate.get(i.getDueDate()).remove(i);

            if (i.getSubtotal() == 0) {
                this.paidInvoices.remove(i);
            }
        });

        return filtered;
    }

    @Override
    public Iterable<Invoice> getAllFromDepartment(Department department) {
        return Collections2.filter(this.byIds.values(),
                i -> {
                    assert i != null;
                    return i.getDepartment() == department;
                }).stream()
                .sorted(Comparator.comparingDouble(Invoice::getSubtotal).reversed()
                        .thenComparing(Invoice::getIssueDate))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Invoice> getAllByCompany(String companyName) {
        return Collections2.filter(this.byIds.values(),
                i -> {
                    assert i != null;
                    return i.getCompanyName().equals(companyName);
                }).stream()
                .sorted(Comparator.comparing(Invoice::getNumber).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void extendDeadline(LocalDate endDate, int days) {
        Collection<Invoice> filtered = this.filterByDueDate(endDate);

        LocalDate newDate = endDate.plusDays(days);
        filtered.forEach(i -> i.setDueDate(newDate));
    }

    private Collection<Invoice> filterByDueDate(LocalDate dueDate) {
        Set<Invoice> filtered = this.byDueDate.getOrDefault(dueDate, new HashSet<>());

        if (filtered.size() <= 0) {
            this.throwException();
        }

        return new ArrayList<>(filtered);
    }

    private void throwIfNotExistentId(String number) {
        if (!this.byIds.containsKey(number)) {
            this.throwException();
        }
    }

    private void throwException() {
        throw new IllegalArgumentException();
    }
}
