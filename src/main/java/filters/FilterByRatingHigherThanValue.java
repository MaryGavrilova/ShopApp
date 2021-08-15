package filters;
import java.util.List;

public interface FilterByRatingHigherThanValue<T> {
    List<T> filterByRatingHigherThanValue(List<T> products, int rating);
}
