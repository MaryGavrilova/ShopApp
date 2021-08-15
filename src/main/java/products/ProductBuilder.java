package products;

public class ProductBuilder {
    protected int productCode;
    protected String name;
    protected ProductCategory category;
    protected int price;
    protected String producer;
    protected int rating;

    public ProductBuilder() {
        rating = 0;
    }

    public ProductBuilder setProductCode(int productCode) {
        if (productCode < 1) {
            throw new IllegalArgumentException("Артикул не может быть меньше, либо равен 0");
        } else {
            this.productCode = productCode;
        }
        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setCategory(ProductCategory category) {
        this.category = category;
        return this;
    }

    public ProductBuilder setPrice(int price) {
        if (price < 1) {
            throw new IllegalArgumentException("Цена товара не может быть меньше, либо равна 0");
        } else {
            this.price = price;
        }
        return this;
    }

    public ProductBuilder setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public ProductBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Product build() {
        if (name == null || price == 0 || category == null || productCode == 0 || producer == null) {
            throw new IllegalStateException("Не все необходимые параметры товара известны");
        }
        Product product = new Product(productCode, name, category, price, producer, rating);
        return product;
    }
}