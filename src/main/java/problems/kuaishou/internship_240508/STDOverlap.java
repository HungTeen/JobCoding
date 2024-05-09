package main.java.problems.kuaishou.internship_240508;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/5/10 00:17
 **/
public class STDOverlap {

    // 使用一个二维数组作为网格来标记所有的矩形区域
    private static final int GRID_SIZE = 10000; // 足够大的网格大小
    private static final int OFFSET = GRID_SIZE / 2; // 偏移值，用于处理负坐标

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE]; // 初始化网格

        // 标记所有矩形的范围
        for (int i = 0; i < n; i++) {
            int x1 = scanner.nextInt() + OFFSET;
            int y1 = scanner.nextInt() + OFFSET;
            int x2 = scanner.nextInt() + OFFSET;
            int y2 = scanner.nextInt() + OFFSET;

            // 填充网格的矩形区域
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    grid[x][y] = true; // 标记被占用的区域
                }
            }
        }

        // 用于记录上下左右方向
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        int perimeter = 0;

        // 遍历整个网格，计算暴露的边
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                if (grid[x][y]) { // 如果当前块被标记
                    for (int[] direction : directions) {
                        int nx = x + direction[0];
                        int ny = y + direction[1];

                        // 判断相邻块是否为空
                        if (nx < 0 || nx >= GRID_SIZE || ny < 0 || ny >= GRID_SIZE || !grid[nx][ny]) {
                            // 这是一个暴露的边
                            perimeter++;
                        }
                    }
                }
            }
        }

        System.out.println(perimeter);
    }
}
