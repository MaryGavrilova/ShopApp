package stores;

public class FoodProductStore extends ProductStore {

    private static FoodProductStore foodProductStore = null;

    private FoodProductStore() {
    }

    public static FoodProductStore getInstance() {
        if (foodProductStore == null) foodProductStore = new FoodProductStore();
        return foodProductStore;
    }

    // у класса FoodProductStore может быть свои особенности по реализации,
// например для хранения продуктов питания важно знать температуру на складе
    public int showTemperatureInStorageRoom() {
        int temperature = 10;
        System.out.println("Температура на складе " + temperature + " градусов.");
        return temperature;
    }

}
