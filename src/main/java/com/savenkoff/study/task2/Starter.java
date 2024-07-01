package com.savenkoff.study.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Starter {
    public static void main(String[] args) {
        // Реализуйте удаление из листа всех дубликатов
        List<String> demoList1 = List.of("1","2","3","строка","ещё строка","3","2","1");
        System.out.println("Before demoList1: " + demoList1.stream().reduce((s, s2) -> s + " " + s2).orElse(""));
        demoList1 = demoList1.stream()
                .distinct()
                .toList();
        System.out.println("Result: " + demoList1.stream().reduce((s, s2) -> s + " " + s2).orElse(""));

        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        List<Integer> demoList2 = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        System.out.println("demoList2: " + demoList2.stream().map(integer -> integer.toString()).reduce((s, s2) -> s + " " + s2).orElse(""));
        System.out.println("Result: ");
        System.out.println(
                demoList2.stream()
                        .sorted((o1, o2) -> o2 - o1)
                        .toList()
                        .get(2)
        );

        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
        List<Integer> demoList3 = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        System.out.println("demoList3: " + demoList3.stream().map(integer -> integer.toString()).reduce((s, s2) -> s + " " + s2).orElse(""));
        System.out.println("Result: ");
        System.out.println(
                demoList3.stream()
                        .distinct()
                        .sorted((o1, o2) -> o2 - o1)
                        .toList()
                        .get(2)
        );

        // Имеется список объектов типа Сотрудник (имя, возраст, должность),
        // необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        List<Employee> demoList4 = List.of(
                new Employee("Иван", 45, EmployeePosition.Engineer),
                new Employee("Петр", 33, EmployeePosition.Engineer),
                new Employee("Василий", 18, EmployeePosition.Engineer),
                new Employee("Мария", 21, EmployeePosition.StreetCleaner), // ;)
                new Employee("Руслан", 50, EmployeePosition.Engineer)
        );
        System.out.println("demoList4: ");
        demoList4.stream().forEach(System.out::println);
        System.out.println("Result: ");
        demoList4.stream()
                .filter(employee -> (employee.position == EmployeePosition.Engineer))
                .sorted(Employee::compareTo)
                .limit(3)
                .forEach(System.out::println);

        // Имеется список объектов типа Сотрудник (имя, возраст, должность),
        // посчитайте средний возраст сотрудников с должностью «Инженер»
        List<Employee> demoList5 = new ArrayList<>(demoList4);
        System.out.println("demoList5: ");
        demoList5.stream().forEach(System.out::println);
        double employeeAgeAvg = demoList5.stream()
                .filter(employee -> (employee.position == EmployeePosition.Engineer))
                .mapToInt(value -> value.age)
                .average()
                .orElseThrow();
        System.out.println("Result: " + employeeAgeAvg);

        //Найдите в списке слов самое длинное
        List<String> demoList6 = List.of("Слово", "Буква", "А", "Ага", "Длинношеее", "Рамамбахарамамбуру", "Ещё", "Одно", "Слово");
        System.out.println("Before demoList6: " + demoList6.stream().reduce((s, s2) -> s + " " + s2).orElse(""));
        System.out.println("Result: " +
                demoList6.stream()
                    .sorted((o1, o2) -> o2.length() - o1.length())
                    .findFirst()
                    .orElseThrow()
        );

        // Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
        // Постройте хеш-мапУ, в которой будут храниться пары: слово - сколько раз оно встречается во входной строке
        String demoString1 = "слово два слова три слова четыре слова 5 слов шесть слов";
        System.out.println("demoString1: " + demoString1);
        HashMap<String,Integer> demoHashMap1 = new HashMap<>();
        Arrays.stream(demoString1.split(" "))
                .forEach(s -> {
                    Integer i = demoHashMap1.containsKey(s) ? demoHashMap1.put(s, demoHashMap1.get(s) + 1) : demoHashMap1.put(s, 1);
                }); // Наверное можно было изящней через .collect(Collectors.toMap), но до меня не дошло, как переиспользовать map в таком случае
        System.out.println("Result: ");
        demoHashMap1.forEach((s, integer) -> System.out.println("\"" + s + "\" встречается " + integer + " раз."));

        // Отпечатайте в консоль строки из списка в порядке увеличения длины слова,
        // если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        String demoString2 = demoString1;
        System.out.println("demoString2: " + demoString1);
        System.out.println("Result: ");
        Arrays.stream(demoString2.split(" "))
                .distinct()
                .sorted((o1, o2) -> {
                    // При одинаковой длине сравниваемых слов используем сортировку "по-умолчанию", по алфавиту
                    // Если заремить эти 2 строки, слова "слова" и "слово" в выводе поменяются местами
                    if (o1.length() == o2.length())
                        return o1.compareTo(o2);
                    return o1.length() - o2.length();
                })
                .forEach(System.out::println);

        // Имеется массив строк, в каждой из которых лежит набор из 5 строк,
        // разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько,
        // получите любое из них
        String[] demoArray = {
                "слово два слова три слова",
                "четыре слова 5 слов шесть",
                "Слово Буква А Ага Длинношеее",
                "Рамамбахарамамбуру Ещё Одно Слово Добавил"
        };
        System.out.println("demoArray: " + Arrays.deepToString(demoArray));
        System.out.println("Result: ");
        Arrays.stream(demoArray)
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted((o1, o2) -> o2.length() - o1.length())
                .limit(1)
                .forEach(System.out::println);

    }
}
