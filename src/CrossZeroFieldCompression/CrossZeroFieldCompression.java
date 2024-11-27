package CrossZeroFieldCompression;

/**
 * Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3],
 * и представляют собой, например, состояния ячеек поля для игры в крестики нолики,
 * где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом, 3 – резервное значение.
 * Такое предположение позволит хранить в одном числе типа int всё поле 3х3. Записать в файл 9 значений так,
 * чтобы они заняли три байта. Данная промежуточная аттестация оценивается по системе "зачет" / "не зачет"
 */


import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CrossZeroFieldCompression {
    public static void main(String[] args) throws IOException {
        // Исходные данные: массив из 9 элементов
        int[] field = {0, 1, 2, 3, 0, 1, 2, 3, 0};

        // Упаковываем элементы в три байта
        int packedValue = packField(field);

        // Сохранение упакованного значения в файл
        saveToFile(packedValue, "field.bin");

        System.out.println("Поле успешно сохранено в файл.");
    }

    private static int packField(int[] field) {
        if (field.length != 9) {
            throw new IllegalArgumentException("Размер массива должен быть 9!");
        }

        int result = 0;
        for (int i = 0; i < 9; ++i) {
            if (field[i] < 0 || field[i] > 3) {
                throw new IllegalArgumentException("Значение должно быть в диапазоне от 0 до 3!");
            }
            result |= (field[i] & 0b11) << (i * 2);
        }
        return result;
    }

    private static void saveToFile(int value, String fileName) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))) {
            out.writeInt(value);
        }
    }
}