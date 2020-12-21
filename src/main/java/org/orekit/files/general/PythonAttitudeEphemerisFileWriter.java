package org.orekit.files.general;

import java.io.IOException;

public class PythonAttitudeEphemerisFileWriter implements AttitudeEphemerisFileWriter {
    /**
     * Write the passed in {@link AttitudeEphemerisFile} using the passed in
     * {@link Appendable}.
     *
     * @param writer        a configured Appendable to feed with text
     * @param ephemerisFile a populated ephemeris file to serialize into the buffer
     * @throws IOException if any buffer writing operations fail or if the underlying
     *                     format doesn't support a configuration in the EphemerisFile
     *                     (for example having multiple satellites in one file, having
     *                     the origin at an unspecified celestial body, etc.)
     */
    @Override
    public void write(Appendable writer, AttitudeEphemerisFile ephemerisFile) throws IOException {
/* TODO: Implement this */

    }
}
