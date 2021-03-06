package org.pepsoft.worldpainter.importing;

import org.pepsoft.util.ProgressReceiver;
import org.pepsoft.worldpainter.World2;

import java.io.IOException;

public abstract class MapImporter {
    /**
     * Import the map, returning it as a {@link World2} object.
     *
     * @return The imported map as a {@code World2} object.
     * @throws IOException If an I/O error occurred importing the map.
     */
    public World2 doImport() throws IOException {
        try {
            return doImport(null);
        } catch (ProgressReceiver.OperationCancelled e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Import the map, optionally reporting progress to the specified progress
     * receiver and returning it as a {@link World2} object.
     *
     * @param progressReceiver The progress receiver to which to report
     *                         progress.
     * @return The imported map as a {@code World2} object.
     * @throws IOException If an I/O error occurred importing the map.
     * @throws ProgressReceiver.OperationCancelled If the progress receiver
     * threw an {@link ProgressReceiver.OperationCancelled} exception.
     */
    public abstract World2 doImport(ProgressReceiver progressReceiver) throws IOException, ProgressReceiver.OperationCancelled;

    /**
     * Get the warnings generated by the import, or {@code null} if there
     * were none. Multiple warnings should be on separate lines separated by
     * line separators ({@code \n}).
     *
     * @return The warnings generated by the import, or {@code null} if
     * there were none.
     */
    public abstract String getWarnings();

    public enum ReadOnlyOption {
        NONE, MAN_MADE, MAN_MADE_ABOVE_GROUND, ALL
    }
}