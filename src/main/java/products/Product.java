package products;

import java.util.Objects;

public class Product {
    protected final int productCode; //артикул товара
    protected String name;
    protected ProductCategory category;
    protected int price;
    protected String producer;
    protected int rating; // количество голосов покупателей за товар

    public Product(int productCode, String name, ProductCategory category, int price, String producer, int rating) {
        this.productCode = productCode;
        this.name = name;
        this.category = category;
        this.category = category;
        this.price = price;
        this.producer = producer;
        this.rating = rating;
    }

    public Product(int productCode, String name, ProductCategory category, int price, String producer) {
        this.productCode = productCode;
        this.name = name;
        this.category = category;
        this.price = price;
        this.producer = producer;
        rating = 0;
    }

    public int getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getProducer() {
        return producer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void likeProduct() {
        rating++;
    }

    public void dislikeProduct() {
        if (rating > 0) {
            rating--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productCode == product.productCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode);
    }

    @Override
    public String toString() {
        return "-------------------------------\nАртикул: " + productCode + ", производитель: " + producer + ", категория: " + category +
                ",\nнаименование: " + name + ",  цена: " + price + " руб., рейтинг: " + rating;
    }
}
