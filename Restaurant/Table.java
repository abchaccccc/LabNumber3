package Restaurant;

class Table {
    private int number;
    private int max;
    private boolean isOccupied;

    public Table (int number, int max) {
        this.number = number;
        if (max<1) {
            this.max=1;
        } else {
            this.max = max;
        }
        this.isOccupied = false;
    }

    public int getNumber() {
        return number;
    }

    public int getMax() {
        return max;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void free() {
        isOccupied = false;
    }

    public void occupy() {
        isOccupied = true;
    }
}

