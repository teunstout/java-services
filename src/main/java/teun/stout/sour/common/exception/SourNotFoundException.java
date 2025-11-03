package teun.stout.sour.common.exception;

public class SourNotFoundException extends RuntimeException {

    public SourNotFoundException(String id) {
        super(String.format("Could not find sour with id '%s'", id));
    }
}
