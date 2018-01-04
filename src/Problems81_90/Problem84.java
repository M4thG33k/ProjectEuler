package Problems81_90;


import java.util.*;

public class Problem84 {

    private static int diceSides = 4;
    private static List<String> spots = new ArrayList<>(40);
    private static Map<String, Integer> nameToSpotMap = new HashMap<>(40);
    private static List<String> ccCards = new ArrayList<>(16);
    private static List<String> chCards = new ArrayList<>(16);
    private static Set<String> chance = new HashSet<>();
    private static Set<String> cchest = new HashSet<>();
    private static Map<Integer, Integer> numEndings = new HashMap<>(40);
    private static Random random = new Random();
    private static int[] dice = {0, 0};
    private static int location = 0;

    public static void main(String[] args) {
        initializeSpots();
        initializeCards();

        int numTurns = 1000000;

        for (int i = 0; i < numTurns; i++) {
            takeTurn();
            numEndings.put(location, numEndings.get(location) + 1);
        }

        List<LandChance> finalRestingPlaces = new ArrayList<>(40);
        for (int spot :
                numEndings.keySet()) {
            finalRestingPlaces.add(new LandChance(spot, numEndings.get(spot), numTurns ));
        }

        finalRestingPlaces.sort(Comparator.comparingDouble(x -> -x.probability));

        System.out.println(finalRestingPlaces);

    }

    private static void initializeSpots() {
        String[] names = {"GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3", "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3", "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3", "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2"};
        for (int i = 0; i < names.length; i++) {
            createSpot(i, names[i]);
            numEndings.put(i, 0);
        }
    }

    private static void createSpot(int i, String name) {
        spots.add(name);
        nameToSpotMap.put(name, i);
    }

    private static void initializeCards() {
        for (int i = 1; i < 4; i++) {
            cchest.add("CC" + i);
            chance.add("CH" + i);
        }

        // Community Chest
        for (int i = 0; i < 14; i++) {
            ccCards.add("");
        }
        ccCards.add("GO");
        ccCards.add("JAIL");

        // Chance Cards
        for (int i = 0; i < 6; i++) {
            chCards.add("");
        }

        String[] cards = new String[]{"GO", "JAIL", "C1", "E3", "H2", "R1", "NR", "NR", "NU", "-3"};
        chCards.addAll(Arrays.asList(cards));
    }

    private static void rollDice() {
        dice[0] = random.nextInt(diceSides) + 1;
        dice[1] = random.nextInt(diceSides) + 1;
    }

    private static boolean isDoubles() {
        return dice[0] == dice[1];
    }

    private static int takeTurn() {
        int rolls = 0;
        while (rolls < 3) {
            rolls += 1;
            rollDice();
            if (isDoubles() && rolls == 3) {
                location = nameToSpotMap.get("JAIL");
                return rolls;
            }
            location = (location + dice[0] + dice[1]) % 40;

            if (chance.contains(spots.get(location))) {
                if (!handleChance()) {
                    return rolls;
                }
            } else if (cchest.contains(spots.get(location))) {
                if (!handleCChest()) {
                    return rolls;
                }
            } else if (spots.get(location).equals("G2J")) {
                location = nameToSpotMap.get("JAIL");
                return rolls;
            }

            if (!isDoubles()) {
                return rolls;
            }
        }
        return rolls;
    }

    private static boolean handleCChest() {
        switch (ccCards.get(random.nextInt(ccCards.size()))) {
            case "GO":
                location = 0;
                return true;
            case "JAIL":
                location = nameToSpotMap.get("JAIL");
                return false;
            default:
                return true;
        }
    }

    private static boolean handleChance() {
        String card = chCards.get(random.nextInt(chCards.size()));
        switch (card) {
            case "GO":
                location = 0;
                return true;
            case "JAIL":
                location = nameToSpotMap.get("JAIL");
                return false;
            case "C1":
            case "E3":
            case "H2":
            case "R1":
                location = nameToSpotMap.get(card);
                return true;
            case "-3":
                location -= 3;
                return true;
            case "NR":
                // Next railroad
                int offset = location % 10;
                if (offset > 5) {
                    location += 15 - offset;
                } else {
                    location += 5 - offset;
                }
                location %= 40;
                return true;
            case "NU":
                // Next Utility
                if (location > nameToSpotMap.get("U1") && location < nameToSpotMap.get("U2")) {
                    location = nameToSpotMap.get("U2");
                } else {
                    location = nameToSpotMap.get("U1");
                }
                return true;
            default:
                // Not moving
                return true;
        }
    }

    private static class LandChance {
        public String spot;
        public double probability;

        public LandChance(int location, int lands, int numTurns) {
            spot = String.format("%02d", location);
            probability = ((double) lands) / ((double) numTurns);
        }

        @Override
        public String toString() {
            return String.format("%s => %.5f", spot, probability);
        }
    }

}
