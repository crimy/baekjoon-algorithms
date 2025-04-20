package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem1926 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static int[] solve(int n, int m, int[][] drawing) {
        boolean[][] visited = new boolean[n][m];
        int max = 0;
        int drawingCount = 0;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (!visited[x][y] && drawing[x][y] == 1) {
                    drawingCount++;
                    max = Math.max(max, bfs(x, y, n, m, drawing, visited));
                }
            }
        }
        return new int[]{drawingCount, max};
    }

    private static int bfs(int x, int y, int n, int m, int[][] drawing, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int area = 1;

        while (!queue.isEmpty()) {
            int[] polled = queue.poll();
            int px = polled[0], py = polled[1];

            for (int k = 0; k < 4; k++) {
                int nx = px + dx[k];
                int ny = py + dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (!visited[nx][ny] && drawing[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        area++;
                    }
                }
            }
        }

        return area;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] split = br.readLine().split(" ");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);

            int[][] drawing = new int[n][m];
            for (int i = 0; i < n; i++) {
                int[] nums = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                System.arraycopy(nums, 0, drawing[i], 0, m);
            }

            int[] result = solve(n, m, drawing);
            System.out.println(result[0]);
            System.out.println(result[1]);
        }
    }
}
