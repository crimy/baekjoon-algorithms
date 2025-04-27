package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem1697 {
    static int from, to;
    static int max = 100000;
    static boolean[] visited = new boolean[max + 1];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        from = sc.nextInt();
        to = sc.nextInt();
        int result = bfs();
        System.out.println(result);
    }
    public static int solve(int fromPosition, int toPosition) {
        from = fromPosition;
        to = toPosition;
        visited = new boolean[max + 1];
        return bfs();
    }
    public static int bfs() {
        int result = Integer.MAX_VALUE;
        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(new Vertex(from, 0));
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            int position = vertex.position;
            int second = vertex.second;
            visited[position] = true;
            if(position == to) {
                result = Math.min(result, second);
            }
            if(position * 2 <= max && !visited[position * 2]) {
                queue.offer(new Vertex(position * 2, second + 1));
            }
            if(position + 1 <= max && !visited[position + 1]) {
                queue.offer(new Vertex(position + 1, second + 1));
            }
            if(position - 1 >= 0 && !visited[position - 1]) {
                queue.offer(new Vertex(position - 1, second + 1));
            }
        }
        return result;
    }
    public static class Vertex {
        int position;
        int second;

        public Vertex(int position, int second) {
            this.position = position;
            this.second = second;
        }
    }
}
