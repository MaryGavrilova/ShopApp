package stores;

import products.Product;

import java.util.*;

public class ProductStore implements Store<Product> {

    Map<Integer, Product> productsRange = new HashMap<>(); // ассортимент магазина (перечень всех товаров, которыми в принципе торгует магазин)
    Map<Integer, Integer> stocks = new HashMap<>(); // актуальное наличие товаров, количество каждого наименования

    //получение списка с перечнем всех товаров магазина
    @Override
    public List<Product> getListOfProductsRange() {
        return new ArrayList<Product>(productsRange.values());
    }

    //получение продукта из перечня всех товаров магазина по его артикулу
    @Override
    public Product getProductFromProductsRange(int productCode) {
        return productsRange.get(productCode);
    }

    //получение информации о внесении продукта в перечень всех товаров магазина по его артикулу
    @Override
    public boolean isProductInRange(int productCode) {
        return productsRange.containsKey(productCode);
    }

    //получение количества товара конкретного наименования на складе по его артикулу
    @Override
    public int getQuantityByProductCode(int productCode) {
        if (isProductInRange(productCode)) {
            return stocks.get(productCode);
        } else {
            System.out.println("Товара с таким артикулом " + productCode + " в данном магазине нет");
            return 0;
        }
    }

    //внесение  и отмена внесения продукта в перечень всех товаров магазина
    @Override
    public StoreCommand addProductToProductsRange(Product product) {
        return new StoreCommand() {
            @Override
            public boolean doCommand() {
                if (productsRange.containsKey(product.getProductCode())) {
                    System.out.println("Продукт c артикулом " + product.getProductCode() + " уже внесен в перечень товаров магазина");
                    return false;
                } else {
                    productsRange.put(product.getProductCode(), product);
                    stocks.put(product.getProductCode(), 0);
                    System.out.println("Продукт c артикулом " + product.getProductCode() + " внесен в перечень товаров магазина");
                    return true;
                }
            }

            @Override
            public boolean undoCommand() {
                if (!productsRange.containsKey(product.getProductCode())) {
                    System.out.println("Продукт не может быть исключен из перечня товаров магазина, поскольку его там нет");
                    return false;
                } else {
                    for (Map.Entry<Integer, Integer> entry : stocks.entrySet()) {
                        int currentCode = entry.getKey();
                        if (currentCode == product.getProductCode()) {
                            stocks.remove(currentCode);
                            System.out.println("Продукт исключен из списка по наличию товара в магазине ");
                        }
                    }
                    for (Map.Entry<Integer, Product> entry : productsRange.entrySet()) {
                        Product currentProduct = entry.getValue();
                        if (currentProduct.equals(product)) {
                            productsRange.remove(entry.getKey());
                            System.out.println("Продукт исключен из перечня всех товаров магазина");
                        }
                    }
                    return true;
                }
            }
        };
    }

    //поставка товара в определенном количестве на склад
    //quantity - количество, которое мы добавляем к текущему количеству на складе
    // если товара до этого не было в перечне всех товаров, то добавляем его
    @Override
    public void deliverProductToStore(Product product, int quantity) {
        if (quantity > 0) {
            if (productsRange.containsKey(product.getProductCode())) {
                int currentQuantity = stocks.get(product.getProductCode());
                stocks.put(product.getProductCode(), quantity + currentQuantity);
            } else {
                productsRange.put(product.getProductCode(), product);
                stocks.put(product.getProductCode(), quantity);
            }
            System.out.println("Товар с артикулом " + product.getProductCode() + " " + product.getName() +
                    " добавлен на склад в количестве " + quantity + " шт.");
        } else {
            System.out.println("Количество товара нет может быть меньше, либо равно нулю");
        }
    }

    //бронирование и отмена бронирования товара на складе при внесении его в корзину
    @Override
    public StoreCommand bookingProductOnStore(int productCode, int quantity) {
        return new StoreCommand() {
            @Override
            public boolean doCommand() {
                if (stocks.containsKey(productCode)) {
                    if (quantity > getQuantityByProductCode(productCode)) {
                        // покупаемое количество товара превышает доступное количество на складе
                        System.out.println("Выбранное к покупке количество товара c артикулом " + productCode
                                + " превышает запасы на складе.\nНа текущий момент на складе в наличии: "
                                + getQuantityByProductCode(productCode) + " шт. товара.");
                        return false;
                    } else if (quantity == getQuantityByProductCode(productCode)) {
                        // покупаемое количество товара совпадает со всем запасом на складе
                        stocks.put(productCode, 0);
                        System.out.println("Товар с артикулом " + productCode + " забронирован полностью (весь остаток на складе).");
                        return true;
                    } else {
                        // покупаемое количество товара меньше доступного количества на складе
                        int currentQuantity = getQuantityByProductCode(productCode);
                        stocks.put(productCode, currentQuantity - quantity);
                        System.out.println("Товар с артикулом " + productCode + " забронирован в количестве "
                                + quantity + " шт.");
                        return true;
                    }
                } else {
                    System.out.println("Товара с таким артикулом " + productCode + " в данном магазине нет");
                    return false;
                }
            }

            @Override
            public boolean undoCommand() {
                if (stocks.containsKey(productCode)) {
                    int currentQuantity = getQuantityByProductCode(productCode);
                    stocks.put(productCode, quantity + currentQuantity);
                    System.out.println("Бронь на складе на товар с артикулом " + productCode + " в количестве "
                            + quantity + " шт. отменена");
                    return true;
                } else {
                    System.out.println("Товара с таким артикулом " + productCode + " в данном магазине нет");
                    return false;
                }
            }
        };
    }

    //получение листа с доступными для покупки товарами
    @Override
    public List<Product> getListOfAvailableProducts() {
        List<Product> list = new ArrayList<>();
        Iterator<Map.Entry<Integer, Product>> iterator = productsRange.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Product> entry = iterator.next();
            Product product = entry.getValue();
            if (!(stocks.get(product.getProductCode()) == 0)) {
                list.add(product);
            }
        }
        return list;
    }
}