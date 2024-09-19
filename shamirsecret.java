import java.util.HashMap;
import java.util.Map;

public class ShamirSecretSharing {

    public static void main(String[] args) {
        int[][] roots = {
            {1, decodeValue(10, "4")},  
            {2, decodeValue(2, "111")},  
            {3, decodeValue(10, "12")}, 
            {4, decodeValue(4, "213")}   
        };
        int k = 3;
        int[] xValues = new int[k];
        int[] yValues = new int[k];
        for (int i = 0; i < k; i++) {
            xValues[i] = roots[i][0];
            yValues[i] = roots[i][1];
        }
        int c = (int) gaussianElimination(xValues, yValues, k);
        System.out.printf("The constant term c is: %d%n", c);
    }
    private static int decodeValue(int base, String value) {
        return Integer.parseInt(value, base);
    }
    private static double gaussianElimination(int[] xValues, int[] yValues, int k) {
        double[][] matrix = new double[k][k + 1];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                matrix[i][j] = Math.pow(xValues[i], j);
            }
            matrix[i][k] = yValues[i];
        }
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                double ratio = matrix[j][i] / matrix[i][i];
                for (int l = i; l <= k; l++) {
                    matrix[j][l] -= ratio * matrix[i][l];
                }
            }
        }
        double[] coefficients = new double[k];
        for (int i = k - 1; i >= 0; i--) {
            coefficients[i] = matrix[i][k];
            for (int j = i + 1; j < k; j++) {
                coefficients[i] -= matrix[i][j] * coefficients[j];
            }
            coefficients[i] /= matrix[i][i];
        }
        return coefficients[0];
    }
}
