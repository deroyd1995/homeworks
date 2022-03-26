package animals;

public class FairyTale {
    public static void main(String[] args){
        AbstractAnimal[] animals = {
                new Bear("bark"),
                new Cat("meow"),
                new Dog("bark"),
                new Cow("roar")
        };

        for (AbstractAnimal curanimal:animals){
            if (curanimal.compareSound(curanimal.getSound())==false){
                System.out.println(curanimal.normalizeSound(curanimal.getSound()));
            }
            else{
                System.out.println(curanimal);
            }
        }
    }
}
