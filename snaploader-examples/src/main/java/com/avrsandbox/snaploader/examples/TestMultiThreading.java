/*
 * Copyright (c) 2023, AvrSandbox, jSnapLoader
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'AvrSandbox' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.avrsandbox.snaploader.examples;

import java.io.IOException;
import com.avrsandbox.snaploader.LoadingCriterion;
import com.avrsandbox.snaploader.ConcurrentNativeBinaryLoader;
import com.avrsandbox.snaploader.NativeDynamicLibrary;
import com.avrsandbox.snaploader.UnSupportedSystemError;

/**
 * Tests multi-threading and thread locks.
 * 
 * @author pavl_g
 */
public final class TestMultiThreading {

    protected static final Thread thread0 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(200);
                    TestBasicFeatures.loader.loadLibrary(LoadingCriterion.CLEAN_EXTRACTION);
                } catch (UnSupportedSystemError | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }, "Thread-Zero");

    protected static final Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(200);
                    TestBasicFeatures.loader.loadLibrary(LoadingCriterion.CLEAN_EXTRACTION);
                } catch (UnSupportedSystemError | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }, "Thread-One");

    protected static final Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(200);
                    TestBasicFeatures.loader.loadLibrary(LoadingCriterion.CLEAN_EXTRACTION);
                } catch (UnSupportedSystemError | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }, "Thread-Three");

    public static void main(String[] args) {
        TestBasicFeatures.loader = new ConcurrentNativeBinaryLoader(TestBasicFeatures.libraryInfo);
        final NativeDynamicLibrary nativeDynamicLibrary = TestBasicFeatures.loader.getNativeDynamicLibrary();
        // System.out.println("Jar file path: " + nativeDynamicLibrary.getJarPath());
        // System.out.println("Library directory: " + nativeDynamicLibrary.getLibraryDirectory());
        // System.out.println("Compressed library: " + nativeDynamicLibrary.getCompressedLibrary());
        // System.out.println("Proposed extracted library: " + nativeDynamicLibrary.getExtractedLibrary());
        
        thread0.start();
        thread1.start();
        thread2.start();
        
        while (true);
    }
}
