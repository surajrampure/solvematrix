import java.util.*;

public class SolveMatrix {
    public static void main (String [] args) {

        int[][] matrix = new int[5][5];
        matrix[0] = new int[]{0, 0, 0, 1, 8};
        matrix[1] = new int[]{1, 1, 1, 1, 4};
        matrix[2] = new int[]{8, 4, 2, 1, 0};
        matrix[3] = new int[]{5, 9, 3, 1, 9};
        matrix[4] = new int[]{9, 5, 4, 1, 1};
        int[] ans = new int[]{0, 7, 0, 6, 7};
        float[] coefs = cramers(matrix, ans);
        System.out.println(Arrays.toString(coefs));

    }
    public static float[] cramers(int[][] matrix, int[] anscolumn) {
        /*
        int[][] matrix2 = matrix;
        int degree = matrix.length;
        float[] coefs = new float[degree];
        int det_coef = findDet(matrix);
        for (int i = 0; i < degree; i++) {
            coefs[i] = (float) findDet(replaceC(matrix2, anscolumn, i))/det_coef;
        }

        return coefs;
        */
        int[][] matrix2 = cloneArray(matrix);
        int degree = matrix.length;
        float[] coefs = new float[degree];
        int coef_det = findDet(matrix2);
        for (int i = 0; i < degree; i ++) {
            coefs[i] = (float) findDet(replaceC(matrix2, anscolumn, i))/coef_det;
        }
        return coefs;
    }

    public static int[][] replaceC (int[][] matrix, int[] newc, int c) {
        int degree = matrix.length;
        int[][] new_matrix = cloneArray(matrix);
        for (int i = 0; i < degree; i ++) {
            new_matrix[i][c] = newc[i];
        }
        return new_matrix;
    }

    public static int findDet (int [][] matrix) {
        int deg = matrix.length;
        if (deg == 2) {
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }
        else {
            int[] vals = new int[deg];
            for (int i = 0; i < deg; i++) {
                vals[i] = findDet(removeRC(matrix, 0, i));
            }
            for (int i = 0; i < deg; i++) {
                vals[i] *= matrix[0][i]*(int)Math.pow(-1, i);
            }
            return sum(vals);
        }
    }

    public static int sum (int[] vals) {
        int v = 0;
        for (int i: vals) {
            v += i;
        }
        return v;
    }

    // Returns a duplicated version of a given array
    public static int[][] cloneArray(int[][] src) {
        int length = src.length;
        int[][] target = new int[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public static int[][] removeRC (int [][] matrix, int r, int c) {
        int deg = matrix.length;
        int[][] new_matrix = new int[deg-1][deg-1];
        for (int i = 0; i < deg; i ++) {
            if (i < r) {
                for (int j = 0; j < deg; j ++) {
                    if (j < c) {
                        new_matrix[i][j] = matrix[i][j];
                    }
                    if (j > c) {
                        new_matrix[i][j-1] = matrix[i][j];
                    }
                }
            }

            if (i > r) {
                for (int j = 0; j < deg; j ++) {
                    if (j < c) {
                        new_matrix[i-1][j] = matrix[i][j];
                    }

                    if (j > c) {
                        new_matrix[i-1][j-1] = matrix[i][j];
                    }
                }
            }
        }
        return new_matrix;
    }
}