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
    public static class BaseAdHocTool {
        private boolean[] branches;
        private String name;

        public BaseAdHocTool(int branchAmount, String name) {
            branches = new boolean[branchAmount];
            this.name = name;
        }

        public void visitBranch(int i) {
            branches[i] = true;
        }

        public void visitIfUnvisited(int i, int other) {
            if (!branches[other]) {
                branches[i] = true;
            }
        }

        private int getVisitedBranches() {
            int visitedBranches = 0;
            for (boolean branch : branches) {
                if (branch) {
                    visitedBranches++;
                }
            }
            return visitedBranches;
        }

        public void printRes() {
            System.out.println(name + " branch coverage: " + getVisitedBranches() + " / " + branches.length +
                    " (" + 1000 * getVisitedBranches() / branches.length * 0.1 + " %)");
        }
    }

    public static class NetMessage {
        public static class Builder {
            public static class MergeFrom {
                private static BaseAdHocTool tool = new BaseAdHocTool(156, "NetData.NetMessage.Builder.mergeFrom");

                public static void visitBranch(int i) {
                    tool.visitBranch(i);
                }

                public static void printRes() {
                    tool.printRes();
                }

                public static void visitIfUnvisited(int i, int other) {
                    tool.visitIfUnvisited(i, other);
                }
            }

            public static class IsInitialized {
                private static BaseAdHocTool tool = new BaseAdHocTool(86, "NetData.NetMessage.Builder.IsInitialized");

                public static void visitBranch(int i) {
                    tool.visitBranch(i);
                }

                public static void printRes() {
                    tool.printRes();
                }

                public static void visitIfUnvisited(int i, int other) {
                    tool.visitIfUnvisited(i, other);
                }
            }
        }
    }
}
