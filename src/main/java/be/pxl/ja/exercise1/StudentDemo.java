package be.pxl.ja.exercise1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDemo {
    public static void main(String[] args) {
        List<Student> studentList = StudentDao.createStudents();

        System.out.println("Oefening 1");
        studentList.stream()
                .filter(s -> s.getDateOfBirth().withYear(LocalDate.now().getYear()).equals(LocalDate.now()))
                .forEach(s -> System.out.println(s.getName()));

        System.out.println("Oefening 2");
        studentList.stream()
                .filter(s -> s.getName().toLowerCase().equals("carol"))
                .forEach(s -> System.out.println(s.getName() + " " + s.getDateOfBirth() + " " + s.getGraduationYear() + " " + s.getScore()));

        System.out.println("Oefening 3");
        long count = studentList.stream()
                .filter(s -> s.getGraduationYear() == 2017)
                .count();
        System.out.println(count);

        System.out.println("Oefening 4");
        studentList.stream()
                .sorted((student1, student2) -> student2.getScore() - student1.getScore())
                .limit(1)
                .forEach(s -> System.out.println(s.getScore() + " " + s.getName()));

        System.out.println("Oefening 5");
        studentList.stream()
                .sorted(Comparator.comparing(Student::getDateOfBirth))
                .limit(1)
                .forEach(s -> System.out.println(s.getName() + " " + ChronoUnit.YEARS.between(s.getDateOfBirth(), LocalDate.now())));

        System.out.println("Oefening 6");
        String names = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
        System.out.println(names);

        System.out.println("Oefening 7");
        studentList.stream()
                .filter(s -> s.getGraduationYear() == 2018)
                .sorted((student1, student2) -> student2.getDateOfBirth().compareTo(student1.getDateOfBirth()))
                .limit(1)
                .forEach(s -> System.out.println(s.getName() + " " + s.getGraduationYear() + " " + ChronoUnit.YEARS.between(s.getDateOfBirth(), LocalDate.now())));

        System.out.println("Oefening 8");
        OptionalDouble average2017 = studentList.stream()
                .filter(s -> s.getGraduationYear() == 2017)
                .mapToDouble(Student::getScore)
                .average();
        OptionalDouble average2018 = studentList.stream()
                .filter(s -> s.getGraduationYear() == 2018)
                .mapToDouble(Student::getScore)
                .average();
        Stream.of(average2017, average2018)
                .forEach(s -> System.out.println(s.getAsDouble()));

        System.out.println("Oefening 9");
        studentList.stream()
                .sorted(Comparator.comparing(Student::getGraduationYear))
                .sorted((student1, student2) -> student2.getScore() - student1.getScore())
                .forEach(s -> System.out.println(s.getName() + " " + s.getGraduationYear() + " " + s.getScore()));
   }
}
