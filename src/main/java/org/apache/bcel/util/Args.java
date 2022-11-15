/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.bcel.util;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.ClassFormatException;

/**
 * Argument validation.
 *
 * @since 6.6.2
 */
public class Args {

    /**
     * Requires a non-0 value.
     *
     * @param value   The value to test.
     * @param message The message prefix
     * @return The value to test.
     */
    public static int require0(final int value, final String message) {
        if (value != 0) {
            throw new ClassFormatException(String.format("%s [Value must be 0: %,d]", message, value));
        }
        return value;
    }

    /**
     * Requires a u2 value of at least {@code min} and not above {@code max}.
     *
     * @param value   The value to test.
     * @param min     The minimum required u2 value.
     * @param max     The maximum required u2 value.
     * @param message The message prefix
     * @return The value to test.
     */
    public static int requireU2(final int value, final int min, final int max, final String message) {
        if (max > Const.MAX_SHORT) {
            throw new IllegalArgumentException(String.format("Programming error: %,d > %,d", max, Const.MAX_SHORT));
        }
        if (min < 0) {
            throw new IllegalArgumentException(String.format("Programming error: %,d < 0", min));
        }
        if (value < min || value > max) {
            throw new ClassFormatException(String.format("%s [Value out of range (%,d - %,d) for type u2: %,d]", message, min, Const.MAX_SHORT, value));
        }
        return value;
    }

    /**
     * Requires a u2 value of at least {@code min}.
     *
     * @param value   The value to test.
     * @param min     The minimum required value.
     * @param message The message prefix
     * @return The value to test.
     */
    public static int requireU2(final int value, final int min, final String message) {
        return requireU2(value, 0, Const.MAX_SHORT, message);
    }

    /**
     * Requires a u2 value.
     *
     * @param value   The value to test.
     * @param message The message prefix
     * @return The value to test.
     */
    public static int requireU2(final int value, final String message) {
        return requireU2(value, 0, message);
    }
}