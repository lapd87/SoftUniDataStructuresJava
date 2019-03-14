package _01Hierarchy.test;

import _01Hierarchy.main.Hierarchy;
import _01Hierarchy.main.IHierarchy;

public class HierarchyStructureInitializer {
    
    public static <T> IHierarchy<T> create(T root) {
        return new Hierarchy<>(root);
    }
}
