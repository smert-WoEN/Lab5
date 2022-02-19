package lagacy.Super;
@Deprecated

public enum Difficulty {
    VERY_EASY("VERY_EASY"),
    EASY("EASY"),
    HARD("HARD"),
    HOPELESS("HOPELESS"),
    TERRIBLE("TERRIBLE");

    private final String lable;

    Difficulty(String label) {
        this.lable = label;
    }

    public String getLable() {
        return lable;
    }
}