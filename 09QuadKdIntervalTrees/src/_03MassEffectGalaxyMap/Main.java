package _03MassEffectGalaxyMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int starClustersCount = Integer
                .parseInt(bufferedReader.readLine());

        int reportsCount = Integer
                .parseInt(bufferedReader.readLine());

        int galaxySize = Integer
                .parseInt(bufferedReader.readLine());

        List<StarCluster> starClusters = new ArrayList<>();

        for (int i = 0; i < starClustersCount; i++) {
            String[] input = bufferedReader.readLine()
                    .split("\\s+");

            StarCluster starCluster = new StarCluster(input[0],
                    Integer.parseInt(input[1]),
                    Integer.parseInt(input[2]));

            if (starCluster.getX() >= 0
                    && starCluster.getX() <= galaxySize
                    && starCluster.getY() >= 0
                    && starCluster.getY() <= galaxySize) {
                starClusters.add(starCluster);
            }
        }

        starClusters.sort((a, b) -> {
            int comp = a.getX() - b.getX();
            if (comp != 0) {
                return comp;
            }
            return a.getY() - b.getY();
        });

        KdTree galaxy = new KdTree();
        galaxy.buildFromList(starClusters);

        for (int i = 0; i < reportsCount; i++) {
            starClusters = new ArrayList<>();

            String[] input = bufferedReader.readLine()
                    .split("\\s+");

            Rectangle range = new Rectangle(Integer.parseInt(input[1]),
                    Integer.parseInt(input[2]),
                    Integer.parseInt(input[3]),
                    Integer.parseInt(input[4]));

            galaxy.eachInOrderInRange(starClusters::add, range);

            System.out.println(starClusters.size());
        }

    }
}
