package ru.javawebinar.topjava.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealTo {
    private Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

//    private final AtomicBoolean exceed;      // or Boolean[1],  filteredByAtomic
//    private final Boolean exceed;            // filteredByReflection
//    private final Supplier<Boolean> exceed;  // filteredByClosure
    private boolean exceed;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this(null, dateTime, description, calories, exceed);
    }

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public Boolean getExceed() {
        return exceed;
    }

    // for filteredBySetterRecursion
    public void setExceed(boolean exceed) {
        this.exceed = exceed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
