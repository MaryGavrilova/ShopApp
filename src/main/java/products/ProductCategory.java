package products;

public enum ProductCategory {
    PASTRY ("Кондитерские изделия"),
    DAIRY ("Молочные продукты и сыры"),
    MEAT ("Мясные и колбасные изделия"),
    DRINKS ("Напитки"),
    BAKERY ("Хлебобулочные изделия");

    private String title;

    ProductCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

}
