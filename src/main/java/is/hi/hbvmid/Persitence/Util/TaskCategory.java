package is.hi.hbvmid.Persitence.Util;

public enum TaskCategory {
    HOUSEHOLD("Household chores"),
    SPORTS("Training and Competition"),
    SCHOOL("Schoolwork"),
    WORK("Work"),
    HOBBIES("Hobbies"),
    SELF_CARE("Self Care"),
    FAMILY("Family"),
    FRIENDS("Friends");

    private final String displayValue;

    TaskCategory(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
