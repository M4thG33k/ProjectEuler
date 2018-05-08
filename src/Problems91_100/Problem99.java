package Problems91_100;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Problem99 {

    public static void main(String[] args) {
        // It's math time, kids! Since the log functions are strictly increasing functions, we know that
        // if x > y, then log(x) > log(y). Moreover, using properties of logs, we know log(a^b) = b*log(a)!
        // This simplification gives us an easy solution!
        try (Stream<String> stream = Files.lines(Paths.get("files/p099_base_exp.txt"))) {
            AtomicInteger lineNumber = new AtomicInteger(1);
            AtomicReference<Double> maxim = new AtomicReference<>(0.0);
            AtomicInteger maxLine = new AtomicInteger();

            stream.forEach(
                    line -> {
                        String[] vals = line.split(",");
                        double value = Integer.parseInt(vals[1]) * Math.log(Double.parseDouble(vals[0]));
                        if (value > maxim.get()) {
                            maxim.set(value);
                            maxLine.set(lineNumber.get());
                        }
                        lineNumber.incrementAndGet();
                    }
            );

            System.out.println(maxLine.get());

        } catch (IOException e) {
            System.out.println("Error loading file");
        }
    }
}
