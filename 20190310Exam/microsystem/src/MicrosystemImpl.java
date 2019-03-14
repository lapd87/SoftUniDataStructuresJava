import com.google.common.collect.Collections2;

import java.util.*;
import java.util.stream.Collectors;

public class MicrosystemImpl implements Microsystem {

    private Map<Integer, Computer> byIds;

    public MicrosystemImpl() {
        this.byIds = new HashMap<>();
    }

    @Override
    public void createComputer(Computer computer) {
        if (this.byIds.containsKey(computer.getNumber())) {
            this.throwException();
        }

        byIds.put(computer.getNumber(), computer);
    }

    @Override
    public boolean contains(int number) {
        return this.byIds.containsKey(number);
    }

    @Override
    public int count() {
        return this.byIds.size();
    }

    @Override
    public Computer getComputer(int number) {
        this.throwIfNotExistentId(number);

        return this.byIds.get(number);
    }

    @Override
    public void remove(int number) {
        this.throwIfNotExistentId(number);

        this.byIds.remove(number);
    }

    @Override
    public void removeWithBrand(Brand brand) {

        boolean hasRemoved = this.byIds.values().removeIf(c -> c.getBrand() == brand);

        if (!hasRemoved){
            throwException();
        }
    }

    @Override
    public void upgradeRam(int ram, int number) {
        this.throwIfNotExistentId(number);

        this.byIds.get(number).setRAM(ram);
    }

    @Override
    public Iterable<Computer> getAllFromBrand(Brand brand) {
        return Collections2.filter(this.byIds.values(),
                (c) -> {
                    assert c != null;
                    return c.getBrand() == brand;
                }).stream()
                .sorted(Comparator.comparingDouble(Computer::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getAllWithScreenSize(double screenSize) {
        return Collections2.filter(this.byIds.values(),
                (c) -> {
                    assert c != null;
                    return c.getScreenSize() == screenSize;
                }).stream()
                .sorted(Comparator.comparingInt(Computer::getNumber).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getAllWithColor(String color) {
        return Collections2.filter(this.byIds.values(),
                (c) -> {
                    assert c != null;
                    return c.getColor().equals(color);
                }).stream()
                .sorted(Comparator.comparingDouble(Computer::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getInRangePrice(double minPrice, double maxPrice) {
        return Collections2.filter(this.byIds.values(),
                (c) -> {
                    assert c != null;
                    return c.getPrice() >= minPrice && c.getPrice() <= maxPrice;
                }).stream()
                .sorted(Comparator.comparingDouble(Computer::getPrice).reversed())
                .collect(Collectors.toList());
    }

    private void throwIfNotExistentId(int number) {
        if (!this.byIds.containsKey(number)) {
            this.throwException();
        }
    }

    private void throwException() {
        throw new IllegalArgumentException();
    }
}
