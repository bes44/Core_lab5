package ReadCrossZeroField;

///Прочитать числа из файла, полученного в результате выполнения задания 2.

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadCrossZeroField {
    public static void main(String[] args) throws IOException {
        String fileName = "field.bin";

        int packedValue = readFromFile(fileName);
        int[] unpackedField = unpackField(packedValue);

        for (int cell : unpackedField) {
            System.out.print(cell + " ");
        }
    }

    private static int readFromFile(String fileName) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            return in.readInt(); // Читает 4 байта как int
        }
    }

    private static int[] unpackField(int packedValue) {
        int[] field = new int[9];
        for (int i = 0; i < 9; ++i) {
            field[i] = (packedValue >>> (i * 2)) & 0b11;
        }
        return field;
    }
}