package daily;

import java.util.HashMap;
import java.util.Map;

public class LC1396DesignUndergroundSystem {
    static private class CheckInPair {
        int timeIn;
        String checkInStation;

        CheckInPair(int timeIn, String checkInStation) {
            this.timeIn = timeIn;
            this.checkInStation = checkInStation;
        }
    }

    static private class CounterPair {
        int timeCost;
        int counter;

        CounterPair(int timeCost, int counter) {
            this.timeCost = timeCost;
            this.counter = counter;
        }
    }


    static class UndergroundSystem {
        Map<Integer, CheckInPair> checkInMap;
        Map<String, CounterPair> summarize;

        public UndergroundSystem() {
            checkInMap = new HashMap<>();
            summarize = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            if (!checkInMap.containsKey(id)) {
                CheckInPair pair = new CheckInPair(t, stationName);
                checkInMap.put(id, pair);
            }
        }

        public void checkOut(int id, String stationName, int t) {
            CheckInPair pair = checkInMap.get(id);
            int checkInTime = pair.timeIn;
            String checkInStation = pair.checkInStation;
            int timeCost = t - checkInTime;
            String summaryKey = checkInStation + "+" + stationName;

            if (!summarize.containsKey(summaryKey)) {
                CounterPair counterPair = new CounterPair(timeCost, 1);
                summarize.put(summaryKey, counterPair);
            } else {
                CounterPair counterPair = summarize.get(summaryKey);
                counterPair.counter++;
                counterPair.timeCost += timeCost;
            }
            checkInMap.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            CounterPair counterPair = summarize.get(startStation+ "+" + endStation);
            return (double) counterPair.timeCost / counterPair.counter;
        }
    }

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(37043, "K2618O72", 29);
        undergroundSystem.checkOut(37043, "U1DTINDT", 39);
        undergroundSystem.getAverageTime("K2618O72", "U1DTINDT");

        undergroundSystem.checkIn(779975, "K2618O72", 112);
        undergroundSystem.checkOut(779975, "CN3K6CYM", 157);
        undergroundSystem.getAverageTime("K2618O72", "U1DTINDT");

    }
}
