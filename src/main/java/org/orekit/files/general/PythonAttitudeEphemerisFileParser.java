package org.orekit.files.general;

import java.io.BufferedReader;
import java.io.IOException;

public class PythonAttitudeEphemerisFileParser implements AttitudeEphemerisFileParser {
    /**
     * Parse an attitude ephemeris file from a stream.
     *
     * @param reader   containing the ephemeris file.
     * @param fileName to use in error messages.
     * @return a parsed ephemeris file.
     * @throws IOException if {@code reader} throws one.
     */
    @Override
    public AttitudeEphemerisFile parse(BufferedReader reader, String fileName) throws IOException {
        return null;
    }
/* TODO: IMPLEMENT THIS */

    /**
     * Parse an attitude ephemeris file from a file on the local file system.
     *
     * <p> For Implementors: Most subclasses should implement this method as follows, but
     * there is no default implementation because most subclasses should use a specialized
     * return type.
     *
     * <pre>
     * try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
     *     return parse(reader, fileName);
     * }
     * </pre>
     *
     * @param fileName path to the ephemeris file.
     * @return parsed ephemeris file.
     * @throws IOException if one is thrown while opening or reading from {@code fileName}
     */
    @Override
    public AttitudeEphemerisFile parse(String fileName) throws IOException {
        return null;
    }
}
