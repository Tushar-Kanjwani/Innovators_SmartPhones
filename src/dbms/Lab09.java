package dbms;
class lab9 {
    public static void main(String[] args) {
        int[] digits = {6, 5, 0, 4, 9, 1, 2, 8, 3, 6}; 
        int start = 5;
        String instructions = shortestSequence(digits, start);
        System.out.println(instructions);
    }

    public static String shortestSequence(int[] digits, int start) {
        int[][] keypad = {{7, 8, 9}, {4, 5, 6}, {1, 2, 3}, {0, 0, 0}};
        int[] startPosition = new int[2];
        String result = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (keypad[i][j] == start) {
                    startPosition[0] = i;
                    startPosition[1] = j;
                    break;
                }
            }
        }
        for (int digit : digits) {
            int[] position = new int[2];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (keypad[i][j] == digit) {
                        position[0] = i;
                        position[1] = j;
                        break;
                    }
                }
            }
            while (startPosition[0] != position[0]) {
                if (startPosition[0] < position[0]) {
                    result += "down ";
                    startPosition[0]++;
                } else {
                    result += "up ";
                    startPosition[0]--;
                }
            }
            while (startPosition[1] != position[1]) {
                if (startPosition[1] < position[1]) {
                    result += "right ";
                    startPosition[1]++;
                } else {
                    result += "left ";
                    startPosition[1]--;
                }
            }
            result += "punch ";
        }
        return result;
    }
}