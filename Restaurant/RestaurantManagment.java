package Restaurant;

public class RestaurantManagment {
    private Table[] tables;
    private int count;

    public RestaurantManagment(int[] max) {
        count = max.length;
        tables = new Table[count];
        for (int i = 0; i < count; i++) {
            tables[i] = new Table(i + 1, max[i]);
        }
        System.out.println("Создан ресторан с " + count + " столами");
    }

    public int getCount() {
        return count;
    }

    public boolean seatOne(int number) {
        if (number > count || number < 1) {
            System.out.println("Такого стола не существует");
            return false;
        }
        Table table = tables[number - 1];
        if (table.getIsOccupied() == false) {
            table.occupy();
            System.out.println("Человек посажен за " + number + " стол");
            return true;
        } else {
            System.out.println("Стол занят");
            return false;
        }
    }

    public boolean seatMore(int number, int k) {
        if (k < 1) {
            System.out.println("Размер компании должен быть положительным");
            return false;
        }
        if (number > count || number < 1) {
            System.out.println("Такого стола не существует");
            return false;
        }
        Table table = tables[number - 1];
        if (table.getIsOccupied() == false && table.getMax() >= k) {
            System.out.println("Компания из " + k + " человек посажена за " + number + " стол");
            table.occupy();
            return true;
        } else {
            System.out.println("Стол не подходит");
            return false;
        }
    }

    public boolean seatFirst(int k) {
        if (k < 1) {
            System.out.println("Размер компании должен быть положительным");
            return false;
        }

        for (int i = 0; i < count; i++) {
            Table table = tables[i];
            if (table.getMax() >= k && table.getIsOccupied() == false) {
                System.out.println("Компания из " + k + " человек посажена за " + table.getNumber() + " стол");
                table.occupy();
                return true;
            }
        }
        System.out.println("Такого стола не нашлось");
        return false;
    }

    public boolean seatOptimal(int k) {
        if (k < 1) {
            System.out.println("Размер компании должен быть положительным");
            return false;
        }
        int min = Integer.MAX_VALUE;
        Table best = null;
        for (int i = 0; i < count; i++) {
            Table table = tables[i];
            if (table.getMax() >= k && table.getIsOccupied() == false) {
                if (table.getMax() - k < min) {
                    min = table.getMax()-k;
                    best = table;
                }
            }
        }
        if (best == null) {
            System.out.println("Такого стола не нашлось");
            return false;
        } else {
            best.occupy();
            System.out.println("Компания из " + k + " человек посажена за " + best.getNumber() + " стол");
            return true;
        }
    }

    public boolean clearTable(int number) {
        if (number > count || number < 1) {
            System.out.println("Такого стола не существует");
            return false;
        }
        Table table = tables[number - 1];
        if (table.getIsOccupied() == true) {
            table.free();
            System.out.println(number + " стол освобожден");
            return true;
        } else {
            System.out.println(number + " стол уже свободен");
            return false;
        }
    }

    public boolean seatNear(int k) {
        if (k < 1) {
            System.out.println("Размер компании должен быть положительным");
            return false;
        }
        int seats = 0;
        int start = -1;
        for (int i = 0; i < count; i++) {
            if (tables[i].getIsOccupied() == true) {
                seats = 0;
                start = -1;
            } else {
                seats += tables[i].getMax();
                if (start == -1) {
                    start = i;
                }
            }
            if (seats >= k) {
                for (int u = start; u < i + 1; u++) {
                    tables[u].occupy();
                }
                System.out.println("Компания из " + k + " человек посажена за соседние столы с "+(start+1)+" по "+(i+1));
                return true;
            }
        }
        System.out.println("Не удалось найти подходящие соседние столы");
        return false;
    }

    public boolean seatNearOptimal(int k) {
        if (k < 1) {
            System.out.println("Размер компании должен быть положительным");
            return false;
        }
        int start = -1;
        int end = -1;
        int min = Integer.MAX_VALUE;

        for (int s = 0; s < count; s++) {
            int extra = 0;
            int max = 0;
            for (int e = s; e < count; e++) {
                if (tables[e].getIsOccupied()==true) {
                    break;
                }
                max += tables[e].getMax();
                if (max-k<min&&max>=k) {
                    min = max-k;
                    start = s;
                    end = e;
                }
            }
        }
        if (end != -1) {
            for (int u = start; u < end + 1; u++) {
                tables[u].occupy();
            }
            System.out.println("Компания из " + k + " человек посажена за соседние столы с "+(start+1)+" по "+(end+1));
            return true;
        } else {
            System.out.println("Не удалось найти подходящие соседние столы");
            return false;
        }
    }

}
