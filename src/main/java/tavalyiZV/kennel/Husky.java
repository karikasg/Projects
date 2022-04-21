package tavalyiZV.kennel;

public class Husky extends Dog {

    public Husky(String name) {
        super(name);
    }

    @Override
    public void feed() {
        this.increaseHappiness(4);
    }

    @Override
    public void play(int hours) {
        this.increaseHappiness(hours*3);
    }
}
