package animals;

public class Dog extends AbstractAnimal{
    private String AnimalSound = "Bark"; //заданный животному звук
    private String AnimalType = "Dog";  //заданный тип животного

    public Dog(String sound) {
        super(sound);
    }

    @Override
    public String normalizeSound(String sound){
        return (AnimalType+" doesn't sound like "+sound+" it sounds like "+AnimalSound);
    }

    @Override
    public boolean compareSound(String sound){
        if (sound.compareToIgnoreCase(AnimalSound) == 0){return true;}
        else {return false;}
    }

    public String toString(){
        return AnimalType+" "+sound;
    }
}
