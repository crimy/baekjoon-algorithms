package boj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem7576Test {
    @Test
    void testSolve() {
        int[][] map = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1}
        };

        int result = Problem7576.solve(4,6, map);
        assertEquals(8, result);
    }

}