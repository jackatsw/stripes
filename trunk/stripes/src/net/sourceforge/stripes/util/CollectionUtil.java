/* Copyright 2005-2006 Tim Fennell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sourceforge.stripes.util;

/**
 * Utility methods for working with Collections and Arrays.
 *
 * @author Tim Fennell
 * @since Stripes 1.4
 */
public class CollectionUtil {
    /**
     * Checks to see if an array contains an item. Works on unsorted arrays. If the array is
     * null this method will always return false.  If the item is null, will return true if the
     * array contains a null entry, false otherwise.  In all other cases, item.equals() is used
     * to determine equality.
     *
     * @param arr the array to scan for the item.
     * @param item the item to be looked for
     * @return true if item is contained in the array, false otherwise
     */
    public static boolean contains(Object[] arr, Object item) {
        if (arr == null) return false;

        for (int i=0; i<arr.length; ++i) {
            if (item == null && arr[i] == null) return true;
            if (item != null && item.equals(arr[i])) return true;
        }

        return false;
    }

    /**
     * Checks to see if the array contains any values that are non-null non empty-string values.
     * If it does, returns false.  Returns true for null arrays and zero length arrays, as well
     * as for arrays consisting only of nulls and empty strings.
     */
    public static boolean empty(String[] arr) {
        if (arr == null || arr.length == 0) return true;
        for (String s : arr) {
            if (s != null && !"".equals(s)) return false;
        }

        return true;
    }

    /**
     * <p>Checks to see if an event is applicable given an array of event names. The array is
     * usually derived from the <tt>on</tt> attribute of one of the Stripes annotations
     * (e.g. {@link net.sourceforge.stripes.validation.ValidationMethod}). The array can
     * be composed of <i>positive</i> event names (e.g. {"foo", "bar"}) in which case the event
     * must be contained in the array, or negative event names (e.g. {"!splat", "!whee"}) in
     * which case the event must not be contained in the array.</p>
     *
     * <p>Calling this method with a null or zero length array will always return true.</p>
     *
     * @param events an array containing event names or event names prefixed with bangs
     * @param event the event name to check for applicability given the array
     * @return true if the array indicates the event is applicable, false otherwise
     */
    public static boolean applies(String events[], String event) {
        if (events == null || events.length == 0) return true;
        boolean isPositive = events[0].charAt(0) != '!';

        if (isPositive) return contains(events, event);
        else return !contains(events, "!" + event);
    }
}
