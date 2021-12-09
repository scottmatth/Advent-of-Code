package day.six;

import java.util.ArrayList;
import java.util.List;

public class LanternFish {
    private Integer daysTillSpawn;
//    List<LanternFish> children = new ArrayList<>();

    public LanternFish(int initialYears) {
        this.daysTillSpawn = initialYears;
    }

    public int getDaysTillSpawn() {
        return daysTillSpawn;
    }

//    public List<LanternFish> getChildren() {
//        return children;
//    }

    public LanternFish incrementDay() {
//        this.children.forEach(LanternFish::incrementDay);
        LanternFish child = null;
        if (this.daysTillSpawn == 0) {
            this.daysTillSpawn = 6;
            child = new LanternFish(8);
        } else {
            this.daysTillSpawn--;
        }
        return child;
    }

//    public long countFish() {
//
//        Long childrenCount = this.children.stream()
//                .reduce(0L, (integer, lanternFish) -> integer + lanternFish.countFish(), Long::sum);
//        return childrenCount + 1L;
//    }
}
