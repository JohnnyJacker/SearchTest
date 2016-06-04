package net.egofor.searchtest.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;


@Database(version = MaterialDatabase.VERSION)

public class MaterialDatabase {
    private MaterialDatabase(){}

    public static final int VERSION = 1;

    @Table(BushingColumns.class) public static final String BUSHINGS = "bushings";
}
