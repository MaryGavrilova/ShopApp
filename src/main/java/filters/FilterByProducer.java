package filters;

import java.util.List;

public interface FilterByProducer<T, Y> {
    List<T> filterByProducer(List<T> list, Y producer);
}
