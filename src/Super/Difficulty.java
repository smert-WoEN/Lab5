package Super;

public enum Difficulty {
    VERY_EASY("veryEasy"),
    EASY("easy"),
    HARD("hard"),
    HOPELESS("hopeless"),
    TERRIBLE("terrible");

    public final String label;

    Difficulty(String label) {
        this.label = label;
    }
}
