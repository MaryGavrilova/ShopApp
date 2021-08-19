package baskets;

import stores.Store;

import java.util.List;

public interface Basket<T> {

    boolean isProductInProductsBasket(int productCode);

    boolean addProductToBasket(Store<T> store, int productCode, int quantity);

    boolean removeProductFromBasket(Store<T> store, int productCode, int quantity);

    List<String> getListOfProductsInBasket(Store<T> store);
}
