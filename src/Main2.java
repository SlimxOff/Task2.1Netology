import java.util.*;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        // Генерация данных
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(random.nextInt(names.size())),
                    families.get(random.nextInt(families.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)])
            );
        }

        // Найти количество несовершеннолетних
        long underageCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + underageCount);

        // Получить список фамилий призывников
        List<String> conscriptFamilies = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .toList();
        System.out.println("Фамилии призывников: " + conscriptFamilies);

        // Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        List<String> potentialWorkers = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60)
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)  // Преобразование в список фамилий
                .collect(Collectors.toList());

        System.out.println("Потенциально работоспособные люди: " + String.join(", ", potentialWorkers));

    }
}
