package entities;

import java.util.Objects;

public class Grass extends Entity {
    private final int nutritionValue;

    public Grass() {
        nutritionValue = 10;
    }

    public Grass(int nutritionValue) {
        this.nutritionValue = nutritionValue;
    }

    public int getNutritionValue() {
        return nutritionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Grass grass = (Grass) o;
        return nutritionValue == grass.nutritionValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nutritionValue);
    }

    @Override
    public String getSprite() {
        return "\uD83C\uDF31";
    }
}
