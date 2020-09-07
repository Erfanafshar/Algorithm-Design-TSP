import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static double minCost = Integer.MAX_VALUE;
    static int[] bestIndexes;

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File("test.txt");
        
        Scanner scanner = new Scanner(file);
        //Scanner scanner = new Scanner(System.in);
        
        int number;
        String coordinatesOfPoints;
        String[] coordinates;

        number = Integer.valueOf(scanner.nextLine());

        Point[] points = new Point[number];
        int[] indexes = new int[number + 1];
        Point[] result = new Point[number + 1];
        bestIndexes = new int[number + 1];
        
        long startTime;
        long endTime;
        long time;


        indexes[0] = 0;
        indexes[number] = 0;
        for (int i = 1; i < number; i++) {
            indexes[i] = i;
        }

        for (int i = 0; i < number; i++) {
            coordinatesOfPoints = scanner.nextLine();
            coordinates = coordinatesOfPoints.split(" ");

            points[i] = new Point(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]));
        }
        
        
        /*Random random = new Random();
        for (int i = 0; i < number; i++) {
            
            points[i] = new Point(random.nextInt() % 100, random.nextInt() % 100);
        }*/

        //startTime = System.nanoTime();
        findMinCost(points, number , indexes, 1, number);
        
        //endTime = System.nanoTime();
        //time = endTime - startTime;
        
        //System.out.println("Time : " + time);
        
        System.out.println("Length Of Tsp : " + minCost);
        System.out.println("TSP tour : ");
        
                
        for(int i = 0; i < number; i++){
            int k = bestIndexes[i];
            result[i] = points[k];
        }
        
        result[number] = result[0];
       
        for(int i = 0; i < number + 1; i++){
            System.out.println(result[i].getX() + " " + result[i].getY());
        }
        
        new Draw(result,number);
    }

    private static void findMinCost(Point[] points, int number, int[] index, int first, int last) {
        double cost;
        if (first == last) {
            cost = getCost(index, points, number);
            if (cost < minCost) {
                minCost = cost;
                bestIndexes = index;
            }
        } else {
            for (int i = first; i < last; i++) {

                index = swap(index, first, i);
                findMinCost(points, number, index, first + 1, last);
                index = swap(index, first, i);
            }
        }
    }

    public static double getCost(int[] thisPath, Point[] points, int number) {
        double sum = 0;

        for (int i = 0; i < number; i++) {
            sum += getDistance(points[thisPath[i]], points[thisPath[i + 1]]);
        }
        return sum;
    }

    private static double getDistance(Point first, Point second) {
        double xDis = (first.getX() - second.getX()) * (first.getX() - second.getX());
        double yDis = (first.getY() - second.getY()) * (first.getY() - second.getY());
        return Math.sqrt(xDis + yDis);
    }

    private static int[] swap(int[] index, int i, int j) {
        int tmp;
        tmp = index[i];
        index[i] = index[j];
        index[j] = tmp;
        return index;
    }
}

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}