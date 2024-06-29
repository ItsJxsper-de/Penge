package dev.itsjxsper.pengeapi.mariadb;

public enum MariadbDataType {
    CHAR(255),
    INT(255),
    BOOLEAN;

    private final long size;

    MariadbDataType() {
        this.size = -1;
    }

    MariadbDataType(int size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public String toDB() {
        if (size > 0)
            return this.name().toUpperCase() + "(" + size + ")";
        return this.name().toUpperCase();
    }
}
