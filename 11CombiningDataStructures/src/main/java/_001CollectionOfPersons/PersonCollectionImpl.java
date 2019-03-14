package _001CollectionOfPersons;

import java.util.*;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> emailPerson;
    private Map<String, SortedSet<Person>> domainPerson;
    private Map<String, SortedSet<Person>> nameTownPerson;
    private SortedMap<Integer, SortedSet<Person>> agePerson;
    private Map<String, SortedMap<Integer, SortedSet<Person>>> townAgePerson;


    public PersonCollectionImpl() {
        this.emailPerson = new HashMap<>();
        this.domainPerson = new HashMap<>();
        this.nameTownPerson = new HashMap<>();
        this.agePerson = new TreeMap<>();
        this.townAgePerson = new HashMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {

        if (this.emailPerson.containsKey(email)) {
            return false;
        }

        Person person = new Person(email, name, age, town);

        this.emailPerson.put(email, person);

        String domain = email.split("@")[1];
        this.domainPerson
                .putIfAbsent(domain, new TreeSet<>());
        this.domainPerson
                .get(domain)
                .add(person);

        String nameTown = name + " " + town;
        this.nameTownPerson
                .putIfAbsent(nameTown, new TreeSet<>());
        this.nameTownPerson
                .get(nameTown)
                .add(person);

        this.agePerson
                .putIfAbsent(age, new TreeSet<>());
        this.agePerson
                .get(age)
                .add(person);

        this.townAgePerson
                .putIfAbsent(town, new TreeMap<>());
        this.townAgePerson
                .get(town)
                .putIfAbsent(age, new TreeSet<>());
        this.townAgePerson
                .get(town)
                .get(age)
                .add(person);

        return true;
    }

    @Override
    public int getCount() {
        return this.emailPerson.size();
    }

    @Override
    public Person findPerson(String email) {
        return this.emailPerson
                .getOrDefault(email, null);
    }

    @Override
    public boolean deletePerson(String email) {

        if (!this.emailPerson.containsKey(email)) {
            return false;
        }

        Person removed = this.emailPerson
                .remove(email);

        this.domainPerson
                .get(email.split("@")[1])
                .remove(removed);

        this.nameTownPerson
                .get(removed.getName() + " " + removed.getTown())
                .remove(removed);

        this.agePerson
                .get(removed.getAge())
                .remove(removed);

        this.townAgePerson
                .get(removed.getTown())
                .get(removed.getAge())
                .remove(removed);

        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {

        return this.domainPerson
                .getOrDefault(emailDomain, new TreeSet<>());
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {

        return this.nameTownPerson
                .getOrDefault(name + " " + town, new TreeSet<>());
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {

        List<Person> result = new ArrayList<>();

        for (SortedSet<Person> set : this.agePerson
                .subMap(startAge, endAge + 1)
                .values()) {

            result.addAll(set);
        }

        return result;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {

        List<Person> result = new ArrayList<>();

        SortedMap<Integer, SortedSet<Person>> agePerson = this.townAgePerson
                .getOrDefault(town, new TreeMap<>());

        if (agePerson.isEmpty()) {
            return result;
        }

        Collection<SortedSet<Person>> sets = agePerson
                .subMap(startAge, endAge + 1)
                .values();

        for (SortedSet<Person> set : sets) {
            result.addAll(set);
        }

        return result;
    }
}
