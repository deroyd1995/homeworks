package animals;

public abstract class AbstractAnimal {

    public String sound;

    public AbstractAnimal(String sound) {
        this.sound = sound;
    }

    public String getSound(){
        return sound;
    }
    public abstract boolean compareSound(String sound);
    public abstract String normalizeSound(String sound);

}