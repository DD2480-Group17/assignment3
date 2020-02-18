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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.terasology.AdHoc.AdHocSimplexNoise;

import static org.junit.jupiter.api.Assertions.*;
import static org.terasology.AdHoc.AdHocSimplexNoise.*;

import java.lang.Math;
class SimplexNoiseTest {

    /**
     * Tests that every output are in the desired interval [-1,1] with random coordinates
     * Test case 1:
     * Expected: not null
     *
     *Test case 2:
     * min = -1
     * max = 1
     * Expected: output is larger than min and less than max
     */
    @Test
    void testInInterval() {
        long seed = 100;
        float min = (float)(-1);
        float max = (float)1;
        SimplexNoise simplex = new SimplexNoise(seed);


        for (int i = 0; i < 5; i++) {
            float result = simplex.noise(((float) Math.random() * 1000), ((float) Math.random() * 1000), ((float) Math.random() * 1000));
            System.out.println("Test case: " + i);
            assertNotNull(result);
            assertTrue(min <= result && max >= result, "Output in chosen interval");
        }
    }

    /**
     * Tests that if the coordinates x,y,z are equal, the output will be equal to zero and not null.
     * The test also shows the result of the adhoc for x = y = z.
     * Test case 1:
     * Expected value: not null
     *
     * Test case 2:
     * Expected value: 0
     */
    @Test
    void testXYZTheSame(){
        AdHocSimplexNoise.clearResult();

        long seed = 100;
        SimplexNoise simplex = new SimplexNoise(seed);
        float x = (float)100;
        float y = (float)100;
        float z = (float)100;
        float expected = (float)0;
        assertNotNull(simplex.noise(x, y, z));
        assertEquals(expected, simplex.noise(x, y, z), "Output equal to zero");

        boolean[] result = getAdHoc();
        for (int i = 0; i < result.length; i++){
            System.out.println("Visited branch " + (i+1) + " " + result[i]);
        }
    }

    /**
     * Tests that if the coordinate x is larger than y and z and not too small, the output will be not equal to zero
     * and not null. The test also shows the result of the adhoc for x > y and x > z.
     *
     * Test case 1:
     * Expected value: not null
     *
     * Test case 2:
     * Expected value: not 0
     */
    @Test
    void testXLargerThanYX() {
        AdHocSimplexNoise.clearResult();

        long seed = 100;
        SimplexNoise simplex = new SimplexNoise(seed);
        float x = (float)100;
        float y = (float)23;
        float z = (float)34;
        assertNotNull(simplex.noise(x, y, z));
        assertNotEquals(0, simplex.noise(x, y, z), "Output not equal to zero");

        boolean[] result = getAdHoc();
        for (int i = 0; i < result.length; i++){
            System.out.println("Visited branch " + (i+1) + " " + result[i]);
        }
    }

    /**
     * Tests that if the coordinate y is larger than x and z and not too small, the output will be not equal to zero
     * and not null. The test also shows the result of the adhoc for y > x and y > z.
     *
     * Test case 1:
     * Expected value: not null
     *
     * Test case 2:
     * Expected value: not 0
     */
    @Test
    void testYLargerThanXZ() {
        AdHocSimplexNoise.clearResult();

        long seed = 100;
        SimplexNoise simplex = new SimplexNoise(seed);
        float x = (float)23;
        float y = (float)100;
        float z = (float)34;
        assertNotNull(simplex.noise(x, y, z));
        assertNotEquals(0, simplex.noise(x, y, z), "Output not equal to zero");

        boolean[] result = getAdHoc();
        for (int i = 0; i < result.length; i++){
            System.out.println("Visited branch " + (i+1) + " " + result[i]);
        }
    }

    /**
     * Tests that if the coordinate z is larger than x and y and not too small, the output will be not
     * equal to zero and not null. The test also shows the result of the adhoc for z > x and z > y.
     *
     * Test case 1:
     * Expected value: not null
     *
     * Test case 2:
     * Expected value: not 0
     */
    @Test
    void testZLargerThanXAndY() {
        AdHocSimplexNoise.clearResult();

        long seed = 100;
        SimplexNoise simplex = new SimplexNoise(seed);
        float x = (float)34;
        float y = (float)23;
        float z = (float)100;
        assertNotNull(simplex.noise(x, y, z));
        assertNotEquals(0, simplex.noise(x, y, z));

        boolean[] result = getAdHoc();
        for (int i = 0; i < result.length; i++){
            System.out.println("Visited branch " + (i+1) + " " + result[i]);
        }
    }

    @AfterAll
    private static void printCoverage() {
        BranchCoverageSimplesNoiseNoiseMethod.printBranchCoveragePercentage();
    }
}