package animals;

public class Bear extends AbstractAnimal{
    private String AnimalSound = "Rooar"; //заданный животному звук
    private String AnimalType = "Bear";  //заданный тип животного

    public Bear(String sound) {
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
