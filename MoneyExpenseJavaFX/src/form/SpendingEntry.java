package form;

import javafx.beans.property.*;

public class SpendingEntry {
    private final IntegerProperty id;      // Added for SpendingTracker
    private final StringProperty date;
    private final StringProperty category;
    private final IntegerProperty amount;

    // Constructor for scenarios with `id`
    public SpendingEntry(int id, String date, String category, int amount) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.category = new SimpleStringProperty(category);
        this.amount = new SimpleIntegerProperty(amount);
    }

    // Constructor for scenarios without `id`
    public SpendingEntry(String date, String category, int amount) {
        this.id = new SimpleIntegerProperty(0); // Default value when `id` is not provided
        this.date = new SimpleStringProperty(date);
        this.category = new SimpleStringProperty(category);
        this.amount = new SimpleIntegerProperty(amount);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public int getId() {
        return id.get();
    }
}
