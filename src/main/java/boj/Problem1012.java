package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem1012 {
    static int t, n, m, k;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            m = sc.nextInt();
            n = sc.nextInt();
            k = sc.nextInt();

            map = new int[n][m];
            visited = new boolean[n][m];

            for (int j = 0; j < k; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                map[y][x] = 1;
            }

            int count = 0;
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (map[y][x] == 1 && !visited[y][x]) {
                        bfs(x, y);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                    if (map[ny][nx] == 1 && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
