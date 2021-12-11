package day.six;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class LanternFish {
    private int daysTillSpawn;
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

    public BigInteger countFish() {

        BigInteger childrenCount = this.children.stream()
                .reduce(BigInteger.ZERO, (integer, lanternFish) -> integer.add(lanternFish.countFish()), BigInteger::add);
        return childrenCount.add(BigInteger.ONE);
    }
}
