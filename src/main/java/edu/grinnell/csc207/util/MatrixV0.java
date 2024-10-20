package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Natalie Nardone
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The contents of the matrix.
   */
  T[][] contents;

  /**
   * The width of the matrix.
   */
  int width;

  /**
   * The height of the matrix.
   */
  int height;

  /**
   * The default value to return.
   */
  T defaultVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    if ((width < 0) || (height < 0)) {
      throw new NegativeArraySizeException();
    } else {
      this.width = width;
      this.height = height;
      this.defaultVal = def;
      contents = (T[][]) new Object[height][];
      for (int i = 0; i < height; i++) {
        contents[i] = (T[]) new Object[width];
      } // for
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          contents[i][j] = def;
        } // for
      } // for
    } // if/else
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if ((row < 0) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } else {
      return contents[row][col];
    } // if/else
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if ((row < 0) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } else {
      contents[row][col] = val;
    } // if/else
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    T[] vals = (T[]) new Object[this.width];
    java.util.Arrays.fill(vals, defaultVal);
    try {
      this.insertRow(row, vals);
    } catch (ArraySizeException e) {
      // should never reach this point
    } // try/catch
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if ((row > this.height) || (row < 0)) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.width) {
      throw new ArraySizeException();
    } // if/else
    this.contents = java.util.Arrays.copyOf(this.contents, this.contents.length + 1);
    // move all array elements after the row inserted
    for (int i = this.height; i > row; i--) {
      this.contents[i] = this.contents[i - 1];
    } // for
    // new row contains specified values
    this.contents[row] = vals;
    this.height++;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    T[] vals = (T[]) new Object[this.height];
    java.util.Arrays.fill(vals, defaultVal);
    try {
      this.insertCol(col, vals);
    } catch (ArraySizeException e) {
      // should never reach this point
    } // try/catch
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if ((col > this.width) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if/else
    for (int i = 0; i < this.height; i++) {
      this.contents[i] = java.util.Arrays.copyOf(this.contents[i], this.contents[i].length + 1);
      for (int j = this.width; j > col; j--) {
        this.contents[i][j] = this.contents[i][j - 1];
      } // for
      this.contents[i][col] = vals[i];
    } // for
    this.width++;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if ((row >= this.height) || (row < 0)) {
      throw new IndexOutOfBoundsException();
    } // if
    this.height--;
    for (int i = row; i < this.height; i++) {
      this.contents[i] = this.contents[i + 1];
    } // for
    this.contents = java.util.Arrays.copyOf(this.contents, this.contents.length - 1);
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if ((col >= this.width) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } // if
    this.width--;
    for (int i = 0; i < this.height; i++) {
      for (int j = col; j < this.width; j++) {
        this.contents[i][j] = this.contents[i][j + 1];
      } // for
      this.contents[i] = java.util.Arrays.copyOf(this.contents[i], this.contents[i].length - 1);
    } // for
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if ((startRow < 0) || (startCol < 0) || (endRow > this.height) || (endCol > this.width)) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        this.contents[i][j] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if ((startRow < 0) || (startCol < 0) || (endRow > this.height) || (endCol > this.width)) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = startRow, j = startCol; i < endRow && j < endCol; i += deltaRow, j += deltaCol) {
      this.contents[i][j] = val;
    } // for
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> copy = new MatrixV0<T>(this.width, this.height);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        copy.set(i, j, this.contents[i][j]);
      } // for
    } // for
    return copy;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      Matrix otherMatrix = (Matrix) other;
      // check if same height and width
      if (this.height != otherMatrix.height()) {
        return false;
      } else if (this.width != otherMatrix.width()) {
        return false;
      } else {
        // check if all contents are the same
        for (int i = 0; i < this.height; i++) {
          for (int j = 0; j < this.width; j++) {
            if ((this.contents[i][j] == null) || (otherMatrix.get(i, j) == null)) {
              if (this.contents[i][j] != otherMatrix.get(i, j)) {
                return false;
              } // if
            } else {
              if (!this.contents[i][j].equals(otherMatrix.get(i, j))) {
                return false;
              } // if
            } // if/else
          } // for
        } // for
        return true;
      } // if/else
    } else {
      // If it's not a matrix, it's not equal.
      return false;
    } // if/else
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
