/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.android.mtp;

import android.os.storage.StorageManager;
import android.system.ErrnoException;
import android.system.Os;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import java.io.File;

@SmallTest
public class AppFuseTest extends AndroidTestCase {
    /**
     * TODO: Enable this test after adding SELinux policies for appfuse.
     * @throws ErrnoException
     * @throws InterruptedException
     */
    public void disabled_testBasic() throws ErrnoException, InterruptedException {
        final StorageManager storageManager = getContext().getSystemService(StorageManager.class);
        final AppFuse appFuse = new AppFuse("test");
        appFuse.mount(storageManager);
        final File file = appFuse.getMountPoint();
        assertTrue(file.isDirectory());
        assertEquals(1, Os.stat(file.getPath()).st_ino);
        appFuse.close();
        assertTrue(1 != Os.stat(file.getPath()).st_ino);
    }
}
