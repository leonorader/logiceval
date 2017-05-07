package representation;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by Grekton on 2017.05.07..
 */
public class LogicalTest {

    @Test
    public void negationTest() {
        boolean x = false;
        assertTrue(new Logical().not(x));
    }

    @Test
    public void negationTest2() {
        boolean x = true;
        assertFalse(new Logical().not(x));
    }

    @Test
    public void andTest() {
        boolean x = true;
        boolean y = true;
        assertTrue(new Logical().and(x,y));
    }

    @Test
    public void andTest2() {
        boolean x = true;
        boolean y = false;
        assertFalse(new Logical().and(x,y));
    }

    @Test
    public void andTest3() {
        boolean x = false;
        boolean y = true;
        assertFalse(new Logical().and(x,y));
    }

    @Test
    public void andTest4() {
        boolean x = false;
        boolean y = false;
        assertFalse(new Logical().and(x,y));
    }

    @Test
    public void orTest() {
        boolean x = false;
        boolean y = false;
        assertFalse(new Logical().or(x,y));
    }

    @Test
    public void orTest2() {
        boolean x = true;
        boolean y = true;
        assertTrue(new Logical().or(x,y));
    }

    @Test
    public void orTest3() {
        boolean x = true;
        boolean y = false;
        assertTrue(new Logical().or(x,y));
    }

    @Test
    public void orTest4() {
        boolean x = false;
        boolean y = true;
        assertTrue(new Logical().or(x,y));
    }

    @Test
    public void impliesTest() {
        boolean x = true;
        boolean y = false;
        assertFalse(new Logical().implies(x,y));
    }

    @Test
    public void impliesTest2() {
        boolean x = true;
        boolean y = true;
        assertTrue(new Logical().implies(x,y));
    }

    @Test
    public void impliesTest3() {
        boolean x = false;
        boolean y = true;
        assertTrue(new Logical().implies(x,y));
    }

    @Test
    public void impliesTest4() {
        boolean x = false;
        boolean y = false;
        assertTrue(new Logical().implies(x,y));
    }




}
