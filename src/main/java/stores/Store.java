package stores;

import java.util.List;


public interface Store<T> {

    StoreCommand addProductToProductsRange(T product);

    void deliverProductToStore(T product, int quantity);

    StoreCommand bookingProductOnStore(int productCode, int quantity);

    List<T> getListOfAvailableProducts();

    List<T> getListOfProductsRange();

    int getQuantityByProductCode(int productCode);

    T getProductFromProductsRange(int productCode);

    boolean isProductInRange(int productCode);
}
