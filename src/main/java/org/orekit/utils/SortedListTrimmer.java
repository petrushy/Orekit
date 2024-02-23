/* Contributed in the public domain.
 * Licensed to CS GROUP (CS) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * CS licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.orekit.utils;

import org.hipparchus.exception.LocalizedCoreFormats;
import org.hipparchus.util.FastMath;
import org.orekit.errors.OrekitException;
import org.orekit.errors.OrekitIllegalArgumentException;
import org.orekit.errors.OrekitMessages;
import org.orekit.errors.TimeStampedCacheException;
import org.orekit.time.AbsoluteDate;
import org.orekit.time.TimeStamped;

import java.util.List;

/** A trimmer for externally stored chronologically sorted lists.
 *
 * @author Evan Ward
 * @since 12.1
 */
public class SortedListTrimmer {

    /** Size of the list to return from {@link #getNeighborsSubList(AbsoluteDate, List)}. */
    private final int neighborsSize;

    /** Create a new trimmer with the given neighbors size.
     * @param neighborsSize size of the list returned from {@link #getNeighborsSubList(AbsoluteDate, List)}
     */
    public SortedListTrimmer(final int neighborsSize) {
        if (neighborsSize < 1) {
            throw new OrekitIllegalArgumentException(LocalizedCoreFormats.NUMBER_TOO_SMALL,
                                                     neighborsSize, 1);
        }
        // assign instance variables
        this.neighborsSize = neighborsSize;
    }

    /** Get size of the list returned from {@link #getNeighborsSubList(AbsoluteDate, List)}.
     * @return size of the list returned from {@link #getNeighborsSubList(AbsoluteDate, List)}
     */
    public int getNeighborsSize() {
        return neighborsSize;
    }

    /** Get the entries surrounding a central date.
     * <p>
     * If the central date is well within covered range, the returned array will
     * be balanced with half the points before central date and half the points
     * after it (depending on n parity, of course). If the central date is near
     * the boundary, then the returned array will be unbalanced and will contain
     * only the n earliest (or latest) entries. A typical example of the later
     * case is leap seconds cache, since the number of leap seconds cannot be
     * arbitrarily increased.
     * <p>
     * @param <T>  the type of data
     * @param central central date
     * @param data complete list of entries (must be chronologically sorted)
     * @return entries surrounding the specified date (sublist of {@code data})
     */
    public <T extends TimeStamped> List<T> getNeighborsSubList(final AbsoluteDate central, final List<T> data) {

        if (neighborsSize > data.size()) {
            throw new OrekitException(OrekitMessages.NOT_ENOUGH_DATA, data.size());
        }

        // find central index
        final int i = findIndex(central, data);

        // check index in in the range of the data
        if (i < 0) {
            final AbsoluteDate earliest = data.get(0).getDate();
            throw new TimeStampedCacheException(OrekitMessages.UNABLE_TO_GENERATE_NEW_DATA_BEFORE,
                                                earliest, central, earliest.durationFrom(central));
        } else if (i >= data.size()) {
            final AbsoluteDate latest = data.get(data.size() - 1).getDate();
            throw new TimeStampedCacheException(OrekitMessages.UNABLE_TO_GENERATE_NEW_DATA_AFTER,
                                                latest, central, central.durationFrom(latest));
        }

        // force unbalanced range if necessary
        int start = FastMath.max(0, i - (neighborsSize - 1) / 2);
        final int end = FastMath.min(data.size(), start + neighborsSize);
        start = end - neighborsSize;

        // return list without copying
        return data.subList(start, end);

    }

    /**
     * Find the index, i, to {@code data} such that {@code data[i] <= t} and
     * {@code data[i+1] > t} if {@code data[i+1]} exists.
     *
     * @param <T>  the type of data
     * @param t the time
     * @param data complete list of entries (must be chronologically sorted)
     * @return the index of the data at or just before {@code t}, {@code -1} if
     *         {@code t} is before the first entry, or {@code data.size()} if
     *         {@code t} is after the last entry.
     */
    private <T extends TimeStamped> int findIndex(final AbsoluteDate t, final List<T> data) {

        // left bracket of search algorithm
        int    iInf  = 0;
        double dtInf = t.durationFrom(data.get(0));
        if (dtInf < 0) {
            // before first entry
            return -1;
        }

        // right bracket of search algorithm
        int    iSup  = data.size() - 1;
        double dtSup = t.durationFrom(data.get(data.size() - 1));
        if (dtSup > 0) {
            // after last entry
            return data.size();
        }

        // search entries, using linear interpolation
        // this should take only 2 iterations for near linear entries (most frequent use case)
        // regardless of the number of entries
        // this is much faster than binary search for large number of entries
        while (iSup - iInf > 1) {
            final int    iInterp = (int) FastMath.rint((iInf * dtSup - iSup * dtInf) / (dtSup - dtInf));
            final int    iMed    = FastMath.max(iInf + 1, FastMath.min(iInterp, iSup - 1));
            final double dtMed   = t.durationFrom(data.get(iMed).getDate());
            if (dtMed < 0) {
                iSup  = iMed;
                dtSup = dtMed;
            } else {
                iInf  = iMed;
                dtInf = dtMed;
            }
        }

        // at this point data[iInf] <= t <= data[iSup], but the javadoc for this method
        // says the upper bound is exclusive, so check for equality to make a half open
        // interval.
        if (dtSup == 0.0) {
            return iSup;
        }

        return iInf;

    }

}
