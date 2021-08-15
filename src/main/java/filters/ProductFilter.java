package filters;

import products.Product;
import products.ProductCategory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFilter implements FilterByKeyWord<Product>, FilterByCategory<Product, ProductCategory>,
        FilterByProducer<Product, String>, FilterByPriceRange<Product>,
        FilterByRatingHigherThanValue<Product>, FilterByRatingLowerThanValue<Product> {
    @Override
        public List<Product> filterByKeyWord(List<Product> products, String keyWord) {
        List<Product> resultList = products.stream()
                .filter(product -> product.getName().contains(keyWord))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Product> filterByCategory(List<Product> products, ProductCategory category) {
        List<Product> resultList = products.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Product> filterByProducer(List<Product> products, String producer) {
        List<Product> resultList = products.stream()
                .filter(product -> product.getProducer().equals(producer))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Product> filterByPriceRange(List<Product> products, int minPrice, int maxPrice) {
        List<Product> resultList = products.stream()
                .filter(product -> product.getPrice() >= minPrice)
                .filter(product -> product.getPrice() <= maxPrice)
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Product> filterByRatingHigherThanValue(List<Product> products, int rating) {
        List<Product> resultList = products.stream()
                .filter(product -> product.getRating() > rating)
                .sorted(Comparator.comparing(Product::getRating))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Product> filterByRatingLowerThanValue(List<Product> products, int rating) {
        List<Product> resultList = products.stream()
                .filter(product -> product.getRating() < rating)
                .sorted(Comparator.comparing(Product::getRating).reversed())
                .collect(Collectors.toList());
        return resultList;
    }
}
