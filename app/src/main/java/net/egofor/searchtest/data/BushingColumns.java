package net.egofor.searchtest.data;


import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;


public class BushingColumns {
    @DataType(DataType.Type.INTEGER) @PrimaryKey @AutoIncrement
    public static final String _ID = "_id";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String UPC = "upc";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String SHORT_DESCRIPTION = "short_description";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String MFG = "mfg";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String MFG_PART_NUMBER = "mfg_part_number";

    @DataType(DataType.Type.TEXT)
    public static final String TYPE = "type";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String INSULATED = "insulated";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String MATERIAL = "material";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String TRADE_SIZE = "trade_size";

    @DataType(DataType.Type.TEXT) @NotNull
    public static final String IMAGE = "image";
}
