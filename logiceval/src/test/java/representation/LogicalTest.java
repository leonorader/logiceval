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
        assertTrue(Logical.not(x));
    }

    @Test
    public void negationTest2() {
        boolean x = true;
        assertFalse(Logical.not(x));
    }

    @Test
    public void andTest() {
        boolean x = true;
        boolean y = true;
        assertTrue(Logical.and(x,y));
    }

    @Test
    public void andTest2() {
        boolean x = true;
        boolean y = false;
        assertFalse(Logical.and(x,y));
    }

    @Test
    public void andTest3() {
        boolean x = false;
        boolean y = true;
        assertFalse(Logical.and(x,y));
    }

    @Test
    public void andTest4() {
        boolean x = false;
        boolean y = false;
        assertFalse(Logical.and(x,y));
    }

    @Test
    public void andTest5() {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        assertFalse(Logical.and(x,y,z));
    }

    @Test
    public void andTest6() {
        boolean x = false;
        boolean y = true;
        boolean z = false;
        boolean zz = true;
        assertFalse(Logical.and(x,y,z,zz));
    }

    @Test
    public void orTest() {
        boolean x = false;
        boolean y = false;
        assertFalse(Logical.or(x,y));
    }

    @Test
    public void orTest2() {
        boolean x = true;
        boolean y = true;
        assertTrue(Logical.or(x,y));
    }

    @Test
    public void orTest3() {
        boolean x = true;
        boolean y = false;
        assertTrue(Logical.or(x,y));
    }

    @Test
    public void orTest4() {
        boolean x = false;
        boolean y = true;
        assertTrue(Logical.or(x,y));
    }

    @Test
    public void orTest5() {
        boolean x = false;
        boolean y = true;
        boolean z = true;
        assertTrue(Logical.or(x,y,z));
    }

    @Test
    public void orTest6() {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        boolean zz = false;
        assertFalse(Logical.or(x,y,z,zz));
    }

    @Test
    public void impliesTest() {
        boolean x = true;
        boolean y = false;
        assertFalse(Logical.implies(x,y));
    }



    @Test
    public void impliesTest2() {
        boolean x = true;
        boolean y = true;
        assertTrue(Logical.implies(x,y));
    }

    @Test
    public void impliesTest3() {
        boolean x = false;
        boolean y = true;
        assertTrue(Logical.implies(x,y));
    }

    @Test
    public void impliesTest4() {
        boolean x = false;
        boolean y = false;
        assertTrue(Logical.implies(x,y));
    }




}
