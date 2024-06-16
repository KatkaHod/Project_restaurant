package orderTracking;

public class Table {
    private int tableNumber;


    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public String toString() {
        return "Table number: " + tableNumber;
    }
}
