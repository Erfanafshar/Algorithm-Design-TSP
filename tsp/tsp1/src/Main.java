import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File("test.txt");
        
        Scanner scanner = new Scanner(file);
        //Scanner scanner = new Scanner(System.in);
        
        
        int number;
        String coordinatesOfPoints;
        String[] coordinates;
        Point lastPoint;
        Point nextPoint;

        number = Integer.valueOf(scanner.nextLine());
        Point[] points = new Point[number];
        Point[] result = new Point[number + 1];
        
        long startTime;
        long endTime;
        long time;

        for (int i = 0; i < number; i++) {
            coordinatesOfPoints = scanner.nextLine();
            coordinates = coordinatesOfPoints.split(" ");

            points[i] = new Point(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]));
        }
        
        /*Random random = new Random();
        for (int i = 0; i < number; i++) {

            points[i] = new Point(random.nextInt() % 100, random.nextInt() % 100);
        }*/

        int visitedPoints = 0;

        result[0] = points[0];
        lastPoint = points[0];

        //startTime = System.nanoTime();
        while (visitedPoints < number - 1) {

            visitedPoints++;
            lastPoint.setVisited(true);
            nextPoint = getNearestNeighbour(lastPoint,points,number);

            result[visitedPoints] = nextPoint;
            lastPoint = nextPoint;
        }

        result[number] = result[0];
        
        double sum = 0;
        
        for(int i = 0; i < number; i++){
            sum += getDistance(result[i] , result[i + 1]);
        }
        
        //endTime = System.nanoTime();
        //time = endTime - startTime;
        
        //System.out.println("Time : " + time);
        
        System.out.println("Length Of Tsp : " + sum);
        System.out.println("TSP tour : ");
        for(int i = 0; i < number + 1; i++){
            System.out.println(result[i].getX() + " " + result[i].getY());
        }
        
        new Draw(result,number);
    }

    private static Point getNearestNeighbour(Point firstPoint, Point[] points, int number) {
        Point nextPoint;
        double minDistance = Integer.MAX_VALUE;
        int resIndex = Integer.MAX_VALUE;
        double thisDistance;

        for (int i = 0; i < number; i++) {
            thisDistance = getDistance(firstPoint, points[i]);
            if (!points[i].isVisited()) {
                if (minDistance > thisDistance) {
                    minDistance = thisDistance;
                    resIndex = i;
                }
            }
        }

        nextPoint = points[resIndex];
        return nextPoint;
    }

    private static double getDistance(Point first, Point second) {
        double xDis = (first.getX() - second.getX()) * (first.getX() - second.getX());
        double yDis = (first.getY() - second.getY()) * (first.getY() - second.getY());

        return Math.sqrt(xDis + yDis);
    }
}

class Point {

    int x;
    int y;
    boolean isVisited;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        isVisited = false;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
   