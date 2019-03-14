package _01Dictionary;

public class Main {

    public static void main(String[] args) {

        Dictionary<String, Integer> dictionary = new HashTable<>();

        dictionary.add("ss", 5);
        dictionary.addOrReplace("ss", 6);
        dictionary.add("sss", 6);
        dictionary.add("ssss", 6);

        dictionary.forEach(kv -> System.out.println(kv.getKey() + " " + kv.getValue()));
    }
}
