import java.util.Arrays;

public class DefaultOrderManagementService implements OrderManagementService{
    private static final int DEFAULT_ORDER_CAPACITY = 10;

    private static DefaultOrderManagementService instance;

    private Order[] orders;
    private int numberOfOrders;

    private DefaultOrderManagementService(){
        orders = new DefaultOrder[DEFAULT_ORDER_CAPACITY];
        numberOfOrders = 0;
    }

    public static OrderManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderManagementService();
        }
        return instance;
    }

    @Override
    public void addOrder(Order order) {
        if (order == null) {
            return;
        }
        if (orders.length <= numberOfOrders){
            orders = Arrays.copyOf(orders, orders.length << 1);
        }
        orders[numberOfOrders++] = order;
        System.out.println("Hey order-> " +order);
    }

    @Override
    public Order[] getOrdersByUserId(int userId) {
        int sum = 0;
        for (int i=0; i<numberOfOrders; i++){
            if (orders[i].getCustomerId() == userId){
                sum++;
            }
        }

        Order[] userOrders = new Order[sum];
        int j=0;
        for (int i=0; i<numberOfOrders; i++){
            if (orders[i].getCustomerId() == userId){
                userOrders[j++] = orders[i];
            }
        }

        return userOrders;
    }

    @Override
    public Order[] getOrders() {
        return Arrays.copyOf(orders, numberOfOrders);
    }


    void clearServiceState() {
        instance = null;
    }

}
