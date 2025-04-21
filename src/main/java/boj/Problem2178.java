package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem2178 {
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        visited = new boolean[n][m];
        int result = bfs();
        System.out.println(result);
    }

    public static int solve(int solveN, int solveM, int[][] solveMap) {
        n = solveN;
        m = solveM;
        visited = new boolean[n][m];
        map = solveMap;
        return bfs();
    }

    public static int bfs() {
        int result = Integer.MAX_VALUE;
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(0, 0, 1));
        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (current.x == n - 1 && current.y == m - 1) {
                result = Math.min(result, current.count);
            }
            for (int k = 0; k < 4; k++) {
                int nx = current.x + dx[k];
                int ny = current.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                queue.offer(new Position(nx, ny, current.count + 1));
                visited[nx][ny] = true;
            }
        }
        return result;
    }

    public static class Position {
        int x;
        int y;
        int count;

        public Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
