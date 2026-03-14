package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }

        T best = this.get(0);
        for (int i = 1; i < this.size(); i += 1) {
            T current = this.get(i);
            if (c.compare(current, best) > 0) {
                best = current;
            }
        }
        return best;
    }
    public T max() {
        return max(this.comparator);
    }
}
