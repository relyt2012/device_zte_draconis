/*
 * Copyright (C) 2014 The MoKee OpenSource Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cyanogenmod.hardware;

import org.cyanogenmod.hardware.util.FileUtils;

import java.io.File;
import java.lang.Exception;

public class DisplayColorCalibration {

    private static final String KCAL_TUNING_FILE = "/sys/devices/platform/kcal_ctrl.0/kcal";
    private static final String KCAL_CTRL_FILE = "/sys/devices/platform/kcal_ctrl.0/kcal_enable";
    private static final String KCAL_MIN_FILE = "/sys/devices/platform/kcal_ctrl.0/kcal_min";

    public static boolean isSupported() {
        File file = new File(KCAL_TUNING_FILE);
        return file.exists();
    }

    public static int getMaxValue() {
        return 256;
    }

    public static int getMinValue() {
        try {
            return Integer.parseInt(FileUtils.readOneLine(KCAL_MIN_FILE));
        } catch (Exception e) {}
        return 0;
    }

    public static int getDefValue() {
        return 256;
    }

    public static String getCurColors() {
        return FileUtils.readOneLine(KCAL_TUNING_FILE);
    }

    public static boolean setColors(String colors) {
        if (!FileUtils.writeLine(KCAL_TUNING_FILE, colors)) {
            return false;
        }
        return FileUtils.writeLine(KCAL_CTRL_FILE, "1");
    }
}
