package agh.project.simulation.creations.attributes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    Vector2d test1 = new Vector2d(1, 1);
    Vector2d test2 = new Vector2d(0, 0);
    Vector2d test3 = new Vector2d(-1, 1);
    Vector2d test4 = new Vector2d(1, - 1);
    Vector2d test5 = new Vector2d(-2, -2);
    Vector2d test6 = new Vector2d(2, 2);
    Vector2d test7 = new Vector2d(-1, 1);
    Vector2d test8 = new Vector2d(-2, -2);
    Vector2d test9 = new Vector2d(-9, 10);
    Vector2d test10 = new Vector2d(12, -4);

    @Test
    void precedes() {
        //True
        assertTrue(test1.precedes(test1));
        assertTrue(test2.precedes(test1));
        assertTrue(test3.precedes(test1));
        assertTrue(test5.precedes(test6));
        assertTrue(test5.precedes(test2));

        //False
        assertFalse(test3.precedes(test4));
        assertFalse(test6.precedes(test5));
        assertFalse(test1.precedes(test5));
        assertFalse(test6.precedes(test1));
        assertFalse(test1.precedes(test4));
    }

    @Test
    void follows() {
        //True
        assertTrue(test1.follows(test1));
        assertTrue(test5.follows(test5));
        assertTrue(test6.follows(test1));
        assertTrue(test2.follows(test5));
        assertTrue(test3.follows(test5));

        //False
        assertFalse(test3.follows(test1));
        assertFalse(test3.follows(test4));
        assertFalse(test5.follows(test6));
        assertFalse(test1.follows(test6));
        assertFalse(test4.follows(test2));

    }

    @Test
    void add() {
        //Not null
        assertNotNull(test1.add(test2));
        assertNotNull(test2.add(test3));
        assertNotNull(test1.add(test1));

        //Equal
        assertEquals(new Vector2d(1, 1), test1.add(test2));
        assertEquals(new Vector2d(2, 2), test1.add(test1));
        assertEquals(new Vector2d(-1, -1), test5.add(test1));
        assertEquals(new Vector2d(0, 0), test5.add(test6));
        assertEquals(new Vector2d(-1, -3), test5.add(test4));

        //Not Equal
        assertNotEquals(new Vector2d(2,1), test6.add(test2));
        assertNotEquals(new Vector2d(0,0), test1.add(test2));
        assertNotEquals(new Vector2d(0,1), test1.add(test3));
    }

    @Test
    void subtract() {
        //Not Null
        assertNotNull(test1.subtract(test1));
        assertNotNull(test1.subtract(test2));
        assertNotNull(test6.subtract(test5));

        //Equals
        assertEquals(new Vector2d(0,0), test1.subtract(test1));
        assertEquals(new Vector2d(2,0), test1.subtract(test3));
        assertEquals(new Vector2d(3, 1), test4.subtract(test5));
        assertEquals(new Vector2d(-4, -4), test5.subtract(test6));
        assertEquals(new Vector2d(-2, 2), test3.subtract(test4));

        //Not Equals
        assertNotEquals(new Vector2d(0, 1), test1.subtract(test1));
        assertNotEquals(new Vector2d(0, 2), test6.subtract(test1));
        assertNotEquals(new Vector2d(4, 4), test5.subtract(test6));

    }

    @Test
    void upperRight() {
        //Not Null
        assertNotNull(test1.upperRight(test2));
        assertNotNull(test1.upperRight(test1));
        assertNotNull(test5.upperRight(test6));

        //Equals
        assertEquals(new Vector2d(1,1), test1.upperRight(test3));
        assertEquals(new Vector2d(2,2), test6.upperRight(test5));
        assertEquals(new Vector2d(1,1), test3.upperRight(test4));
        assertEquals(new Vector2d(12,10), test9.upperRight(test10));
        assertEquals(new Vector2d(12,1), test1.upperRight(test10));

        //Not Equals
        assertNotEquals(new Vector2d(-9,-4), test9.upperRight(test10));
        assertNotEquals(new Vector2d(-1,-1), test3.upperRight(test4));
        assertNotEquals(new Vector2d(-1,1), test3.upperRight(test4));
    }

    @Test
    void lowerLeft() {
        //Not Null
        assertNotNull(test10.lowerLeft(test1));
        assertNotNull(test10.lowerLeft(test10));
        assertNotNull(test9.lowerLeft(test2));

        //Equals
        assertEquals(new Vector2d(-9, -4), test9.lowerLeft(test10));
        assertEquals(new Vector2d(-9, -2), test9.lowerLeft(test5));
        assertEquals(new Vector2d(-1, 1), test1.lowerLeft(test3));
        assertEquals(new Vector2d(-2, -2), test5.lowerLeft(test4));
        assertEquals(new Vector2d(-1, -1), test3.lowerLeft(test4));

        //NotEquals
        assertNotEquals(new Vector2d(12, 10), test10.lowerLeft(test9));
        assertNotEquals(new Vector2d(-2, 2), test5.lowerLeft(test6));
        assertNotEquals(new Vector2d(2, -1), test4.lowerLeft(test6));
    }

    @Test
    void opposite() {
        //Not Null
        assertNotNull(test10.opposite());
        assertNotNull(test9.opposite());
        assertNotNull(test8.opposite());

        //Equals
        assertEquals(new Vector2d(-1, -1), test1.opposite());
        assertEquals(new Vector2d(0, 0), test2.opposite());
        assertEquals(new Vector2d(1, -1), test3.opposite());
        assertEquals(new Vector2d(-1, 1), test4.opposite());
        assertEquals(new Vector2d(-12, 4), test10.opposite());

        //NotEquals
        assertNotEquals(new Vector2d(9, 10), test9.opposite());
        assertNotEquals(new Vector2d(2, -2), test6.opposite());
        assertNotEquals(new Vector2d(-1, -1), test4.opposite());
    }
}