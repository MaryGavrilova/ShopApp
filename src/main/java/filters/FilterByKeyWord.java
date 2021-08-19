package filters;

import java.util.List;

public interface FilterByKeyWord<T> {
    List<T> filterByKeyWord(List<T> list, String keyWord);
}
