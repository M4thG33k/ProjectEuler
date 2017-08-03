package Problems51_60;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Problem54 {

    private static Map<Character, Integer> valMap = new HashMap<>();

    public static void main(String[] args) {
        char[] cardChars = new char[]{'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
        for (int i=0; i<cardChars.length; i++)
        {
            valMap.put(cardChars[i], i);
        }

        int firstWins = 0;
        int counter = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("p054_poker.txt"));
            String line;

            while ((line = reader.readLine()) != null)
            {
                counter += 1;
                String[] vals = line.split(" ");
                Hand first = new Hand();
                Hand second = new Hand();
                for (int i=0; i<10; i++)
                {
                    if (i<5)
                    {
                        first.addCard(vals[i]);
                    }
                    else
                    {
                        second.addCard(vals[i]);
                    }
                }

                int r1 = first.getRank();
                int r2 = second.getRank();

                if (r1 != r2)
                {
                    if (r1 > r2)
                    {
                        firstWins += 1;
                    }
                }
                else {
                    List<Integer> order1 = first.getOrder(r1);
                    List<Integer> order2 = second.getOrder(r2);

                    for (int i=0; i<order1.size(); i++)
                    {
                        if (order1.get(i).equals(order2.get(i)))
                        {
                            continue;
                        }

                        if (order1.get(i) > order2.get(i))
                        {
                            firstWins += 1;
                        }
                        break;
                    }
                }
            }

            reader.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(firstWins);
    }

    private static class Hand
    {
        private int[] values = new int[13];
        private boolean isStraight;
        private Set<Character> suits = new HashSet<>();
        private int numCards = 0;
        private List<Integer> cards = new ArrayList<>();

        public Hand()
        {

        }

        public void addCard(String card)
        {
            numCards += 1;
            values[valMap.get(card.charAt(0))] += 1;
            suits.add(card.charAt(1));

            if (numCards == 5)
            {
                int first = 0;
                while (values[first] == 0)
                {
                    first += 1;
                }

                isStraight = true;
                for (int i=0; i<5; i++)
                {
                    if (first+i > 12 || values[first+i] != 1)
                    {
                        isStraight = false;
                        break;
                    }
                }

                createCards();
            }
        }

        private void createCards()
        {
            for (int i=0; i<13; i++)
            {
                if (values[i] > 0)
                {
                    cards.add((i<<3) + values[i]);
                }
            }

            Collections.sort(cards);
        }

        private boolean hasFlush()
        {
            return suits.size() == 1;
        }

        private boolean hasStraightFlush()
        {
            return isStraight && hasFlush();
        }

        private boolean hasRoyalFlush()
        {
            return values[12] == 1 && hasStraightFlush();
        }

        private boolean hasFourOfAKind()
        {
            return (cards.size() == 2 && !hasFullHouse());
        }

        private boolean hasFullHouse()
        {
            return (cards.size() == 2 && hasThreeOfAKind());
        }

        private boolean hasThreeOfAKind()
        {
            for (int val : cards)
            {
                if ((val & 7)==3)
                {
                    return true;
                }
            }
            return false;
        }

        private int countPairs()
        {
            int pairs = 0;
            for (int i : cards)
            {
                if ((i&7) == 2)
                {
                    pairs += 1;
                }
            }
            return pairs;
        }

        private int getRank()
        {
            if (this.hasRoyalFlush())
            {
                return 9;
            }

            if (this.hasStraightFlush())
            {
                return 8;
            }

            if (this.hasFourOfAKind())
            {
                return 7;
            }

            if (this.hasFullHouse())
            {
                return 6;
            }

            if (this.hasFlush())
            {
                return 5;
            }

            if (this.isStraight)
            {
                return 4;
            }

            if (this.hasThreeOfAKind())
            {
                return 3;
            }

            return countPairs();
        }

        private List<Integer> getOrder(int rank)
        {
            List<Integer> ret = new ArrayList<>();
            switch (rank)
            {
                case 8:
                    ret.add(cards.get(cards.size()-1) >> 3);
                    break;
                case 7:
                    for (int i: cards)
                    {
                        if ((i&7)==4)
                        {
                            ret.add(i>>3);
                            break;
                        }
                    }
                    break;
                case 6:
                    if ((cards.get(0)&7) == 2)
                    {
                        ret.add(cards.get(1)>>3);
                        ret.add(cards.get(0)>>3);
                    }
                    else
                    {
                        ret.add(cards.get(0)>>3);
                        ret.add(cards.get(1)>>3);
                    }
                    break;
                case 5:
                case 4:
                    ret.add(cards.get(4)>>3);
                    break;
                case 3:
                    for (int i=2; i>=0; i--)
                    {
                        if ((cards.get(i)&7) == 3)
                        {
                            ret.add(cards.get(i)>>3);
                            break;
                        }
                    }
                    for (int i=2; i>=0; i--)
                    {
                        if ((cards.get(i)&7) != 3)
                        {
                            ret.add(cards.get(i)>>3);
                            break;
                        }
                    }
                    break;
                case 2:
                case 1:
                    for (int i=cards.size()-1; i>=0; i--)
                    {
                        if ((cards.get(i)&7) == 2)
                        {
                            ret.add(cards.get(i)>>3);
                            break;
                        }
                    }
                    for (int i=cards.size()-1; i>=0; i--)
                    {
                        if ((cards.get(i)&7) != 2)
                        {
                            ret.add(cards.get(i)>>3);
                            break;
                        }
                    }
                    break;
                default:
                    for (int i=4; i>=0; i--)
                    {
                        ret.add(cards.get(i)>>3);
                    }
            }
            return ret;
        }
    }
}
