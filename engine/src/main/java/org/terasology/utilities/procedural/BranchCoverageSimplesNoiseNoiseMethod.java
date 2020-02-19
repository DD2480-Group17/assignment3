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
package org.terasology.utilities.procedural;

public class BranchCoverageSimplesNoiseNoiseMethod {

    // total 22 branches
    public static final boolean branchCovered[] = new boolean[22];

    /**
     * Method for calculating branch coverage percentage
     * @return branch coverage percentage
     */
    public static double calcBranchCoveragePercentage() {
        double counter = 0;
        for(boolean b : branchCovered){
            if (b) counter++;
        }

        return counter / branchCovered.length * 100.0d;
    }

    /**
     * Method for printing branch coverage percentage.
     */
    public static void printBranchCoveragePercentage() {
        for (int i = 0; i < branchCovered.length; i++){
            System.out.println("noise(): branch " + i + ": " + branchCovered[i]);
        }
        System.out.println("Branch coverage of SimplexNoise.noise(x1,x2,x3,x4): " + calcBranchCoveragePercentage() + " %");
    }
}
