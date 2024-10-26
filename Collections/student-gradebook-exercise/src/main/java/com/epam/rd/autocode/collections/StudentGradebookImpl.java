package com.epam.rd.autocode.collections;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class StudentGradebookImpl implements StudentGradebook {

    private final TreeMap<Student, Map<String, BigDecimal>> gradebook;
    private final Comparator<Student> studentComparator;

    public StudentGradebookImpl() {
        this.studentComparator = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int lastNameCompare = s1.getLastName().compareTo(s2.getLastName());
                if (lastNameCompare != 0) return lastNameCompare;
                int firstNameCompare = s1.getFirstName().compareTo(s2.getFirstName());
                if (firstNameCompare != 0) return firstNameCompare;
                return s1.getGroup().compareTo(s2.getGroup());
            }
        };
        this.gradebook = new TreeMap<>(studentComparator);
    }

    @Override
    public boolean addEntryOfStudent(Student student, String discipline, BigDecimal grade) {
        if (student == null || discipline == null || grade == null) {
            throw new NullPointerException("None of the parameters must be null.");
        }
        if (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(BigDecimal.valueOf(5)) > 0) {
            throw new IllegalArgumentException("Grade must be between 0 and 5.");
        }

        Map<String, BigDecimal> disciplines = gradebook.computeIfAbsent(student, k -> new HashMap<>());
        if (disciplines.containsKey(discipline)) {
            return false;
        }

        disciplines.put(discipline, grade);
        return true;
    }

    @Override
    public int size() {
        return gradebook.size();
    }

    @Override
    public Comparator<Student> getComparator() {
        return studentComparator;
    }

    @Override
    public List<String> getStudentsByDiscipline(String discipline) {
        if (discipline == null) {
            throw new NullPointerException("Discipline must not be null.");
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<Student, Map<String, BigDecimal>> entry : gradebook.entrySet()) {
            Student student = entry.getKey();
            Map<String, BigDecimal> disciplines = entry.getValue();
            if (disciplines.containsKey(discipline)) {
                BigDecimal grade = disciplines.get(discipline);
                result.add(student.getFirstName() + "_" + student.getLastName() + ": " + grade.toPlainString());
            }
        }
        return result;
    }

    @Override
    public Map<Student, Map<String, BigDecimal>> removeStudentsByGrade(BigDecimal grade) {
        if (grade == null) {
            throw new NullPointerException("Grade must not be null.");
        }
        if (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(BigDecimal.valueOf(5)) > 0) {
            throw new IllegalArgumentException("Grade must be between 0 and 5.");
        }

        Map<Student, Map<String, BigDecimal>> removedStudents = new TreeMap<>(studentComparator);
        Iterator<Map.Entry<Student, Map<String, BigDecimal>>> iterator = gradebook.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Student, Map<String, BigDecimal>> entry = iterator.next();
            Student student = entry.getKey();
            Map<String, BigDecimal> disciplines = entry.getValue();
            boolean hasLowGrade = false;

            for (BigDecimal studentGrade : disciplines.values()) {
                if (studentGrade.compareTo(grade) < 0) {
                    hasLowGrade = true;
                    break;
                }
            }

            if (hasLowGrade) {
                removedStudents.put(student, disciplines);
                iterator.remove();
            }
        }

        return removedStudents;
    }

    @Override
    public Map<BigDecimal, List<Student>> getAndSortAllStudents() {
        Map<BigDecimal, List<Student>> sortedByAverageGrade = new TreeMap<>();

        for (Map.Entry<Student, Map<String, BigDecimal>> entry : gradebook.entrySet()) {
            Student student = entry.getKey();
            Map<String, BigDecimal> disciplines = entry.getValue();
            if (disciplines.isEmpty()) continue;

            BigDecimal total = BigDecimal.ZERO;
            int count = 0;

            for (BigDecimal grade : disciplines.values()) {
                total = total.add(grade);
                count++;
            }

            BigDecimal average = total.divide(BigDecimal.valueOf(count), RoundingMode.HALF_UP);

            sortedByAverageGrade
                .computeIfAbsent(average, k -> new ArrayList<>())
                .add(student);
        }

        return sortedByAverageGrade;
    }
}
