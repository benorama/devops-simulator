package benorama.devops.simulator

public enum Pipeline {

    AUTO, MANUAL, SEMI

    static boolean contains(String value) {
        for (Pipeline pipeline : values()) {
            if (pipeline.name() == value.toUpperCase()) {
                return true
            }
        }
        return false
    }

}