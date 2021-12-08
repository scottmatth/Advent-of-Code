package day.six;

import java.util.ArrayList;
import java.util.List;

public class LanternFish {
    private Integer daysTillSpawn;
    List<LanternFish> children = new ArrayList<>();

    public LanternFish(int initialYears) {
        this.daysTillSpawn = initialYears;
    }

    public int getDaysTillSpawn() {
        return daysTillSpawn;
    }

    public List<LanternFish> getChildren() {
        return children;
    }

    public void incrementDay() {
        this.children.forEach(LanternFish::incrementDay);
        if (this.daysTillSpawn == 0) {
            this.daysTillSpawn = 6;
            this.children.add(new LanternFish(8));
        } else {
            this.daysTillSpawn--;
        }
    }

    public int countFish() {

        Integer childrenCount = this.children.stream()
                .reduce(0, (integer, lanternFish) -> integer + lanternFish.countFish(), Integer::sum);
        return childrenCount + 1;
    }
}
