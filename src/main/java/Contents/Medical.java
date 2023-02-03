package Contents;

public class Medical extends Item {

    private static String value;

    public static String getValue() {
        return value;
    }

    public static void setValue(String value) {
        Medical.value = value;
    }
}
