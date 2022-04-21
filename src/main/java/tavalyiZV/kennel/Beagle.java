package tavalyiZV.kennel;

public class Beagle extends Dog {

    public Beagle(String name) {
        super(name);
    }

    @Override
    public void feed() {
        this.increaseHappiness(2);
    }

    @Override
    public void play(int hours) {
        this.increaseHappiness(hours*2);
    }
}
