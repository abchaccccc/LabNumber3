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
