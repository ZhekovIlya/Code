package com.ua.zhekov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task2 {

    private static class SimpleReader {
        BufferedReader br;
        StringTokenizer st;

        public SimpleReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static class City {
        private int id;
        private int dst;

        public City(int id, int dst) {
            this.id = id;
            this.dst = dst;
        }

        public int getDst() {
            return dst;
        }
    }

    private static void Dijkstra(int sourceCityId, int destinationCityId, Map<Integer, List<City>> neighbourCity,
                                 Map<Integer, Integer> actualDistanceToNeighbour) {
        //pq will contain cities with actual distance to them from sourceCity
        PriorityQueue<City> pq = new PriorityQueue<>(Comparator.comparing(City::getDst));
        pq.add(new City(sourceCityId, 0));
        actualDistanceToNeighbour.put(sourceCityId, 0);
        while (!pq.isEmpty()) {
            City tmp = pq.poll();
            //return if we on destinationCity (compare by id)
            if (tmp.id == destinationCityId) {
                return;
            }
            //fill actualDistanceToNeighbour cities finding shortest distances between sourceCity and destination
            for (City neighbour : neighbourCity.get(tmp.id)) {
                int curDistance = neighbour.dst + tmp.dst;
                if (curDistance < actualDistanceToNeighbour.get(neighbour.id)) {
                    pq.add(new City(neighbour.id, curDistance));
                    actualDistanceToNeighbour.put(neighbour.id, curDistance);
                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleReader sr = new SimpleReader();
        int s = sr.nextInt();
        for (int tc = 0; tc < s; tc++) {
            //Solving here
            Map<String, Integer> cityDirectory = new HashMap<>();
            //number of cities
            int n = sr.nextInt();
            Map<Integer, List<City>> neighbourCity = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                // for each city
                String name = sr.next();
                cityDirectory.put(name, i);
                int edges = sr.nextInt();
                List<City> cities = new ArrayList<>();
                for (int j = 0; j < edges; j++) {
                    // for each neighbour city
                    int id = sr.nextInt();
                    int w = sr.nextInt();
                    cities.add(new City(id, w));
                }
                // putting neighbour Cities to the map, by a key equals to the current City id
                neighbourCity.put(i, cities);
            }
            int r = sr.nextInt();
            for (int i = 0; i < r; i++) {
                //get current city and destination city ids from console input of City name
                int currentCityId = cityDirectory.get(sr.next());
                int destinationCityId = cityDirectory.get(sr.next());
                //creating map where key is destCity.id  value is weight of distance
                Map<Integer, Integer> actualDistanceToNeighbour = new HashMap<>();
                //fill values by a cities ids and Integer max value as a distance to sure that its max
                neighbourCity.forEach((k, v) -> actualDistanceToNeighbour.put(k, Integer.MAX_VALUE));
                //perform dijkstra algorithm to fill actualDistanceToNeighbour with a correct values for current city
                Dijkstra(currentCityId, destinationCityId, neighbourCity, actualDistanceToNeighbour);
                //print actual distance
                System.out.println(actualDistanceToNeighbour.get(destinationCityId));
            }
        }
    }
}
