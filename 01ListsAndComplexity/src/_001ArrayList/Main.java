package _001ArrayList;

import _001ArrayList.arrayList.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(5);

        list.set(0, list.get(0) + 1);

        int element = list.removeAt(0);

        System.out.println(element);
    }
}
