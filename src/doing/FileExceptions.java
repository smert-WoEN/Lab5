package doing;

public class FileExceptions extends IllegalArgumentException{
    public FileExceptions(){
        super();
    }

    public FileExceptions(String message) {
        super(message);
    }

    public FileExceptions(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public FileExceptions(Throwable throwable) {
        super(throwable);
    }
}
