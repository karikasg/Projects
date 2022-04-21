package tavalyiZV.kennel;

import java.util.ArrayList;
import java.util.List;

public class Kennel {

    List<Dog> dogs = new ArrayList<>();

    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    public void feedAll() {
        for (Dog dog : dogs) {
            dog.feed();
        }
    }

    public Dog findByName(String name) {
        for (Dog dog : dogs) {
            if (dog.getName().equals(name)) return dog;
        }
        throw new IllegalArgumentException("Nem található "+name+" nevű kutya");
    }

    public void playWith(String name, int hours) {
        for (Dog dog : dogs) {
            if (dog.getName().equals(name)) dog.play(hours);
        }
    }

    public List<String> getHappyDogNames(int minHappiness) {
        return dogs.stream().filter(o -> o.getHappiness()>minHappiness).map(Dog::getName).toList();
    }

    public List<Dog> getDogs() {
        return dogs;
    }
}

