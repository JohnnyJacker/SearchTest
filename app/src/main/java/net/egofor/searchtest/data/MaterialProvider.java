package net.egofor.searchtest.data;

import android.net.Uri;
import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = MaterialProvider.AUTHORITY, database = MaterialDatabase.class)
public class MaterialProvider {

    public static final String AUTHORITY = "net.egofor.searchtest.data.MaterialProvider";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path{
        String BUSHING = "bushing";
    }

    private static Uri buildUri(String... paths){
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path:paths){
            builder.appendPath(path);
        }
        return builder.build();
    }


    //  TODO  Bushings Table
    @TableEndpoint(table = MaterialDatabase.BUSHINGS)
    public static class Bushings{
        @ContentUri(
                path = Path.BUSHING,
                type = "vnd.android.cursor.dir/bushing"
        )
        public static final Uri CONTENT_URI = buildUri(Path.BUSHING);

        @InexactContentUri(
                name = "bushing",
                path = Path.BUSHING + "/*",
                type = "vnd.android.cursor.item/bushing",
                whereColumn = BushingColumns._ID,
                pathSegment = 1
        )
        public static Uri withSymbol(String symbol){
            return buildUri(Path.BUSHING, symbol);
        }
    }
}
