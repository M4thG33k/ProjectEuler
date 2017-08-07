package Problems51_60;

import Util.Prime;

import java.util.*;

// Not fast, but it works!

public class Problem60 {

    private static List<Long> primes = Prime.primesStrictlyBelow(30000);
    private static Set<Long> pset = new HashSet<>(primes);
    private static Map<Long, Map<Long, Boolean>> map = new HashMap<>();

    public static void main(String[] args) {
        init();

        long answer = Long.MAX_VALUE;

        for (int a=0; a<primes.size(); a++)
        {
            long A = primes.get(a);
            if (A * 5 >= answer)
            {
                break;
            }
            for (int b=a+1; b<primes.size(); b++)
            {
                long B = primes.get(b);
                if (A + B*4 >= answer)
                {
                    break;
                }

                if (!map.get(A).get(B))
                {
                    continue;
                }

                for (int c=b+1; c<primes.size(); c++)
                {
                    long C = primes.get(c);
                    if (A+B + C*3 >= answer)
                    {
                        break;
                    }

                    if (!map.get(A).get(C) || !map.get(B).get(C))
                    {
                        continue;
                    }

                    for (int d=c+1; d<primes.size(); d++)
                    {
                        long D = primes.get(d);
                        if (A+B+C+D+D >= answer)
                        {
                            break;
                        }

                        if (!map.get(A).get(D) ||
                                !map.get(B).get(D) ||
                                !map.get(C).get(D))
                        {
                            continue;
                        }

                        for (int e=d+1; e<primes.size(); e++)
                        {
                            long E = primes.get(e);

                            long sum = A+B+C+D+E;
                            if (sum >= answer)
                            {
                                break;
                            }

                            if (!map.get(A).get(E) ||
                                    !map.get(B).get(E) ||
                                    !map.get(C).get(E) ||
                                    !map.get(D).get(E))
                            {
                                continue;
                            }

                            if (sum < answer)
                            {
                                System.out.println(A + ", " + B + ", " + C + ", " + D + ", " + E);
                                answer = sum;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static void init()
    {
        double size = pset.size();
        int count = 0;
        for (int i=0; i<primes.size(); i++)
        {
            long p1 = primes.get(i);
            System.out.println("Init: " + count/size);
            count += 1;
            map.put(p1, new HashMap<>());
            for (int j=i+1; j<primes.size(); j++)
            {
                long p2 = primes.get(j);
                if (Prime.isPrime(Long.parseLong(p1+""+p2)) && Prime.isPrime(Long.parseLong(p2+""+p1)))
                {
                    map.get(p1).put(p2, Boolean.TRUE);
                }
                else
                {
                    map.get(p1).put(p2, Boolean.FALSE);
                }
            }
        }
    }
}
