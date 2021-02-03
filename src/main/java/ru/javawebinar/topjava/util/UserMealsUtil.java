package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetweenHalfOpen;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> overNorm = new ArrayList<>();
        List<UserMealWithExcess> result = new ArrayList<>();
        Map<Integer, Integer> sum = new HashMap<>();

        for (UserMeal userM : meals) {
            sum.merge(userM.getDateTime().getDayOfYear(), userM.getCalories(), (oldVal, newVal) -> oldVal + newVal);

            if (sum.get(userM.getDateTime().getDayOfYear()) > caloriesPerDay) {
                if (isBetweenHalfOpen(userM.getDateTime().toLocalTime(), startTime, endTime)) {
                    overNorm.add(new UserMealWithExcess(
                            userM.getDateTime(),
                            userM.getDescription(),
                            userM.getCalories(),
                            true));
                }
            }
        }

        for (UserMeal userM : meals) {
            for (Map.Entry entry : sum.entrySet()) {
                if (userM.getDateTime().getDayOfYear() == Integer.parseInt(entry.getKey().toString())) {
                    if (isBetweenHalfOpen(userM.getDateTime().toLocalTime(), startTime, endTime)) {
                        if (Integer.parseInt(entry.getValue().toString()) > caloriesPerDay) {
                            result.add(new UserMealWithExcess(
                                    userM.getDateTime(),
                                    userM.getDescription(),
                                    userM.getCalories(),
                                    true));

                        } else {
                            result.add(new UserMealWithExcess(
                                    userM.getDateTime(),
                                    userM.getDescription(),
                                    userM.getCalories(),
                                    false));
                        }
                    }
                }
            }
        }

        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        Map<LocalDate, Integer> sumCalForDay = meals.stream().collect(
                Collectors.groupingBy(user -> user.getDateTime().toLocalDate(),
                        Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(user -> isBetweenHalfOpen(user.getDateTime().toLocalTime(), startTime, endTime))
                .map(user -> new UserMealWithExcess(
                        user.getDateTime(),
                        user.getDescription(),
                        user.getCalories(),
                        sumCalForDay.get(user.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
