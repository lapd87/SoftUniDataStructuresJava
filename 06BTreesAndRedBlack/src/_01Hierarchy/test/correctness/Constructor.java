package _01Hierarchy.test.correctness;

import _01Hierarchy.main.Hierarchy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import _01Hierarchy.test.helpers.BaseTest;
import _01Hierarchy.test.types.CorrectnessTests;

public class Constructor extends BaseTest {

    @Category(CorrectnessTests.class)
    @Test
    public void Constructor_NewHierarchyShouldHaveExactly1Element()
    {
        Hierarchy<Integer> hierarchy = new Hierarchy<>(5);
        Assert.assertEquals(1, hierarchy.getCount());
    }

    @Category(CorrectnessTests.class)
    @Test
    public void Constructor_NewHierarchyShouldHaveCorrectElement()
    {
        Hierarchy<Integer> hierarchy = new Hierarchy<>(5);
        Assert.assertTrue(hierarchy.Contains(5));
    }

    @Category(CorrectnessTests.class)
    @Test
    public void Hierarchy_ShouldBeGeneric()
    {
        Hierarchy<String> hierarchy = new Hierarchy<String>("test");
        Assert.assertTrue(hierarchy.Contains("test"));
    }
}
