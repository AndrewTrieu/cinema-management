package src;

// Seat class
public class Seat {
    // attributes
    private int row;
    private int column;
    private boolean available;

    // constructor
    public Seat(int row, int column, boolean available) {
        this.row = row;
        this.column = column;
        this.available = available;
    }

    // getters and setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
