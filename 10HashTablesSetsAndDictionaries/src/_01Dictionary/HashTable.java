package _01Dictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashTable<TKey, TValue> implements Dictionary<TKey, TValue> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private LinkedList<KeyValue<TKey, TValue>>[] slots;
    private int size;
    private int capacity;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.slots = new LinkedList[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public void add(TKey key, TValue value) {
        addNewElement(key, value, "add");
    }

    @Override
    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public boolean addOrReplace(TKey key, TValue value) {
        return addNewElement(key, value, "addReplace");
    }

    @Override
    public TValue get(TKey key) {

        KeyValue<TKey, TValue> keyValue = find(key);

        if (keyValue == null) {
            throwException();
        }

        return keyValue.getValue();
    }

    @Override
    public boolean tryGetValue(TKey key, TValue value) {

        KeyValue<TKey, TValue> keyValue = find(key);

        return keyValue != null
                && keyValue.getValue().equals(value);
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int index = getIndex(key);

        LinkedList<KeyValue<TKey, TValue>> slot = this.slots[index];

        if (slot != null) {
            for (KeyValue<TKey, TValue> keyValue : slot) {
                if (keyValue.getKey().equals(key)) {
                    return keyValue;
                }
            }
        }

        return null;
    }

    @Override
    public boolean containsKey(TKey key) {
        return this.find(key) != null;
    }

    @Override
    public boolean remove(TKey key) {

        int index = getIndex(key);

        LinkedList<KeyValue<TKey, TValue>> slot = this.slots[index];

        if (slot != null) {

            for (int i = 0; i < slot.size(); i++) {

                if (slot.get(i).getKey().equals(key)) {
                    slot.remove(i);
                    this.size--;
                    return true;
                }
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.slots = new LinkedList[this.capacity];
    }

    @Override
    public Iterable<TKey> keys() {
        List<TKey> keys = new ArrayList<>();

        this.iterator()
                .forEachRemaining(kv -> keys.add(kv.getKey()));

        return keys;
    }

    @Override
    public Iterable<TValue> values() {
        List<TValue> values = new ArrayList<>();

        this.iterator()
                .forEachRemaining(kv -> values.add(kv.getValue()));

        return values;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {

        LinkedList<KeyValue<TKey, TValue>> elements = new LinkedList<>();

        for (LinkedList<KeyValue<TKey, TValue>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<TKey, TValue> keyValue : slot) {
                    elements.addLast(keyValue);
                }
            }
        }

        return elements.iterator();
    }

    private int getIndex(TKey key) {
        return Math.abs(key.hashCode()) % this.capacity;
    }

    @SuppressWarnings("unchecked")
    private void growIfNeeded() {
        if (this.size + 1 >= LOAD_FACTOR * this.capacity) {

            this.capacity *= 2;

            HashTable newTable = new HashTable(this.capacity);

            for (KeyValue<TKey, TValue> keyValue : this) {
                newTable.add(keyValue.getKey(), keyValue.getValue());
            }

            this.slots = newTable.slots;
        }
    }

    private void throwException() {
        throw new IllegalArgumentException();
    }

    private boolean addNewElement(TKey key, TValue value, String methodCalled) {

        growIfNeeded();

        int index = getIndex(key);

        if (this.slots[index] == null) {
            this.slots[index] = new LinkedList<>();
        }

        for (KeyValue<TKey, TValue> keyValue : this.slots[index]) {
            if (keyValue.getKey().equals(key)) {
                if ("add".equals(methodCalled)) {
                    throwException();
                } else if ("addReplace".equals(methodCalled)) {
                    keyValue.setValue(value);
                    return true;
                }
            }
        }

        this.slots[index].addLast(new KeyValue<>(key, value));

        this.size++;

        return false;
    }
}
