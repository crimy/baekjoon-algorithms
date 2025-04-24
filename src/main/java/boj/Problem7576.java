package boj;

import java.util.*;

public class Problem7576 {
    static int n, m;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        sc.nextLine();
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            int[] nums = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            System.arraycopy(nums, 0, map[i], 0, m);
        }
        System.out.println(bfs());
    }

    public static int solve(int solveN, int solveM, int[][] solveMap) {
        n = solveN;
        m = solveM;
        map = solveMap;
        return bfs();
    }

    public static int bfs() {
        Queue<Position> queue = new LinkedList<>();
        int result = 0;

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (map[i][j] == 1) {
                    queue.offer(new Position(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Position position = queue.poll();
            result = position.day;

            for (int i = 0; i < 4; i++) {
                int nx = position.x + dx[i];
                int ny = position.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

                if (map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    queue.offer(new Position(nx, ny, position.day + 1));
                }
            }
        }

        for (int[] row : map) {
            for (int tomato : row) {
                if (tomato == 0) return -1;
            }
        }

        return result;
    }


    public static class Position {
        int x;
        int y;
        int day;

        public Position(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
}
