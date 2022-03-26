package animals;

public class Cat extends AbstractAnimal{
    private String AnimalSound = "Meow"; //заданный животному звук
    private String AnimalType = "Cat";  //заданный тип животного

    public Cat(String sound) {
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
