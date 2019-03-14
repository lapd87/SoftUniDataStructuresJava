import _03ArrayStack.ArrayStack;
import org.junit.Test;

import java.sql.Date;
import java.util.EmptyStackException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayStackTests {

    @Test
    public void pushPopElement() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(33);
        assertEquals(1, stack.size());

        int popped = stack.pop();
        assertEquals(33, popped);
        assertEquals(0, stack.size());
    }

    @Test
    public void pushPopThousandElements() {
        ArrayStack<String> stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        for (int i = 1; i <= 1000; i++) {
            stack.push(i + "");
            assertEquals(i, stack.size());
        }

        String popped = "";
        for (int i = 1000; i >= 1; i--) {
            popped = stack.pop();
            assertEquals(i, Integer.parseInt(popped));
            assertEquals(i - 1, stack.size());
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void popFromEmptyStack(){
        ArrayStack<String> stack = new ArrayStack<>();
        stack.pop();
    }

    @Test
    public void pushPopWithInitialCapacity(){
        ArrayStack<Integer> stack = new ArrayStack<>(1);
        assertEquals(0, stack.size());

        stack.push(33);
        assertEquals(1, stack.size());

        stack.push(55);
        assertEquals(2, stack.size());

        int popped = stack.pop();
        assertEquals(55, popped);
        assertEquals(1, stack.size());

        popped = stack.pop();
        assertEquals(33, popped);
        assertEquals(0, stack.size());
    }

    @Test
    public void toArrayTest(){
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(11);
        stack.push(-22);
        stack.push(33);
        stack.push(44);

        assertArrayEquals(new Integer[]{44, 33, -22, 11}, stack.toArray());

    }

    @Test
    public void toArrayEmptyStack(){
        ArrayStack<Date> stack = new ArrayStack<>();
        assertArrayEquals(new Date[]{}, stack.toArray());
    }
}