
package boj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Problem1926Test {
    @Test
    void testSolve() {
        int[][] drawing = {
                {1, 1, 0, 1, 1},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1}
        };

        int[] solve = Problem1926.solve(6,5, drawing);
        assertEquals(4, solve[0]);
        assertEquals(9, solve[1]);
    }
}
