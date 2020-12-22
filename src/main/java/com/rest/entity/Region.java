package com.rest.entity;


public enum Region {
    USA(0),
    BLR(1),
    RUS(2);

    private int id;

    private Region(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static int returnRegionForQuery(Region region) {
        switch (region) {
            case USA:
                return 0;
            case BLR:
                return 1;
            case RUS:
                return 2;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Region valueOf(int id) {
        for (Region rg : values()) {
            if (rg.id == id) {
                return rg;
            }
        }
        throw new IllegalArgumentException(
                "No enum constant " + Region.class.getCanonicalName() + " with id:" + id);
    }
}
