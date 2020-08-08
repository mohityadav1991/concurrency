package com.mohit.parallelism;

import java.util.*;
import java.util.stream.Collectors;

public final class EmployeeAnalytics {
    public double averageAgeOfEnrolledEmployeesImperative(
            final Employee[] employeeArray) {
        Long startTime = System.currentTimeMillis();
        List<Employee> activeEmployees = new ArrayList<Employee>();

        for (Employee s : employeeArray) {
            if (s.checkIsCurrent()) {
                activeEmployees.add(s);
            }
        }

        double ageSum = 0.0;
        for (Employee s : activeEmployees) {
            ageSum += s.getAge();
        }

        double result = ageSum / (double) activeEmployees.size();
        Long endTime = System.currentTimeMillis();
        System.out.println("Took : " + (endTime - startTime) + "ms. Result: " + result + " : averageAgeOfEnrolledEmployeesImperative");
        return result;
    }

    /**
     * Computes the average age of all actively enrolled Employees using
     * parallel streams. This should mirror the functionality of
     * averageAgeOfEnrolledEmployeesImperative. This method should not use any
     * loops.
     *
     * @param employeeArray Employee data for the class.
     * @return Average age of enrolled Employees
     */
    public double averageAgeOfEnrolledEmployeesParallelStream(
            final Employee[] employeeArray) {
        List<Employee> employees = Arrays.asList(employeeArray);
        long startTime = System.currentTimeMillis();
        double result = employees.parallelStream()
                .filter(s -> s.checkIsCurrent())
                .mapToDouble(s -> s.getAge())
                .average()
                .getAsDouble();
        long endTime = System.currentTimeMillis();
        System.out.println("Took : " + (endTime - startTime) + "ms. Result: " + result + " : averageAgeOfEnrolledEmployeesParallelStream");
        return result;
    }

    /**
     * Sequentially computes the most common first name out of all Employees that
     * are no longer active in the class using loops.
     *
     * @param employeeArray Employee data for the class.
     * @return Most common first name of inactive Employees
     */
    public static String mostCommonFirstNameOfInactiveEmployeesImperative(
            final Employee[] employeeArray) {
        Long startTime = System.currentTimeMillis();
        List<Employee> inactiveEmployees = new ArrayList<Employee>();

        for (Employee s : employeeArray) {
            if (!s.checkIsCurrent()) {
                inactiveEmployees.add(s);
            }
        }

        Map<String, Integer> nameCounts = new HashMap<String, Integer>();

        for (Employee s : inactiveEmployees) {
            if (nameCounts.containsKey(s.getFirstName())) {
                nameCounts.put(s.getFirstName(),
                        new Integer(nameCounts.get(s.getFirstName()) + 1));
            } else {
                nameCounts.put(s.getFirstName(), 1);
            }
        }

        String mostCommon = null;
        int mostCommonCount = -1;
        for (Map.Entry<String, Integer> entry : nameCounts.entrySet()) {
            if (mostCommon == null || entry.getValue() > mostCommonCount) {
                mostCommon = entry.getKey();
                mostCommonCount = entry.getValue();
            }
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("Took : " + (endTime - startTime) + "ms. Result: " + mostCommon + " : mostCommonFirstNameOfInactiveEmployeesImperative");
        return mostCommon;
    }

    public static String mostCommonFirstNameOfInactiveEmployeesParallelStream(
            final Employee[] employeeArray) {
        Long startTime = System.currentTimeMillis();
        List<Employee> employees = Arrays.asList(employeeArray);
        Map<String, Long> firstNameFrequency = employees.parallelStream()
                .filter(s -> !s.checkIsCurrent())
                .collect(Collectors.groupingBy(Employee::getFirstName, Collectors.counting()));
        String result = firstNameFrequency
                .entrySet()
                .stream()
                .parallel()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
        Long endTime = System.currentTimeMillis();
        System.out.println("Took : " + (endTime - startTime) + "ms. Result: " + result + " : mostCommonFirstNameOfInactiveEmployeesParallelStream");
        return result;
    }

    /**
     * Sequentially computes the number of Employees who have failed the course
     * who are also older than 20 years old. A failing grade is anything below a
     * 65. A Employee has only failed the course if they have a failing grade and
     * they are not currently active.
     *
     * @param employeeArray Employee data for the class.
     * @return Number of failed grades from Employees older than 20 years old.
     */
    public int countNumberOfFailedEmployeesOlderThan20Imperative(
            final Employee[] employeeArray) {
        Long startTime = System.currentTimeMillis();
        int count = 0;
        for (Employee s : employeeArray) {
            if (!s.checkIsCurrent() && s.getAge() > 20 && s.getGrade() < 65) {
                count++;
            }
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("Took : " + (endTime - startTime) + "ms. Result: " + count + " : countNumberOfFailedEmployeesOlderThan20Imperative");
        return count;
    }

    /**
     * Computes the number of Employees who have failed the course who are
     * also older than 20 years old. A failing grade is anything below a 65. A
     * Employee has only failed the course if they have a failing grade and they
     * are not currently active. This should mirror the functionality of
     * countNumberOfFailedEmployeesOlderThan20Imperative. This method should not
     * use any loops.
     *
     * @param employeeArray Employee data for the class.
     * @return Number of failed grades from Employees older than 20 years old.
     */
    public int countNumberOfFailedEmployeesOlderThan20ParallelStream(
            final Employee[] employeeArray) {
        Long startTime = System.currentTimeMillis();
        List<Employee> employees = Arrays.asList(employeeArray);
        int count = Math.toIntExact(employees.parallelStream()
                .filter(s -> !s.checkIsCurrent() && s.getAge() > 20 && s.getGrade() < 65)
                .count());
        Long endTime = System.currentTimeMillis();
        System.out.println("Took : " + (endTime - startTime) + "ms. Result: " + count + " : countNumberOfFailedEmployeesOlderThan20ParallelStream");
        return count;
    }

    // RUNNER CODE
    final static int REPEATS = 10;
    private final static String[] firstNames = {"Sanjay", "Yunming", "John", "Vivek", "Shams", "Mike", "Tyson"};
    private final static String[] lastNames = {"Chatterjee", "Zhang", "Smith", "Sarkar", "Imam", "Grossman"};

    private static Employee[] generateEmployeeData() {
        final int N_EmployeeS = 6000000;
        final int N_CURRENT_EmployeeS = 600000;

        Employee[] employees = new Employee[N_EmployeeS];
        Random r = new Random(123);

        for (int s = 0; s < N_EmployeeS; s++) {
            final String firstName = firstNames[r.nextInt(firstNames.length)];
            final String lastName = lastNames[r.nextInt(lastNames.length)];
            final double age = r.nextDouble() * 100.0;
            final int grade = 1 + r.nextInt(100);
            final boolean current = (s < N_CURRENT_EmployeeS);

            employees[s] = new Employee(firstName, lastName, age, grade, current);
        }

        return employees;
    }

    public static void main(String[] args) {
        final Employee[] employees = generateEmployeeData();
        while(true) {
            mostCommonFirstNameOfInactiveEmployeesImperative(employees);
            mostCommonFirstNameOfInactiveEmployeesParallelStream(employees);
            System.out.println("------------------------------------------------");
        }
    }
}
