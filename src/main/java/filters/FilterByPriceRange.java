package filters;

import java.util.List;

public interface FilterByPriceRange<T> {
    List<T> filterByPriceRange(List<T> list, int minPrice, int maxPrice);
}
