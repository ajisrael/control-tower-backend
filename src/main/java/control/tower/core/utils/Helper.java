package control.tower.core.utils;

public class Helper {

    private Helper() {
        throw new IllegalStateException("Utility class");
    }
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    // TODO: Write a helper method to match skus on regex to keep consistent format
    //       in corresponding validation method.

}
