package org.sadovodBase;



import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println();

            System.out.println("         МЕНЮ ЗАДАЧ");

            System.out.println("1");
            System.out.println("2");
            System.out.println("3");
            System.out.println("4");
            System.out.println("5");
            System.out.println("6");
            System.out.println("0");


            System.out.print("Введите номер задачи: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Программа завершена.");
                break;
            }

            if (choice == 1) {
                task1(scanner);
            } else if (choice == 2) {
                task2(scanner);
            } else if (choice == 3) {
                task3(scanner);
            } else if (choice == 4) {
                task4(scanner);
            } else if (choice == 5) {
                task5(scanner);
            } else if (choice == 6) {
                task6(scanner);
            } else {
                System.out.println("Такой задачи нет.");
            }

            System.out.println();
            System.out.println("Нажмите Enter, чтобы продолжить");
            scanner.nextLine();
            scanner.nextLine();
        }

        scanner.close();
    }



    static void task1(Scanner scanner) {

        System.out.println();
        System.out.println(" ЗАДАЧА 1 ");
        System.out.println("Поиск позиции вставки");
        System.out.println();

        System.out.print("Введите n и target: ");

        int n = scanner.nextInt();
        int target = scanner.nextInt();

        int[] nums = new int[n];

        System.out.println("Введите " + n + " элементов массива:");

        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int left = 0;
        int right = n;

        while (left < right) {

            int mid = (left + right) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println();
        System.out.println("Первая позиция для вставки: " + left);
    }



    static void task2(Scanner scanner) {

        System.out.println();
        System.out.println("===== ЗАДАЧА 2 =====");
        System.out.println("Сумма на отрезке");
        System.out.println();

        System.out.print("Введите количество элементов n: ");

        int n = scanner.nextInt();

        long[] prefix = new long[n + 1];

        System.out.println("Введите " + n + " элементов:");

        for (int i = 0; i < n; i++) {

            long value = scanner.nextLong();

            prefix[i + 1] = prefix[i] + value;
        }

        System.out.print("Введите количество запросов q: ");

        int q = scanner.nextInt();

        System.out.println();
        System.out.println("Для каждого запроса введите L и R.");

        for (int i = 0; i < q; i++) {

            System.out.print("Запрос " + (i + 1) + ": ");

            int L = scanner.nextInt();
            int R = scanner.nextInt();

            long sum = prefix[R + 1] - prefix[L];

            System.out.println("Сумма: " + sum);
        }
    }


    static void task3(Scanner scanner) {

        System.out.println();
        System.out.println("===== ЗАДАЧА 3 =====");
        System.out.println("Объединение интервалов");
        System.out.println();

        System.out.print("Введите количество интервалов n: ");

        int n = scanner.nextInt();

        if (n == 0) {
            System.out.println("Интервалов нет.");
            return;
        }

        int[][] intervals = new int[n][2];

        System.out.println("Введите интервалы:");

        for (int i = 0; i < n; i++) {

            System.out.print("Интервал " + (i + 1) + ": ");

            intervals[i][0] = scanner.nextInt();
            intervals[i][1] = scanner.nextInt();
        }


        Arrays.sort(intervals, (a, b) ->
                Integer.compare(a[0], b[0])
        );

        int currentStart = intervals[0][0];
        int currentEnd = intervals[0][1];

        System.out.println();
        System.out.println("Объединённые интервалы:");

        for (int i = 1; i < n; i++) {

            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];


            if (nextStart <= currentEnd) {

                currentEnd = Math.max(currentEnd, nextEnd);

            } else {

                System.out.println(
                        "[" + currentStart + ", " + currentEnd + "]"
                );

                currentStart = nextStart;
                currentEnd = nextEnd;
            }
        }


        System.out.println(
                "[" + currentStart + ", " + currentEnd + "]"
        );
    }



    static void task4(Scanner scanner) {

        System.out.println();
        System.out.println("===== ЗАДАЧА 4 =====");
        System.out.println("Вместимость корабля");
        System.out.println();

        System.out.print("Введите n и количество дней: ");

        int n = scanner.nextInt();
        int days = scanner.nextInt();

        long[] weights = new long[n];

        long left = 0;
        long right = 0;

        System.out.println("Введите веса грузов:");

        for (int i = 0; i < n; i++) {

            weights[i] = scanner.nextLong();


            left = Math.max(left, weights[i]);


            right += weights[i];
        }


        while (left < right) {

            long capacity = left + (right - left) / 2;

            int usedDays = 1;
            long currentWeight = 0;

            for (int i = 0; i < n; i++) {

                if (currentWeight + weights[i] <= capacity) {

                    currentWeight += weights[i];

                } else {

                    usedDays++;

                    currentWeight = weights[i];
                }
            }


            if (usedDays <= days) {

                right = capacity;

            } else {


                left = capacity + 1;
            }
        }

        System.out.println();
        System.out.println(
                "Минимальная вместимость корабля: " + left
        );
    }




    static void task5(Scanner scanner) {

        System.out.println();
        System.out.println("===== ЗАДАЧА 5 =====");
        System.out.println("Подмассивы с суммой K");
        System.out.println();

        System.out.print("Введите n и k: ");

        int n = scanner.nextInt();
        long k = scanner.nextLong();

        Map<Long, Long> map = new HashMap<>();


        map.put(0L, 1L);

        long sum = 0;
        long answer = 0;

        System.out.println("Введите элементы массива:");

        for (int i = 0; i < n; i++) {

            long value = scanner.nextLong();

            sum += value;


            long needed = sum - k;

            if (map.containsKey(needed)) {

                answer += map.get(needed);
            }


            map.put(
                    sum,
                    map.getOrDefault(sum, 0L) + 1
            );
        }

        System.out.println();
        System.out.println(
                "Количество подмассивов с суммой " + k + ": "
                        + answer
        );
    }




    static void task6(Scanner scanner) {

        System.out.println();
        System.out.println("===== ЗАДАЧА 6 =====");
        System.out.println("Переговорные комнаты");
        System.out.println();

        System.out.print("Введите количество встреч n: ");

        int n = scanner.nextInt();

        if (n == 0) {
            System.out.println("Нужно 0 комнат.");
            return;
        }

        long[] starts = new long[n];
        long[] ends = new long[n];

        System.out.println("Введите время начала и окончания встреч:");

        for (int i = 0; i < n; i++) {

            System.out.print("Встреча " + (i + 1) + ": ");

            starts[i] = scanner.nextLong();
            ends[i] = scanner.nextLong();
        }


        Arrays.sort(starts);


        Arrays.sort(ends);

        int startIndex = 0;
        int endIndex = 0;

        int rooms = 0;
        int answer = 0;

        while (startIndex < n) {


            if (starts[startIndex] < ends[endIndex]) {

                rooms++;

                answer = Math.max(answer, rooms);

                startIndex++;

            } else {


                rooms--;

                endIndex++;
            }
        }

        System.out.println();
        System.out.println(
                "Минимальное количество комнат: " + answer
        );
    }
}


