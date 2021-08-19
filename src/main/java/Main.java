import baskets.Basket;
import baskets.ProductBasket;
import filters.ProductFilter;
import products.FoodProductCategory;
import products.Product;
import products.ProductBuilder;
import stores.FoodProductStore;
import stores.Store;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Basket<Product> productBasket = new ProductBasket(); //для помещения в корзину товаров, имеющих характеристики Product
        ProductFilter productFilter = new ProductFilter(); // для фильтрации товаров, имеющих характеристики Product
        Store<Product> foodProductStore = FoodProductStore.getInstance();
        // для хранения продуктов питания (категория FoodProductCategory), имеющих характеристики Product

        //Структура программы позволяет создать много разных складов для товаров типа Product (путем наследования),
        // логически отличающихся, например категорией товара. При этом склады могут иметь свои особенности реализации,
        // иметь дополнительные методы, если необходимо.
        // В тоже время корзина productBasket сможет работать с каждым складом (наследованным от ProductStore)

        Product<FoodProductCategory> foodProduct1 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(1)
                .setName("Печенье овсяное")
                .setCategory(FoodProductCategory.PASTRY)
                .setPrice(40)
                .setProducer("Belvita")
                .setRating(5)
                .build();
        Product<FoodProductCategory> foodProduct2 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(2)
                .setName("Печенье с кусочками шоколада")
                .setCategory(FoodProductCategory.PASTRY)
                .setPrice(60)
                .setProducer("Milka")
                .setRating(10)
                .build();
        Product<FoodProductCategory> foodProduct3 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(3)
                .setName("Ржаной хлеб")
                .setCategory(FoodProductCategory.BAKERY)
                .setPrice(10)
                .setProducer("Сормовский хлебозавод")
                .setRating(30)
                .build();
        Product<FoodProductCategory> foodProduct4 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(4)
                .setName("Пшеничный хлеб")
                .setCategory(FoodProductCategory.BAKERY)
                .setPrice(8)
                .setProducer("Сормовский хлебозавод")
                .setRating(25)
                .build();
        Product<FoodProductCategory> foodProduct5 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(5)
                .setName("Молоко 6%")
                .setCategory(FoodProductCategory.DAIRY)
                .setPrice(50)
                .setProducer("Княгинино")
                .setRating(20)
                .build();
        Product<FoodProductCategory> foodProduct6 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(6)
                .setName("Апельсиновый сок")
                .setCategory(FoodProductCategory.DRINKS)
                .setPrice(90)
                .setProducer("RICH")
                .setRating(15)
                .build();
        Product<FoodProductCategory> foodProduct7 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(7)
                .setName("Докторская колбаса")
                .setCategory(FoodProductCategory.MEAT)
                .setPrice(200)
                .setProducer("Мираторг")
                .setRating(70)
                .build();
        Product<FoodProductCategory> foodProduct8 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(8)
                .setName("Российский сыр")
                .setCategory(FoodProductCategory.DAIRY)
                .setPrice(150)
                .setProducer("Valio")
                .build();
        Product<FoodProductCategory> foodProduct9 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(9)
                .setName("Сметана 20%")
                .setCategory(FoodProductCategory.DAIRY)
                .setPrice(30)
                .setProducer("Княгинино")
                .setRating(45)
                .build();
        Product<FoodProductCategory> foodProduct10 = new ProductBuilder<FoodProductCategory>()
                .setProductCode(10)
                .setName("Масло сливочное")
                .setCategory(FoodProductCategory.DAIRY)
                .setPrice(100)
                .setProducer("Княгинино")
                .setRating(60)
                .build();

        foodProductStore.addProductToProductsRange(foodProduct1).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct2).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct3).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct4).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct5).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct6).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct7).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct8).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct9).doCommand();
        foodProductStore.addProductToProductsRange(foodProduct10).doCommand();

        foodProductStore.deliverProductToStore(foodProduct1, 5);
        foodProductStore.deliverProductToStore(foodProduct2, 8);
        foodProductStore.deliverProductToStore(foodProduct3, 20);
        foodProductStore.deliverProductToStore(foodProduct4, 30);
        foodProductStore.deliverProductToStore(foodProduct5, 3);
        foodProductStore.deliverProductToStore(foodProduct6, 10);
        foodProductStore.deliverProductToStore(foodProduct7, 15);
        foodProductStore.deliverProductToStore(foodProduct8, 10);
        foodProductStore.deliverProductToStore(foodProduct9, 7);
        foodProductStore.deliverProductToStore(foodProduct10, 25);

        System.out.println("***********************************" +
                "\nДобро пожаловать в онлайн-магазин!");
        while (true) {
            try {
                System.out.println("""
                                                
                        ОСНОВНОЕ МЕНЮ:
                        1. Посмотреть список доступных для покупки товаров
                        2. Отфильтровать список товаров (по ключевому слову, категории, ценовому диапазону, производителю, рейтингу)
                        3. Получить информацию о количестве товара в наличии
                        4. Положить товар в корзину
                        5. Убрать товар из корзины
                        6. Посмотреть список продуктовой корзины
                        7. Оформить заказ
                        8. Оценить товар (нравится / не нравится)
                        0. Выход""");
                System.out.println("\nВыберите действие из основного меню, указав номер:");
                String input = scanner.nextLine();
                if (input.equals("0")) {
                    System.out.println("До свидания! Будем ждать вас снова!");
                    break;
                }
                switch (input) {
                    case "1" -> {
                        List<Product> listOfAvailableProducts = foodProductStore.getListOfAvailableProducts();
                        if (listOfAvailableProducts.isEmpty()) {
                            System.out.println("Список доступных для покупки товаров пуст");
                        } else {
                            System.out.println("Список доступных для покупки товаров:");
                            for (Product listOfAvailableProduct : listOfAvailableProducts) {
                                System.out.println(listOfAvailableProduct);
                            }
                        }
                    }
                    case "2" -> {
                        List<Product> listOfProducts = foodProductStore.getListOfAvailableProducts();
                        if (listOfProducts.isEmpty()) {
                            System.out.println("Список доступных для покупки товаров пуст");
                        } else {
                            System.out.println("""
                                    Укажите номер параметра, по которому хотите отфильтровать:
                                    1. по ключевому слову в наименовании товара
                                    2. по категории товара
                                    3. по ценовому диапазону
                                    4. по производителю товара
                                    5. по рейтингу товара""");
                            String inputFilter = scanner.nextLine();
                            switch (inputFilter) {
                                case "1" -> {
                                    System.out.println("Укажите ключевое слово:");
                                    String inputKeyWord = scanner.nextLine();
                                    List<Product> listFilteredByKeyWord = productFilter.filterByKeyWord(listOfProducts, inputKeyWord);
                                    if (!listFilteredByKeyWord.isEmpty()) {
                                        System.out.println("Список доступных для покупки товаров, " +
                                                "отфильтрованных по ключевому слову: " + inputKeyWord);
                                        for (Product product : listFilteredByKeyWord) {
                                            System.out.println(product);
                                        }
                                    } else {
                                        System.out.println("Список доступных для покупки товаров, " +
                                                "отфильтрованных по ключевому слову: " + inputKeyWord + " пуст");
                                    }
                                }
                                case "2" -> {
                                    System.out.println("""
                                            Укажите номер категории товара, по которой хотите отфильтровать список:
                                            1. Кондитерские изделия
                                            2. Молочные продукты и сыры
                                            3. Мясные и колбасные изделия
                                            4. Напитки
                                            5. Хлебобулочные изделия""");
                                    int inputNumberOfСategory = Integer.parseInt(scanner.nextLine());
                                    FoodProductCategory[] categories = FoodProductCategory.values();
                                    if (inputNumberOfСategory > 0 && inputNumberOfСategory <= categories.length) {
                                        List<Product> listFilteredByCategory = productFilter.filterByCategory(listOfProducts, categories[inputNumberOfСategory - 1]);
                                        if (!listFilteredByCategory.isEmpty()) {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по категории: " + categories[inputNumberOfСategory - 1]);
                                            for (Product product : listFilteredByCategory) {
                                                System.out.println(product);
                                            }
                                        } else {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по категории: " + categories[inputNumberOfСategory - 1] + " пуст");
                                        }
                                    } else {
                                        System.out.println("Категории под таким номером нет");
                                    }
                                }
                                case "3" -> {
                                    System.out.println("Укажите значения ценового диапазона:");
                                    System.out.println("Цена от:");
                                    int inputMinPrice = Integer.parseInt(scanner.nextLine());
                                    System.out.println("Цена до:");
                                    int inputMaxPrice = Integer.parseInt(scanner.nextLine());
                                    if (inputMinPrice > 0 && inputMaxPrice > 0) {
                                        List<Product> listFilteredByPriceRange = productFilter.filterByPriceRange(listOfProducts, inputMinPrice, inputMaxPrice);
                                        if (!listFilteredByPriceRange.isEmpty()) {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по ценовому диапазону от " + inputMinPrice + " руб. до " + inputMaxPrice + " руб.:");
                                            for (Product product : listFilteredByPriceRange) {
                                                System.out.println(product);
                                            }
                                        } else {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по ценовому диапазону от " + inputMinPrice + " руб. до " + inputMaxPrice + " руб. пуст");
                                        }
                                    } else
                                        System.out.println("Цена не может быть меньше, либо равна 0");
                                }
                                case "4" -> {
                                    System.out.println("Укажите производителя:");
                                    String inputProducer = scanner.nextLine();
                                    List<Product> listFilteredByProducer = productFilter.filterByProducer(listOfProducts, inputProducer);
                                    if (!listFilteredByProducer.isEmpty()) {
                                        System.out.println("Список доступных для покупки товаров, " +
                                                "отфильтрованных по производителю: " + inputProducer);
                                        for (Product product : listFilteredByProducer) {
                                            System.out.println(product);
                                        }
                                    } else {
                                        System.out.println("Список доступных для покупки товаров, " +
                                                "отфильтрованных по производителю: " + inputProducer + " пуст");
                                    }
                                }
                                case "5" -> {
                                    System.out.println("Укажите  значение рейтинга, относительно которого фильтровать:");
                                    int inputValue = Integer.parseInt(scanner.nextLine());
                                    System.out.println("""
                                            Укажите номер варианта фильтра:
                                            1. выше значения
                                            2. ниже значения""");
                                    String inputVariant = scanner.nextLine();
                                    if (inputVariant.equals("1")) {
                                        List<Product> listFilteredByRatingHigherThanValue = productFilter.filterByRatingHigherThanValue(listOfProducts, inputValue);
                                        if (!listFilteredByRatingHigherThanValue.isEmpty()) {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по рейтингу выше, чем  " + inputValue);
                                            for (Product product : listFilteredByRatingHigherThanValue) {
                                                System.out.println(product);
                                            }
                                        } else {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по рейтингу выше, чем  " + inputValue + " пуст");
                                        }
                                    } else if (inputVariant.equals("2")) {
                                        List<Product> listFilteredByRatingLowerThanValue = productFilter.filterByRatingLowerThanValue(listOfProducts, inputValue);
                                        if (!listFilteredByRatingLowerThanValue.isEmpty()) {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по рейтингу ниже, чем  " + inputValue);
                                            for (Product product : listFilteredByRatingLowerThanValue) {
                                                System.out.println(product);
                                            }
                                        } else {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по рейтингу ниже, чем  " + inputValue + " пуст");
                                        }
                                    } else {
                                        System.out.println("Варианта с таким номером нет");
                                    }
                                }
                                default -> System.out.println("Параметра с таким номером нет");
                            }
                        }
                    }
                    case "3" -> {
                        System.out.println("Укажите артикул товара, количество которого хотите узнать:");
                        int productCodeToGetQuantity = Integer.parseInt(scanner.nextLine());
                        if (productCodeToGetQuantity > 0) {
                            System.out.println("Доступное к покупке количество товара: "
                                    + foodProductStore.getQuantityByProductCode(productCodeToGetQuantity) + " шт.");
                        } else {
                            System.out.println("Артикул товара не может быть меньше либо равен нулю.");
                        }
                    }
                    case "4" -> {
                        System.out.println("Укажите артикул товара, который хотите добавить в корзину:");
                        int productCodeToPut = Integer.parseInt(scanner.nextLine());
                        System.out.println("Укажите количество товара:");
                        int quantityToPut = Integer.parseInt(scanner.nextLine());
                        if (productBasket.addProductToBasket(foodProductStore, productCodeToPut, quantityToPut)) {
                            System.out.println("Товар добавлен в корзину");
                        } else {
                            System.out.println("Товар не добавлен в корзину");
                        }
                    }
                    case "5" -> {
                        System.out.println("Укажите артикул товара, который хотите убрать из корзины:");
                        int productCodeToRemove = Integer.parseInt(scanner.nextLine());
                        System.out.println("Укажите количество товара:");
                        int quantityToRemove = Integer.parseInt(scanner.nextLine());
                        if (productBasket.removeProductFromBasket(foodProductStore, productCodeToRemove, quantityToRemove)) {
                            System.out.println("Товар убран из корзины");
                        } else {
                            System.out.println("Товар не убран из корзины");
                        }
                    }
                    case "6" -> {
                        List<String> basketList = productBasket.getListOfProductsInBasket(foodProductStore);
                        if (basketList.isEmpty()) {
                            System.out.println("Корзина пуста");
                        } else {
                            System.out.println("Корзина:");
                            for (String s : basketList) {
                                System.out.println(s);
                            }
                        }
                    }
                    case "7" -> {
                        System.out.println("Для оформления и последующей оплаты заказа укажите адрес элеткронной почты:");
                        String inputMail = scanner.nextLine();
                        System.out.println("Укажите имя и фамилию:");
                        String inputName = scanner.nextLine();
                        System.out.println("Укажите контактный телефон:");
                        String inputPhoneNumber = scanner.nextLine();
                        System.out.println("Укажите адрес доставки:");
                        String inputAddress = scanner.nextLine();
                        System.out.println("""
                                Спасибо за ваш заказ! Будем ждать вас снова!
                                В ближайшее время с вами свяжется наш менеджер для подтверждения заказа,
                                а на указанный адрес электронной почты придет письмо со счетом для оплаты заказа.""");
                    }
                    case "8" -> {
                        System.out.println("Укажите артикул товара, который хотите оценить:");
                        int productCodeToRate = Integer.parseInt(scanner.nextLine());
                        if (foodProductStore.isProductInRange(productCodeToRate)) {
                            System.out.println("""
                                    Выберите вариант, указав номер:
                                    1. Товар нравится
                                    2. Товар не нравится""");
                            String inputRating = scanner.nextLine();
                            if (inputRating.equals("1")) {
                                Product product = foodProductStore.getProductFromProductsRange(productCodeToRate);
                                product.likeProduct();
                                System.out.println("Спасибо за вашу оценку! Текущий рейтинг товара: " + product.getRating());
                            } else if (inputRating.equals("2")) {
                                Product product = foodProductStore.getProductFromProductsRange(productCodeToRate);
                                product.dislikeProduct();
                                System.out.println("Спасибо за вашу оценку! Текущий рейтинг товара: " + product.getRating());
                            } else {
                                System.out.println("Варианта ответа с таким номером нет");
                            }
                        } else {
                            System.out.println("Товара с таким артикулом нет в перечне товаров магазина");
                        }
                    }
                    default -> System.out.println("Действия с таким номером нет");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Вы ввели не цифру");
            }
        }
    }
}
