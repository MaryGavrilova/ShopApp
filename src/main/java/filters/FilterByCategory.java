package filters;

import java.util.List;

public interface FilterByCategory<T, Y> {
    List<T> filterByCategory(List<T> list, Y category);
}
