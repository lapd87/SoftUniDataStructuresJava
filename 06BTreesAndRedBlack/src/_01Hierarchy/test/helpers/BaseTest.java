package _01Hierarchy.test.helpers;

import _01Hierarchy.main.IHierarchy;
import org.junit.Before;
import _01Hierarchy.test.HierarchyStructureInitializer;

public class BaseTest {

    protected static final int DefaultRootValue = 5;
    protected IHierarchy<Integer> Hierarchy;

    @Before
    public void setUp() {
        this.Hierarchy = HierarchyStructureInitializer.create(DefaultRootValue);
    }
}
