package Problems11_20;

import java.util.HashMap;
import java.util.Map;

public class Collatz {
    private Map<Long, Long> map;

    public Collatz()
    {
        map = new HashMap<>();
        map.put(1L,1L);
    }

    public long lengthOf(long val)
    {
        if (!map.containsKey(val))
        {
            map.put(val, 1 + lengthOf(nextVal(val)));
        }

        return map.get(val);
    }

    public long nextVal(long val)
    {
        if ((val&1)==1)
        {
            return 3*val + 1;
        }
        return val / 2;
    }
}
