import products.Product;

import java.util.*;

public class Basket {
    private static Basket basket = null;

    private Basket() {
    }

    public static Basket getInstance() {
        if (basket == null) basket = new Basket();
        return basket;
    }

    Store store = Store.getInstance();

    Map<Integer, Integer> productsBasket = new HashMap<>(); //корзина покупателя, покупатель выбирает товар, указывая артикул

    //получение информации о наличии товара в корзине по его артикулу
    public boolean isProductInProductsBasket(int productCode) {
        return productsBasket.containsKey(productCode);
    }

    //добавление продукта в корзину в определенном количестве
    public boolean addProductToBasket(int productCode, int quantity) {
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
    public boolean removeProductFromBasket(int productCode, int quantity) {
        if (productCode > 0 && quantity > 0) {
            if (isProductInProductsBasket(productCode)) {
                if (store.bookingProductOnStore(productCode, quantity).undoCommand()) {
                    if (quantity == productsBasket.get(productCode)) {
                        Iterator<Map.Entry<Integer, Integer>> iterator = productsBasket.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<Integer, Integer> entry = iterator.next();
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
    public List<String> getListOfProductsInBasket() {
        List<String> listOfProductsInBasket = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (productsBasket.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            int sum = 0;
            List<Product> shopProductsRangeList = store.getListOfProductsRange();
            Iterator<Map.Entry<Integer, Integer>> iterator = productsBasket.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                int number = entry.getKey();
                int quantity = entry.getValue();
                for (int i = 0; i < shopProductsRangeList.size(); i++) {
                    Product product = shopProductsRangeList.get(i);
                    if (product.getProductCode() == number) {
                        sb.append("-----------------------------");
                        sb.append("\nАртикул: " + product.getProductCode());
                        sb.append(", наименование: " + product.getName());
                        sb.append("\nЦена: " + product.getPrice() + " руб.");
                        sb.append(", количество: " + quantity);
                        sb.append("\nОбщая стоимость: " + quantity * product.getPrice() + " руб.");
                        sum += quantity * product.getPrice();
                        listOfProductsInBasket.add(sb.toString());
                        sb.delete(0, sb.length());
                    }
                }
            }
            sb.append("************************");
            sb.append("\nВСЕГО по всем товарам: " + sum + " руб. ");
            listOfProductsInBasket.add(sb.toString());
        }
        return listOfProductsInBasket;
    }
}
