package _07DistanceInLabyrinth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 27.12.2018 г.
 * Time: 11:33 ч.
 */
public class _07DistanceInLabyrinth {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(bufferedReader.readLine());

        String[][] matrix = new String[size][size];

        Cell start = new Cell(-1, -1, 0);

        for (int i = 0; i < size; i++) {
            String line = bufferedReader.readLine();

            String[] row = line.split("");

            int starColIndex = line.indexOf('*');

            if (starColIndex >= 0) {
                start.setRow(i);
                start.setCol(starColIndex);
            }

            matrix[i] = row;
        }

        Queue<Cell> queue = new LinkedList<>();
        queue.offer(start);

        while (queue.size() > 0) {

            Cell current = queue.poll();
            int currentRow = current.getRow();
            int currentCol = current.getCol();

            int newRow;
            int newCol;

            if (currentRow - 1 >= 0) {

                newRow = currentRow - 1;
                newCol = currentCol;

                calculateCell(newRow, newCol, current, matrix, queue);
            }

            if (currentCol + 1 < size) {

                newRow = currentRow;
                newCol = currentCol + 1;

                calculateCell(newRow, newCol, current, matrix, queue);
            }

            if (currentRow + 1 < size) {

                newRow = currentRow + 1;
                newCol = currentCol;

                calculateCell(newRow, newCol, current, matrix, queue);
            }

            if (currentCol - 1 >= 0) {

                newRow = currentRow;
                newCol = currentCol - 1;

                calculateCell(newRow, newCol, current, matrix, queue);
            }
        }


        for (int row = 0; row < size; row++) {

            StringBuilder printRow = new StringBuilder();

            for (int col = 0; col < size; col++) {

                String printCol = matrix[row][col];

                if (printCol.equals("0")) {
                    printCol = "u";
                }

                printRow.append(printCol);
            }

            System.out.println(printRow);
        }
    }

    private static void calculateCell(int newRow, int newCol,
                                      Cell current, String[][] matrix,
                                      Queue<Cell> queue) {
        String value = matrix[newRow][newCol];

        if (!value.equals("x")) {
            int currentDistance = current.getDistance();

            int newDistance = currentDistance + 1;

            if (newDistance < currentDistance
                    || value.equals("0")) {

                Cell newCell = new Cell(newRow, newCol,
                        newDistance);

                queue.offer(newCell);

                matrix[newRow][newCol] = String.valueOf(newDistance);
            }
        }
    }

    static class Cell {
        private int row;
        private int col;
        private int distance;

        public Cell(int row, int col,
                    int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}

