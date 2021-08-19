package baskets;

import products.Product;
import stores.Store;

import java.util.*;

public class ProductBasket implements Basket<Product> {

    Map<Integer, Integer> productsBasket = new HashMap<>(); //корзина покупателя, покупатель выбирает товар, указывая артикул

    //получение информации о наличии товара в корзине по его артикулу
    @Override
    public boolean isProductInProductsBasket(int productCode) {
        return productsBasket.containsKey(productCode);
    }

    //добавление продукта в корзину в определенном количестве
    @Override
    public boolean addProductToBasket(Store<Product> store, int productCode, int quantity) {
        if (productCode > 0 && quantity > 0) {
            if (!isProductInProductsBasket(productCode)) {
                if (store.bookingProductOnStore(productCode, quantity).doCommand()) {
                    productsBasket.put(productCode, quantity);
                    System.out.println("Товар с артикулом " + productCode + " в количестве " + quantity + " шт. добавлен в корзину");
                    return true;
                } else {
                    System.out.println("Товар с артикулом " + productCode + " в количестве " + quantity + " шт. не может быть добавлен в корзину");
                    return false;
                }
            } else {
                if (store.bookingProductOnStore(productCode, quantity).doCommand()) {
                    productsBasket.put(productCode, quantity + productsBasket.get(productCode));
                    System.out.println("Товар с артикулом " + productCode + " в количестве " + quantity + " шт. добавлен в корзину. " +
                            "Всего количество данного товара: " + productsBasket.get(productCode));
                    return true;
                } else {
                    System.out.println("Товар с артикулом " + productCode + " в количестве " + quantity + " шт. не может быть добавлен в корзину");
                    return false;
                }
            }
        } else {
            System.out.println("Артикул или количество товара не может быть меньше, либо равно нулю");
            return false;
        }
    }

    //удаление продукта из корзины в определенном количестве
    @Override
    public boolean removeProductFromBasket(Store<Product> store, int productCode, int quantity) {
        if (productCode > 0 && quantity > 0) {
            if (isProductInProductsBasket(productCode)) {
                if (store.bookingProductOnStore(productCode, quantity).undoCommand()) {
                    if (quantity == productsBasket.get(productCode)) {
                        for (Map.Entry<Integer, Integer> entry : productsBasket.entrySet()) {
                            int currentNumber = entry.getKey();
                            if (currentNumber == productCode) {
                                productsBasket.remove(currentNumber);
                                System.out.println("Товар с артикулом " + productCode + " в количестве "
                                        + quantity + " шт. исключен из корзины");
                            }
                        }
                        return true;
                    } else if (quantity < productsBasket.get(productCode)) {
                        productsBasket.put(productCode, productsBasket.get(productCode) - quantity);
                        System.out.println("Товар с артикулом " + productCode + " в количестве "
                                + quantity + " шт. исключен из корзины");
                        return true;
                    } else {
                        System.out.println("Количество товара, которое необходимо убрать из корзины, превышает количество положенного в корзину товара");
                        return false;
                    }
                } else {
                    System.out.println("Товар с артикулом " + productCode + " в количестве "
                            + quantity + " шт. не может быть исключен из корзины");
                    return false;
                }
            } else {
                System.out.println("Товар с артикулом " + productCode + " в количестве "
                        + quantity + " шт. не может быть исключен из корзины, поскольку он не добавлен в корзину");
                return false;
            }
        } else {
            System.out.println("Артикул или количество товара не может быть меньше, либо равно нулю");
            return false;
        }
    }


    //получение списка продуктовой корзины
    @Override
    public List<String> getListOfProductsInBasket(Store<Product> store) {
        List<String> listOfProductsInBasket = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (productsBasket.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            int sum = 0;
            List<Product> shopProductsRangeList = store.getListOfProductsRange();
            for (Map.Entry<Integer, Integer> entry : productsBasket.entrySet()) {
                int number = entry.getKey();
                int quantity = entry.getValue();
                for (Product product : shopProductsRangeList) {
                    if (product.getProductCode() == number) {
                        sb.append("-----------------------------")
                                .append("\nАртикул: ")
                                .append(product.getProductCode())
                                .append(", наименование: ")
                                .append(product.getName())
                                .append("\nЦена: ")
                                .append(product.getPrice())
                                .append(" руб.").append(", количество: ")
                                .append(quantity).append("\nОбщая стоимость: ")
                                .append(quantity * product.getPrice())
                                .append(" руб.");
                        sum += quantity * product.getPrice();
                        listOfProductsInBasket.add(sb.toString());
                        sb.delete(0, sb.length());
                    }
                }
            }
            sb.append("************************")
                    .append("\nВСЕГО по всем товарам: ")
                    .append(sum).append(" руб. ");
            listOfProductsInBasket.add(sb.toString());
        }
        return listOfProductsInBasket;
    }
}
