package Project;

public class PlayfairEngine extends EncryptionEngine {
    private final char[][] matrix;
    private final String key;

    public PlayfairEngine() {
        this.key = "KEYWORD";
        this.matrix = generateMatrix(key);
    }

    @Override
    public String encrypt(String data) {
        return processText(data, true);
    }

    @Override
    public String decrypt(String data) {
        return processText(data, false);
    }

    private String processText(String input, boolean encrypt) {
        input = input.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            char a = input.charAt(i);
            char b = (i + 1 < input.length()) ? input.charAt(i + 1) : 'X';

            if (a == b) b = 'X';

            int[] posA = findChar(matrix, a);
            int[] posB = findChar(matrix, b);

            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + (encrypt ? 1 : 4)) % 5]);
                result.append(matrix[posB[0]][(posB[1] + (encrypt ? 1 : 4)) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + (encrypt ? 1 : 4)) % 5][posA[1]]);
                result.append(matrix[(posB[0] + (encrypt ? 1 : 4)) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }

        return result.toString();
    }

    private int[] findChar(char[][] matrix, char c) {
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                if (matrix[row][col] == c)
                    return new int[]{row, col};
        return null;
    }

    private char[][] generateMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();
        for (char c : key.toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1)
                sb.append(c);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (sb.indexOf(String.valueOf(c)) == -1)
                sb.append(c);
        }

        char[][] matrix = new char[5][5];
        for (int i = 0; i < 25; i++)
            matrix[i / 5][i % 5] = sb.charAt(i);
        return matrix;
    }
}