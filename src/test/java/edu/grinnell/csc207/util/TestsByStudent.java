package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Matrix class.
 *
 * @author Natalie Nardone
 */
public class TestsByStudent {

  /**
   * Make sure we can set and get values, and that we can get the correct width and height.
   */
  @Test
  public void testSetGetWidthHeight() {
    Matrix<String> test = new MatrixV0<String>(3, 2);
    test.set(0, 0, "apple");
    test.set(0, 2, "orange");
    test.set(1, 1, "banana");

    assertMatrixEquals(new String[][] {{"apple", null, "orange"}, {null, "banana", null}}, test,
    "Correct matrix after setting");

    assertEquals("apple", test.get(0,0), "Gets correct value 1");
    assertEquals("orange", test.get(0,2), "Gets correct value 2");
    assertEquals("banana", test.get(1,1), "Gets correct value 3");
    assertEquals(null, test.get(0,1), "Gets null correctly");
    assertEquals(null, test.get(1,0), "Gets null correctly");
    assertEquals(null, test.get(1,2), "Gets null correctly");

    assertEquals(3, test.width(), "Gets correct width");
    assertEquals(2, test.height(), "Gets correct height");
  } // testSetGetWidthHeight()

  /**
   * Make sure that we can insert rows of both default and other values, and the height and width are correctly updated.
   */
  @Test
  public void testInsertRow() throws ArraySizeException {
    Matrix<String> test = new MatrixV0<String>(3, 2);
    test.set(0, 0, "apple");
    test.set(0, 2, "orange");
    test.set(1, 1, "banana");

    test.insertRow(0);
    assertMatrixEquals(new String[][] {{null, null, null}, {"apple", null, "orange"}, {null, "banana", null}}, test, 
    "Correct matrix after inserting row at beginning");

    assertEquals(3, test.width(), "Gets correct width after inserting row at beginning");
    assertEquals(3, test.height(), "Gets correct height after inserting row at beginning");

    test.insertRow(3);
    assertMatrixEquals(new String[][] {{null, null, null}, {"apple", null, "orange"}, {null, "banana", null}, {null, null, null}}, test, 
    "Correct matrix after inserting row at end");

    assertEquals(3, test.width(), "Gets correct width after inserting row at end");
    assertEquals(4, test.height(), "Gets correct height after inserting row at end");

    Matrix<String> test2 = new MatrixV0<String>(3, 2);
    test2.set(0, 0, "apple");
    test2.set(0, 2, "orange");
    test2.set(1, 1, "banana");

    test2.insertRow(0, new String [] {"x", "x", "x"});
    assertMatrixEquals(new String[][] {{"x", "x", "x"}, {"apple", null, "orange"}, {null, "banana", null}}, test2, 
    "Correct matrix after inserting row of values at beginning");

    assertEquals(3, test2.width(), "Gets correct width after inserting row at beginning");
    assertEquals(3, test2.height(), "Gets correct height after inserting row at beginning");

    test2.insertRow(3, new String [] {"x", "x", "x"});
    assertMatrixEquals(new String[][] {{"x", "x", "x"}, {"apple", null, "orange"}, {null, "banana", null}, {"x", "x", "x"}}, test2, 
    "Correct matrix after inserting row of values at end");

    assertEquals(3, test2.width(), "Gets correct width after inserting row at end");
    assertEquals(4, test2.height(), "Gets correct height after inserting row at end");
  } // testInsertRow()

  /**
   * Make sure that we can insert columns of both default and other values, and the height and width are correctly updated.
   */
  @Test
  public void testInsertCol() throws ArraySizeException {
    Matrix<String> test = new MatrixV0<String>(3, 2);
    test.set(0, 0, "apple");
    test.set(0, 2, "orange");
    test.set(1, 1, "banana");

    test.insertCol(0);
    assertMatrixEquals(new String[][] {{null, "apple", null, "orange"}, {null, null, "banana", null}}, test, 
    "Correct matrix after inserting col at beginning");

    assertEquals(4, test.width(), "Gets correct width after inserting col at beginning");
    assertEquals(2, test.height(), "Gets correct height after inserting col at beginning");

    test.insertCol(4);
    assertMatrixEquals(new String[][] {{null, "apple", null, "orange", null}, {null, null, "banana", null, null}}, test, 
    "Correct matrix after inserting col at end");

    assertEquals(5, test.width(), "Gets correct width after inserting col at end");
    assertEquals(2, test.height(), "Gets correct height after inserting col at end");

    Matrix<String> test2 = new MatrixV0<String>(3, 2);
    test2.set(0, 0, "apple");
    test2.set(0, 2, "orange");
    test2.set(1, 1, "banana");

    test2.insertCol(0, new String[] {"x", "x"});
    assertMatrixEquals(new String[][] {{"x", "apple", null, "orange"}, {"x", null, "banana", null}}, test2, 
    "Correct matrix after inserting col of values at beginning");

    assertEquals(4, test2.width(), "Gets correct width after inserting col at beginning");
    assertEquals(2, test2.height(), "Gets correct height after inserting col at beginning");

    test2.insertCol(4, new String[] {"x", "x"});
    assertMatrixEquals(new String[][] {{"x", "apple", null, "orange", "x"}, {"x", null, "banana", null, "x"}}, test2, 
    "Correct matrix after inserting col of values at end");

    assertEquals(5, test2.width(), "Gets correct width after inserting col at end");
    assertEquals(2, test2.height(), "Gets correct height after inserting col at end");
  } // testInsertCol()

  /**
   * Make sure that we can delete rows.
   */
  @Test
  public void testDeleteRow() throws ArraySizeException {
    Matrix<Integer> test = new MatrixV0<Integer>(3, 3);
    test.set(0, 0, Integer.valueOf(0));
    test.set(0, 1, Integer.valueOf(1));
    test.set(0, 2, Integer.valueOf(2));
    test.set(1, 0, Integer.valueOf(3));
    test.set(1, 1, Integer.valueOf(4));
    test.set(1, 2, Integer.valueOf(5));
    test.set(2, 0, Integer.valueOf(6));
    test.set(2, 1, Integer.valueOf(7));
    test.set(2, 2, Integer.valueOf(8));

    test.deleteRow(0);
    assertMatrixEquals(new Integer[][] {{3,4,5},{6,7,8}}, test, "Correctly deletes first row");
    assertEquals(2, test.height(), "Correct height after deleting row");
    
    test.insertRow(0, new Integer[] {0,1,2});

    test.deleteRow(2);
    assertMatrixEquals(new Integer[][] {{0,1,2},{3,4,5}}, test, "Correctly deletes last row");
    assertEquals(2, test.height(), "Correct height after deleting row");

    test.insertRow(2, new Integer[] {6,7,8});

    test.deleteRow(1);
    assertMatrixEquals(new Integer[][] {{0,1,2},{6,7,8}}, test, "Correctly deletes middle row");
    assertEquals(2, test.height(), "Correct height after deleting row");
  } // testDeleteRow()

  /**
   * Make sure that we can delete columns.
   */
  @Test
  public void testDeleteCol() throws ArraySizeException {
    Matrix<Integer> test = new MatrixV0<Integer>(3, 3);
    test.set(0, 0, Integer.valueOf(0));
    test.set(0, 1, Integer.valueOf(1));
    test.set(0, 2, Integer.valueOf(2));
    test.set(1, 0, Integer.valueOf(3));
    test.set(1, 1, Integer.valueOf(4));
    test.set(1, 2, Integer.valueOf(5));
    test.set(2, 0, Integer.valueOf(6));
    test.set(2, 1, Integer.valueOf(7));
    test.set(2, 2, Integer.valueOf(8));

    test.deleteCol(0);
    assertMatrixEquals(new Integer[][] {{1,2},{4,5},{7,8}}, test, "Correctly deletes first column");
    assertEquals(2, test.width(), "Correct width after deleting column");
    
    test.insertCol(0, new Integer[] {0,3,6});

    test.deleteCol(2);
    assertMatrixEquals(new Integer[][] {{0,1},{3,4},{6,7}}, test, "Correctly deletes last column");
    assertEquals(2, test.width(), "Correct width after deleting column");
    
    test.insertCol(2, new Integer[] {2,5,8});

    test.deleteCol(1);
    assertMatrixEquals(new Integer[][] {{0,2},{3,5},{6,8}}, test, "Correctly deletes middle column");
    assertEquals(2, test.width(), "Correct width after deleting column");
    
    test.insertCol(1, new Integer[] {1,4,7});
  } // testDeleteCol()

  @Test
  public void testFillRegion() {
    Matrix<Integer> test = new MatrixV0<Integer>(3, 3);
    test.set(0, 0, Integer.valueOf(0));
    test.set(0, 1, Integer.valueOf(1));
    test.set(0, 2, Integer.valueOf(2));
    test.set(1, 0, Integer.valueOf(3));
    test.set(1, 1, Integer.valueOf(4));
    test.set(1, 2, Integer.valueOf(5));
    test.set(2, 0, Integer.valueOf(6));
    test.set(2, 1, Integer.valueOf(7));
    test.set(2, 2, Integer.valueOf(8));

    test.fillRegion(0, 0, 3, 3, Integer.valueOf(9));
    assertMatrixEquals(new Integer[][] {{9,9,9},{9,9,9},{9,9,9}}, test, "Correctly fills entire matrix");

    Matrix<Integer> test2 = new MatrixV0<Integer>(3, 3);
    test2.set(0, 0, Integer.valueOf(0));
    test2.set(0, 1, Integer.valueOf(1));
    test2.set(0, 2, Integer.valueOf(2));
    test2.set(1, 0, Integer.valueOf(3));
    test2.set(1, 1, Integer.valueOf(4));
    test2.set(1, 2, Integer.valueOf(5));
    test2.set(2, 0, Integer.valueOf(6));
    test2.set(2, 1, Integer.valueOf(7));
    test2.set(2, 2, Integer.valueOf(8));

    test2.fillRegion(1, 0, 3, 2, Integer.valueOf(9));
    assertMatrixEquals(new Integer[][] {{0,1,2},{9,9,5},{9,9,8}}, test2, "Correctly fills part of matrix");
  }

  @Test
  public void testFillLine() {
    Matrix<Integer> test = new MatrixV0<Integer>(3, 3);
    test.set(0, 0, Integer.valueOf(0));
    test.set(0, 1, Integer.valueOf(1));
    test.set(0, 2, Integer.valueOf(2));
    test.set(1, 0, Integer.valueOf(3));
    test.set(1, 1, Integer.valueOf(4));
    test.set(1, 2, Integer.valueOf(5));
    test.set(2, 0, Integer.valueOf(6));
    test.set(2, 1, Integer.valueOf(7));
    test.set(2, 2, Integer.valueOf(8));

    test.fillLine(0,1,1,0,3,2, Integer.valueOf(9));
    assertMatrixEquals(new Integer[][] {{0,9,2},{3,9,5},{6,9,8}}, test, "Correctly fills vertical line");

    Matrix<Integer> test2 = new MatrixV0<Integer>(3, 3);
    test2.set(0, 0, Integer.valueOf(0));
    test2.set(0, 1, Integer.valueOf(1));
    test2.set(0, 2, Integer.valueOf(2));
    test2.set(1, 0, Integer.valueOf(3));
    test2.set(1, 1, Integer.valueOf(4));
    test2.set(1, 2, Integer.valueOf(5));
    test2.set(2, 0, Integer.valueOf(6));
    test2.set(2, 1, Integer.valueOf(7));
    test2.set(2, 2, Integer.valueOf(8));

    test2.fillLine(1, 0, 0, 1, 2, 3, Integer.valueOf(9));
    assertMatrixEquals(new Integer[][] {{0,1,2},{9,9,9},{6,7,8}}, test2, "Correctly fills horizontal line");

    Matrix<Integer> test3 = new MatrixV0<Integer>(3, 3);
    test3.set(0, 0, Integer.valueOf(0));
    test3.set(0, 1, Integer.valueOf(1));
    test3.set(0, 2, Integer.valueOf(2));
    test3.set(1, 0, Integer.valueOf(3));
    test3.set(1, 1, Integer.valueOf(4));
    test3.set(1, 2, Integer.valueOf(5));
    test3.set(2, 0, Integer.valueOf(6));
    test3.set(2, 1, Integer.valueOf(7));
    test3.set(2, 2, Integer.valueOf(8));

    test3.fillLine(0, 0, 1, 1, 3, 3, Integer.valueOf(9));
    assertMatrixEquals(new Integer[][] {{9,1,2},{3,9,5},{6,7,9}}, test3, "Correctly fills diagonal line");
  }

  /**
   * Make sure matrix can be cloned.
   */
  @Test
  public void testClone() {
    Matrix<String> test1 = new MatrixV0<String>(3, 2);
    test1.set(0, 0, "apple");
    test1.set(0, 2, "orange");
    test1.set(1, 1, "banana");

    Matrix<String> test2 = test1.clone();
    assertMatrixEquals(new String[][] {{"apple", null, "orange"}, {null, "banana", null}}, test2,
    "Matrix clones correctly");
  } // testClone()

  /**
   * Make sure we can correctly check equality.
   */
  @Test
  public void testEquals() {
    Matrix<String> test1 = new MatrixV0<String>(3, 2);
    test1.set(0, 0, "apple");
    test1.set(0, 2, "orange");
    test1.set(1, 1, "banana");

    Matrix<String> test2 = new MatrixV0<String>(3, 2);
    test2.set(0, 0, "apple");
    test2.set(0, 2, "orange");
    test2.set(1, 1, "banana");

    Matrix<String> test3 = new MatrixV0<String>(3, 2);
    test3.set(0, 1, "apple");
    test3.set(0, 2, "orange");
    test3.set(1, 1, "banana");

    Matrix<String> test4 = new MatrixV0<String>(2, 2);
    test3.set(0, 0, "apple");
    test3.set(0, 1, "orange");
    test3.set(1, 1, "banana");

    Matrix<String> test5 = new MatrixV0<String>(3, 1);
    test3.set(0, 1, "apple");
    test3.set(0, 2, "orange");

    assertEquals(true, test1.equals(test2));
    assertEquals(false, test1.equals(test3));
    assertEquals(false, test1.equals(test4));
    assertEquals(false, test1.equals(test5));
  } // testEquals()

}
