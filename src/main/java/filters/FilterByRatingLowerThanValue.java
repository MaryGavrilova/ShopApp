package filters;
import java.util.List;

public interface FilterByRatingLowerThanValue<T> {
    List<T> filterByRatingLowerThanValue(List<T> products, int rating);
}
