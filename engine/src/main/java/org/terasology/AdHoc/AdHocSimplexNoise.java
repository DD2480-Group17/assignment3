/*
 * Copyright 2020 MovingBlocks
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
package org.terasology.AdHoc;

public class AdHocSimplexNoise {
    public static boolean[] AdHoc = new boolean[10];

    /**
     * Sets all values in AdHoc[] to false before we runt the function
     */
    public AdHocSimplexNoise(){
        for (int i = 0; i < AdHoc.length; i++){
            AdHoc[i] = false;
        }
    }

    /**
     * If we enter a new branch we change the corresponding index to true
     */
    public static void visitBranch(int i){
        AdHoc[i] = true;
    }

    /** Returns the current AdHoc array
     * @return AdHoc with the current branch values
     */
    public static boolean[] getAdHoc(){
        return AdHoc;
    }

    /**
     * Clears the result in the AdHoc
     */
    public static void clearResult(){
        for (int i = 0; i < AdHoc.length; i++){
            AdHoc[i] = false;
        }
    }
}