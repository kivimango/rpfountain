package com.kivimango.core.rpfountain;

import java.io.IOException;
import java.util.List;

public interface ReplayService {

    /**
     * Lists all replays from the local replay directory.
     * @return the list of the replays represented as an instance of a Replay class with the parsed info
     * @throws IOException on any error during reading
     */
    List<Replay>    listLocal() throws IOException;

    /**
     * Counts the number of the replay files in the replay directory.
     * @return the count of replay files
     * @throws IOException on error
     */
    Long            countReplays() throws IOException;

    /**
     * Counts the size of the replay directory on the hard drive.
     * @return the size in bytes
     * @throws IOException on error
     */
    Long            size() throws IOException;
}
