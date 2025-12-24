## Cодержание

## Отчет по лабораторной работе № 3

#### № группы: `ПМ-2502`

#### Выполнил: `Соколов Евгений Станиславович`

#### Вариант: `19`

### Cодержание:
- Общее
- [Постановка задачи](#1-постановка-задачи)
- Класс Table
- [Постановка задачи](#1-постановка-задачи)
- [Алгоритм](#2-алгоритм)
- [Программа](#3-программа)
- Класс RestaurantManagment
- [Постановка задачи](#1-постановка-задачи)
- [Алгоритм](#2-алгоритм)
- [Программа](#3-программа)
- Класс Test
- [Анализ правильности решения](#4-анализ-правильности-решения)
### Общее задание
### 1) Постановка задачи
> Разработать программу для управления посадкой гостей за столы в ресторане. Реализовать функции размещения одиночных гостей и компаний с учётом фиксированной
вместимости столов, освобождения столов и оптимизации распределения мест.

        
### Класс Table
### 1) Постановка задачи

> Класс, который будет хранить в себе информацию о столах, а так же будет задавать операции над ними.
### 2) Алгоритм

#### Алгоритм выполнения программы:

1. **Ввод данных:**  
   Класс принимает номер стола, а так же его вместимость.

2. **Хранение данных**  
   Класс хранит в себе номер стола, его вместимость, а так же является ли он занятым.

3. **Операции:**  
   В классе прописаны геттеры для всех хранящихся значений, а так же возможность освободить или занять стол.

### 3) Программа
```

```java

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




```


### Класс RestaurantManagment
### 1) Постановка задачи

> Класс, благодаря которому можно управлять рестораном. Имеет функции создания ресторана, посадки человека за указанный стол, посадка компании за указанный стол, посадка компании за первый подходящий стол, посадка компании за самый выгодный стол, освобождение стола, посадка компании за несколько соседних столов, посадка компании за самую выгодную последовательность столов.

### 2) Алгоритм

#### Алгоритм выполнения программы:

1. **Ввод данных:**  
   Класс принимает на вход список вместимостей столов.

2. **Хранение данных**  
   Класс хранит в себе количество столов и список их вместимостей.
   
3. **Функции**  
   Класс имеет геттер для количества столов. Класс так же имеет функцию для посадки одного человека за выбранный стол, которая проверяет, свободен ли стол, а затем либо занимает его, либо выдает, что стол занят. Класс имеет функцию для посадки компании за выбранный стол, которая проверяет, свободен ли стол и больше или равна ли его вместимость размеру компании, а затем либо занимает его, либо выдает, что стол не подходит. Класс имеет функцию для посадки компании за первый подходящий стол, которая через цикл проверяет каждый стол по очереди и выбирает первый, который имеет достаточную вместимость, а затем занимает его. Если такого стола не нашлось, то функция пишет об этом. Класс имеет функцию для посадки компании за самый выгодный стол, которая через цикл сравнивает столы по разности их вместимости и размера компании, а затем занимает незанятый стол с наименьшей разностью. Если такого стола не нашлось, то функция пишет об этом. Класс имеет функцию освобождения стола, которая проверяет, свободен ли стол, и если да, то пишет о том, что стол уже свободен, а если нет, то освобождает его. Класс имеет функцию для посадки компании за соседние столы, которая через цикл проверяет последовательности столов на их вместимость и занимает первые столы, которые могут вместить компанию. Если такой последовательности не нашлось, то функция пишет об этои. Класс имеет функцию посадки компании за самую выгодную последовательность столов, которая через 2 цикла сравнивает последовательности столов по разности их суммарной вместимости и размера компании, а затем занимает ту последовательность свободных столов, которая имеет наименьшую разность. Если такой последовательности нет, то функция пишет об этом.

### 3) Программа

```



```java


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




```

### Класс Test

### Анализ правильности решения

Для проверки корректной работы программы существует класс Test, который использует все функции программы на примере определенного ресторана. Все они работают корректно.

### Программа

```java
   
import Restaurant.RestaurantManagment;

public class Test {
    public static void main(String[] args) {
        int[] max={2, 4, 2, 6, 4, 8, 10, 2, 4, 6, 12, 2};

        RestaurantManagment managment = new RestaurantManagment(max);

        managment.seatOne(2);

        managment.seatMore(1,3);

        managment.seatFirst(4);

        managment.seatOptimal(2);

        managment.clearTable(3);

        managment.seatNear(8);

        managment.seatNearOptimal(7);
    }
}

```

        - **Output**:
        ```
        Создан ресторан с 12 столами
        Человек посажен за 2 стол
        Стол не подходит
        Компания из 4 человек посажена за 4 стол
        Компания из 2 человек посажена за 1 стол
        3 стол уже свободен
        Компания из 8 человек посажена за соседние столы с 5 по 6
        Компания из 7 человек посажена за соседние столы с 7 по 7
        ```
