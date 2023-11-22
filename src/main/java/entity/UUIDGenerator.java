package entity;
import java.util.UUID;
public class UUIDGenerator {
    public static int generate(){
        UUID uuid =  UUID.randomUUID();
        return Integer.parseInt(uuid.toString());
        
    }
}
