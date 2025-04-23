package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem4179 {
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int[][] fireMap;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        fireMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(fireMap[i], -1);  // 불 도달 시간 초기화
        }

        Queue<int[]> fireQueue = new LinkedList<>();
        Queue<Position> jihunQueue = new LinkedList<>();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < m; j++) {
                char space = line.charAt(j);
                if (space == 'J') {
                    map[i][j] = 1;
                    jihunQueue.offer(new Position(i, j, 0));
                } else if (space == 'F') {
                    map[i][j] = 0;
                    fireMap[i][j] = 0;
                    fireQueue.offer(new int[]{i, j});
                } else if (space == '.') {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }
        visited = new boolean[n][m];
        int result = bfs(jihunQueue, fireQueue);
        if (result == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }
    }

    public static int bfs(Queue<Position> jihunQueue, Queue<int[]> fireQueue) {
        int result = Integer.MAX_VALUE;
        while (!fireQueue.isEmpty()) {
            int[] f = fireQueue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = f[0] + dx[i];
                int ny = f[1] + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 0 && fireMap[nx][ny] == -1) {
                    fireMap[nx][ny] = fireMap[f[0]][f[1]] + 1;
                    fireQueue.add(new int[]{nx, ny});
                }
            }
        }

        while (!jihunQueue.isEmpty()) {
            Position cur = jihunQueue.poll();
            if (cur.x == 0 || cur.x == n-1 || cur.y == 0 || cur.y == m-1) {
                return cur.count + 1;  // 탈출
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m
                        && map[nx][ny] == 1 && !visited[nx][ny]
                        && (fireMap[nx][ny] == -1 || fireMap[nx][ny] > cur.count + 1)) {

                    visited[nx][ny] = true;
                    jihunQueue.add(new Position(nx, ny, cur.count + 1));
                }
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
