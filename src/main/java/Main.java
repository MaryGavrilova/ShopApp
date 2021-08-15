import filters.ProductFilter;
import products.Product;
import products.ProductBuilder;
import products.ProductCategory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Basket basket = Basket.getInstance();
        Store store = Store.getInstance();
        ProductFilter productFilter = new ProductFilter();

        Product product1 = new ProductBuilder()
                .setProductCode(1)
                .setName("Печенье овсяное")
                .setCategory(ProductCategory.PASTRY)
                .setPrice(40)
                .setProducer("Belvita")
                .setRating(5)
                .build();
        Product product2 = new ProductBuilder()
                .setProductCode(2)
                .setName("Печенье с кусочками шоколада")
                .setCategory(ProductCategory.PASTRY)
                .setPrice(60)
                .setProducer("Milka")
                .setRating(10)
                .build();
        Product product3 = new ProductBuilder()
                .setProductCode(3)
                .setName("Ржаной хлеб")
                .setCategory(ProductCategory.BAKERY)
                .setPrice(10)
                .setProducer("Сормовский хлебозавод")
                .setRating(30)
                .build();
        Product product4 = new ProductBuilder()
                .setProductCode(4)
                .setName("Пшеничный хлеб")
                .setCategory(ProductCategory.BAKERY)
                .setPrice(8)
                .setProducer("Сормовский хлебозавод")
                .setRating(25)
                .build();
        Product product5 = new ProductBuilder()
                .setProductCode(5)
                .setName("Молоко 6%")
                .setCategory(ProductCategory.DAIRY)
                .setPrice(50)
                .setProducer("Княгинино")
                .setRating(20)
                .build();
        Product product6 = new ProductBuilder()
                .setProductCode(6)
                .setName("Апельсиновый сок")
                .setCategory(ProductCategory.DRINKS)
                .setPrice(90)
                .setProducer("RICH")
                .setRating(15)
                .build();
        Product product7 = new ProductBuilder()
                .setProductCode(7)
                .setName("Докторская колбаса")
                .setCategory(ProductCategory.MEAT)
                .setPrice(200)
                .setProducer("Мираторг")
                .setRating(70)
                .build();
        Product product8 = new ProductBuilder()
                .setProductCode(8)
                .setName("Российский сыр")
                .setCategory(ProductCategory.DAIRY)
                .setPrice(150)
                .setProducer("Valio")
                .build();
        Product product9 = new ProductBuilder()
                .setProductCode(9)
                .setName("Сметана 20%")
                .setCategory(ProductCategory.DAIRY)
                .setPrice(30)
                .setProducer("Княгинино")
                .setRating(45)
                .build();
        Product product10 = new ProductBuilder()
                .setProductCode(10)
                .setName("Масло сливочное")
                .setCategory(ProductCategory.DAIRY)
                .setPrice(100)
                .setProducer("Княгинино")
                .setRating(60)
                .build();

        store.addProductToProductsRange(product1).doCommand();
        store.addProductToProductsRange(product2).doCommand();
        store.addProductToProductsRange(product3).doCommand();
        store.addProductToProductsRange(product4).doCommand();
        store.addProductToProductsRange(product5).doCommand();
        store.addProductToProductsRange(product6).doCommand();
        store.addProductToProductsRange(product7).doCommand();
        store.addProductToProductsRange(product8).doCommand();
        store.addProductToProductsRange(product9).doCommand();
        store.addProductToProductsRange(product10).doCommand();

        store.deliverProductToStore(product1, 5);
        store.deliverProductToStore(product2, 8);
        store.deliverProductToStore(product3, 20);
        store.deliverProductToStore(product4, 30);
        store.deliverProductToStore(product5, 3);
        store.deliverProductToStore(product6, 10);
        store.deliverProductToStore(product7, 15);
        store.deliverProductToStore(product8, 10);
        store.deliverProductToStore(product9, 7);
        store.deliverProductToStore(product10, 25);

        System.out.println("***********************************\nДобро пожаловать в онлайн-магазин!");
        while (true) {
            try {
                System.out.println("\nОСНОВНОЕ МЕНЮ:" +
                        "\n1. Посмотреть список доступных для покупки товаров" +
                        "\n2. Отфильтровать список товаров (по ключевому слову, категории, ценовому диапазону, производителю, рейтингу)" +
                        "\n3. Получить информацию о количестве товара в наличии" +
                        "\n4. Положить товар в корзину" +
                        "\n5. Убрать товар из корзины" +
                        "\n6. Посмотреть список продуктовой корзины" +
                        "\n7. Оформить заказ" +
                        "\n8. Оценить товар (нравится / не нравится)" +
                        "\n0. Выход");
                System.out.println("\nВыберите действие из основного меню, указав номер:");
                String input = scanner.nextLine();
                if (input.equals("0")) {
                    System.out.println("До свидания! Будем ждать вас снова!");
                    break;
                }
                switch (input) {
                    case "1":
                        List<Product> listOfAvailableProducts = store.getListOfAvailableProducts();
                        if (listOfAvailableProducts.isEmpty()) {
                            System.out.println("Список доступных для покупки товаров пуст");
                        } else {
                            System.out.println("Список доступных для покупки товаров:");
                            for (int i = 0; i < listOfAvailableProducts.size(); i++) {
                                System.out.println(listOfAvailableProducts.get(i));
                            }
                        }
                        break;
                    case "2":
                        List<Product> listOfProducts = store.getListOfAvailableProducts();
                        if (listOfProducts.isEmpty()) {
                            System.out.println("Список доступных для покупки товаров пуст");
                        } else {
                            System.out.println("Укажите номер параметра, по которому хотите отфильтровать:" +
                                    "\n1. по ключевому слову в наименовании товара" +
                                    "\n2. по категории товара" +
                                    "\n3. по ценовому диапазону" +
                                    "\n4. по производителю товара" +
                                    "\n5. по рейтингу товара");
                            String inputFilter = scanner.nextLine();
                            switch (inputFilter) {
                                case "1":
                                    System.out.println("Укажите ключевое слово:");
                                    String inputKeyWord = scanner.nextLine();
                                    List<Product> listFilteredByKeyWord = productFilter.filterByKeyWord(listOfProducts, inputKeyWord);
                                    if (!listFilteredByKeyWord.isEmpty()) {
                                        System.out.println("Список доступных для покупки товаров, " +
                                                "отфильтрованных по ключевому слову: " + inputKeyWord);
                                        for (int i = 0; i < listFilteredByKeyWord.size(); i++) {
                                            System.out.println(listFilteredByKeyWord.get(i));
                                        }
                                    } else {
                                        System.out.println("Список доступных для покупки товаров, " +
                                                "отфильтрованных по ключевому слову: " + inputKeyWord + " пуст");
                                    }
                                    break;
                                case "2":
                                    System.out.println("Укажите номер категории товара, по которой хотите отфильтровать список:" +
                                            "\n1. Кондитерские изделия" +
                                            "\n2. Молочные продукты и сыры" +
                                            "\n3. Мясные и колбасные изделия" +
                                            "\n4. Напитки" +
                                            "\n5. Хлебобулочные изделия");
                                    int inputNumberOfСategory = Integer.parseInt(scanner.nextLine());
                                    ProductCategory[] categories = ProductCategory.values();
                                    if (inputNumberOfСategory > 0 && inputNumberOfСategory <= categories.length) {
                                        List<Product> listFilteredByCategory = productFilter.filterByCategory(listOfProducts, categories[inputNumberOfСategory - 1]);
                                        if (!listFilteredByCategory.isEmpty()) {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по категории: " + categories[inputNumberOfСategory - 1]);
                                            for (int i = 0; i < listFilteredByCategory.size(); i++) {
                                                System.out.println(listFilteredByCategory.get(i));
                                            }
                                        } else {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по категории: " + categories[inputNumberOfСategory - 1] + " пуст");
                                        }
                                    } else {
                                        System.out.println("Категории под таким номером нет");
                                    }
                                    break;
                                case "3":
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
                                            for (int i = 0; i < listFilteredByPriceRange.size(); i++) {
                                                System.out.println(listFilteredByPriceRange.get(i));
                                            }
                                        } else {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по ценовому диапазону от " + inputMinPrice + " руб. до " + inputMaxPrice + " руб. пуст");
                                        }
                                    } else
                                        System.out.println("Цена не может быть меньше, либо равна 0");
                                    break;
                                case "4":
                                    System.out.println("Укажите производителя:");
                                    String inputProducer = scanner.nextLine();
                                    List<Product> listFilteredByProducer = productFilter.filterByProducer(listOfProducts, inputProducer);
                                    if (!listFilteredByProducer.isEmpty()) {
                                        System.out.println("Список доступных для покупки товаров, " +
                                                "отфильтрованных по производителю: " + inputProducer);
                                        for (int i = 0; i < listFilteredByProducer.size(); i++) {
                                            System.out.println(listFilteredByProducer.get(i));
                                        }
                                    } else {
                                        System.out.println("Список доступных для покупки товаров, " +
                                                "отфильтрованных по производителю: " + inputProducer + " пуст");
                                    }
                                    break;
                                case "5":
                                    System.out.println("Укажите  значение рейтинга, относительно которого фильтровать:");
                                    int inputValue = Integer.parseInt(scanner.nextLine());
                                    System.out.println("Укажите номер варианта фильтра:" +
                                            "\n1. выше значения" +
                                            "\n2. ниже значения");
                                    String inputVariant = scanner.nextLine();
                                    if (inputVariant.equals("1")) {
                                        List<Product> listFilteredByRatingHigherThanValue = productFilter.filterByRatingHigherThanValue(listOfProducts, inputValue);
                                        if (!listFilteredByRatingHigherThanValue.isEmpty()) {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по рейтингу выше, чем  " + inputValue);
                                            for (int i = 0; i < listFilteredByRatingHigherThanValue.size(); i++) {
                                                System.out.println(listFilteredByRatingHigherThanValue.get(i));
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
                                            for (int i = 0; i < listFilteredByRatingLowerThanValue.size(); i++) {
                                                System.out.println(listFilteredByRatingLowerThanValue.get(i));
                                            }
                                        } else {
                                            System.out.println("Список доступных для покупки товаров, " +
                                                    "отфильтрованных по рейтингу ниже, чем  " + inputValue + " пуст");
                                        }
                                    } else {
                                        System.out.println("Варианта с таким номером нет");
                                    }
                                    break;
                                default:
                                    System.out.println("Параметра с таким номером нет");
                                    break;
                            }
                        }
                        break;
                    case "3":
                        System.out.println("Укажите артикул товара, количество которого хотите узнать:");
                        int productCodeToGetQuantity = Integer.parseInt(scanner.nextLine());
                        if (productCodeToGetQuantity > 0) {
                            System.out.println("Доступное к покупке количество товара: "
                                    + store.getQuantityByProductCode(productCodeToGetQuantity) + " шт.");
                        } else {
                            System.out.println("Артикул товара не может быть меньше либо равен нулю.");
                        }
                        break;
                    case "4":
                        System.out.println("Укажите артикул товара, который хотите добавить в корзину:");
                        int productCodeToPut = Integer.parseInt(scanner.nextLine());
                        System.out.println("Укажите количество товара:");
                        int quantityToPut = Integer.parseInt(scanner.nextLine());
                        if (basket.addProductToBasket(productCodeToPut, quantityToPut)) {
                            System.out.println("Товар добавлен в корзину");
                        } else {
                            System.out.println("Товар не добавлен в корзину");
                        }
                        break;
                    case "5":
                        System.out.println("Укажите артикул товара, который хотите убрать из корзины:");
                        int productCodeToRemove = Integer.parseInt(scanner.nextLine());
                        System.out.println("Укажите количество товара:");
                        int quantityToRemove = Integer.parseInt(scanner.nextLine());
                        if (basket.removeProductFromBasket(productCodeToRemove, quantityToRemove)) {
                            System.out.println("Товар убран из корзины");
                        } else {
                            System.out.println("Товар не убран из корзины");
                        }
                        break;
                    case "6":
                        List<String> basketList = basket.getListOfProductsInBasket();
                        if (basketList.isEmpty()) {
                            System.out.println("Корзина пуста");
                        } else {
                            System.out.println("Корзина:");
                            for (int i = 0; i < basketList.size(); i++) {
                                System.out.println(basketList.get(i));
                            }
                        }
                        break;
                    case "7":
                        System.out.println("Для оформления и последующей оплаты заказа укажите адрес элеткронной почты:");
                        String inputMail = scanner.nextLine();
                        System.out.println("Укажите имя и фамилию:");
                        String inputName = scanner.nextLine();
                        System.out.println("Укажите контактный телефон:");
                        String inputPhoneNumber = scanner.nextLine();
                        System.out.println("Укажите адрес доставки:");
                        String inputAddress = scanner.nextLine();
                        System.out.println("Спасибо за ваш заказ! Будем ждать вас снова!" +
                                "\nВ ближайшее время с вами свяжется наш менеджер для подтверждения заказа," +
                                "\nа на указанный адрес электронной почты придет письмо со счетом для оплаты заказа.");
                        break;
                    case "8":
                        System.out.println("Укажите артикул товара, который хотите оценить:");
                        int productCodeToRate = Integer.parseInt(scanner.nextLine());
                        if (store.isProductInRange(productCodeToRate)) {
                            System.out.println("Выберите вариант, указав номер:" +
                                    "\n1. Товар нравится" +
                                    "\n2. Товар не нравится");
                            String inputRating = scanner.nextLine();
                            if (inputRating.equals("1")) {
                                Product product = store.getProductFromProductsRange(productCodeToRate);
                                product.likeProduct();
                                System.out.println("Спасибо за вашу оценку! Текущий рейтинг товара: " + product.getRating());
                            } else if (inputRating.equals("2")) {
                                Product product = store.getProductFromProductsRange(productCodeToRate);
                                product.dislikeProduct();
                                System.out.println("Спасибо за вашу оценку! Текущий рейтинг товара: " + product.getRating());
                            } else {
                                System.out.println("Варианта ответа с таким номером нет");
                            }
                        } else {
                            System.out.println("Товара с таким артикулом нет в перечне товаров магазина");
                        }
                        break;
                    default:
                        System.out.println("Действия с таким номером нет");
                        break;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Вы ввели не цифру");
                continue;
            }
        }
    }
}
