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
package org.terasology.protobuf;

public class AdHocNetData {
    private static final int BRANCH_AMOUNT = 156;
    private static final boolean[] branches = new boolean[BRANCH_AMOUNT];

    public static void visitBranch(int i) {
        branches[i] = true;
    }

    private static int getVisitedBranches() {
        int visitedBranches = 0;
        for (boolean branch : branches) {
            if (branch) {
                visitedBranches++;
            }
        }
        return visitedBranches;
    }

    public static void printRes() {
        System.out.println("NetData.NetMessage.Builder.mergeFrom branch coverage: " +
                getVisitedBranches() + " / " + BRANCH_AMOUNT + " (" + 1000 * getVisitedBranches() / BRANCH_AMOUNT * 0.1 + " %)");
    }
}
