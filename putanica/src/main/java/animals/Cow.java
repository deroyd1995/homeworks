package animals;

public class Cow extends AbstractAnimal{
    private String AnimalSound = "Moo"; //заданный животному звук
    private String AnimalType = "Cow";  //заданный тип животного

    public Cow(String sound) {
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
