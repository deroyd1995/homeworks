package dz6;
import java.util.UUID;

public class marketUUID {
    public String newUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
