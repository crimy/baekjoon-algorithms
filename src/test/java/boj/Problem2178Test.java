package boj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem2178Test {
    @Test
    void testSolve() {
        assertEquals(15, Problem2178.solve(4, 6, new int[][]{
                {1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1}
        }));
    }
}