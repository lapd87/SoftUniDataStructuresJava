package _01FirstLastList.tests;

import _01FirstLastList.FirstLastList;
import _01FirstLastList.IFirstLastList;

public class FirstLastListFactory {
    public static <T extends Comparable<T>> IFirstLastList<T> create() {
    	return new FirstLastList<T>();
    }
}
