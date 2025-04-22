package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem4179 {
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
        Position start;
        Position fire;
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                char space = line.charAt(j);
                if (space == 'J') {
                    map[i][j] = 1;
                    start = new Position(i, j, 0);
                } else if (space == 'F') {
                    map[i][j] = -1;
                    fire = new Position(i, j, 0);
                } else if (space == '.') {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }
        visited = new boolean[n][m];
        int result = bfs(start, fire);
        System.out.println(result);
    }
//    1 : 통행, 0 : 불가능, -1 : 불
    public static int bfs(Position start) {
        int result = Integer.MAX_VALUE;
        Queue<Position> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if ((current.x == n - 1 || current.y == m - 1) && map[current.x][current.y] != 'F') {
                result = Math.min(result, current.count);
            }
            for (int k = 0; k < 4; k++) {
                int nx = current.x + dx[k];
                int ny = current.y + dy[k];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || map[nx][ny] == 'F') {
                    continue;
                }
                int fireNx = nx + dx[k];
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
