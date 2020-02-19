package org.terasology.math;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.terasology.AdHoc.AdHocAABB;
import org.terasology.math.geom.Vector3f;
import org.terasology.protobuf.AdHocNetData;
import org.terasology.protobuf.BranchCoverageNetDataNetMessageBuilderClearMethod;


import static org.junit.jupiter.api.Assertions.*;

class AABBTest {

    @AfterAll
    private static void printCoverage() {
        System.out.println(AdHocAABB.getPathCenterPoint());
        System.out.println("centerPointForNormal has a coverage of: "+AdHocAABB.getCoverage(true)+"\n");
        System.out.println(AdHocAABB.getPathNormalForPlane());
        System.out.println("normalForPlaneClosestToOrigin has a coverage of: "+AdHocAABB.getCoverage(false)+"\n");
    }
    /**
     * the test testCenterPointForNormalInXDirection, test if the function centerPointForNormal returns the correct centerpoint if
     * the object is between vector 0,0,0 and 0,0,5 facing the positive or negative x-axis.
     * <p>
     * Test 1
     * Input:
     * min = 0,0,0 max = 0,0,5 normal = 1,0,0
     * Expected_output:
     * A vector with coordinates (0,0,2.5)
     * <p>
     * Test 2
     * Input:
     * min = 0,0,0 max = 0,0,5 normal = -1,0,0
     * Expected_output:
     * A vector with coordinates (0,0,2.5)
     */
    @Test
    public void testCenterPointForNormalInXDirection() {
        Vector3f normalPositive = new Vector3f(1, 0, 0);
        Vector3f normalNegative = new Vector3f(-1, 0, 0);
        Vector3f max = new Vector3f(0, 0, 5);
        Vector3f min = new Vector3f(0, 0, 0);
        Vector3f answer = new Vector3f(0, 0, 2.5f);

        AABB aabb = AABB.createMinMax(min, max);

        Vector3f result = aabb.centerPointForNormal(normalPositive);

        assertEquals(result, answer);

        Vector3f result2 = aabb.centerPointForNormal(normalNegative);

        assertEquals(result2, answer);
    }

    /**
     * the test testCenterPointForNormalMovedXAngledInYDirection, test if the function centerPointForNormal returns the correct centerpoint if
     * the object is between the angled planes 5,0,0 and 10,0,5 facing the positive or negative y-axis.
     * <p>
     * Test 1
     * Input:
     * min = 5,0,0 max = 10,0,5 normal = 0,1,0
     * Expected_output:
     * A vector with coordinates (7.5,0,2.5)
     * <p>
     * Test 2
     * Input:
     * min = 5,0,0 max = 10,0,5 normal = 0,-1,0
     * Expected_output:
     * A vector with coordinates (7.5,0,2.5)
     */
    @Test
    public void testCenterPointForNormalMovedXAngledInYDirection() {
        Vector3f normalPositive = new Vector3f(0, 1, 0);
        Vector3f normalNegative = new Vector3f(0, -1, 0);
        Vector3f max = new Vector3f(10, 0, 5);
        Vector3f min = new Vector3f(5, 0, 0);
        Vector3f answer = new Vector3f(7.5f, 0, 2.5f);

        AABB aabb = AABB.createMinMax(min, max);

        Vector3f result = aabb.centerPointForNormal(normalPositive);

        assertEquals(result, answer);

        Vector3f result2 = aabb.centerPointForNormal(normalNegative);

        assertEquals(result2, answer);
    }

    /**
     * the test testCenterPointForNormalNotUnitvectorNomal, test if the function centerPointForNormal returns the vector 0,0,0 if
     * the input normal is not a unitvector.
     * <p>
     * Test 1
     * Input:
     * min = 0,0,0 max = 0,0,5 normal = 5,0,0
     * Expected_output:
     * A vector with coordinates (0,0,0)
     */
    @Test
    public void testCenterPointForNormalNotUnitvectorNomal() {
        Vector3f normal = new Vector3f(5, 0, 0);
        Vector3f max = new Vector3f(0, 0, 5);
        Vector3f min = new Vector3f(0, 0, 0);
        Vector3f answer = new Vector3f(0, 0, 0);

        AABB aabb = AABB.createMinMax(min, max);

        Vector3f result = aabb.centerPointForNormal(normal);

        assertEquals(result, answer);
    }

    /**
     * the test testNormalForPlaneClosestToOrigin, test if the function normalForPlaneClosestToOrigin returns the normal -1,0,0 if
     * the input is the point 0,0,1, min = 0,0,0 and max = 0,0,5.
     * <p>
     * Test 1
     * Input:
     * min = 0,0,0 max = 0,0,5 pointOnAABB = 0,0,1, testx = true, testy = true and testz = true.
     * Expected_output:
     * A vector with coordinates (-1,0,0)
     */
    @Test
    public void testNormalForPlaneClosestToOrigin() {
        Vector3f pointOnAABB = new Vector3f(0, 0, 1);
        Vector3f max = new Vector3f(0, 0, 5);
        Vector3f min = new Vector3f(0, 0, 0);
        Vector3f origin = new Vector3f(0, 0, 0);
        Vector3f wanted = new Vector3f(-1, 0, 0);

        AABB aabb = AABB.createMinMax(min, max);

        Vector3f results = aabb.normalForPlaneClosestToOrigin(pointOnAABB, origin, true, true, true);
        assertEquals(results, wanted);
    }

    /**
     * The test testNormalForPlaneClosestToOriginWithSameZ, test if the function normalForPlaneClosestToOrigin returns the normal (0,0,-1) if
     * the input is the point (0,0,1), min = (0,0,1) and max = (0,0,1) (enters the first two branches because z-coordinates is the same for all vectors).
     * <p>
     * Test case 1:
     * Expected_output: A vector with coordinates (0,0,-1)
     */
    @Test
    void testNormalForPlaneClosestToOriginWithSameZ() {
        Vector3f pointOnAABB = new Vector3f(0, 0, 1);
        Vector3f max = new Vector3f(0, 0, 1);
        Vector3f min = new Vector3f(0, 0, 1);
        Vector3f origin = new Vector3f(0, 0, 0);
        Vector3f wanted = new Vector3f(0, 0, -1);

        AABB aabb = AABB.createMinMax(min, max);

        Vector3f results = aabb.normalForPlaneClosestToOrigin(pointOnAABB, origin, true, true, true);
        assertEquals(results, wanted);
    }

    /**
     * the test testCenterPointForNormalPositiveZ, test if the function centerPointForNormal returns the vector 0,0,1 if
     * the normal is (0,0,1), min (0,0,1) and max (0,0,1)
     * <p>
     * Test case 1:
     * Expected_output: A vector with coordinates (0,0,1)
     */
    @Test
    void testCenterPointForNormalPositivZ() {
        Vector3f normal = new Vector3f(0, 0, 1);
        Vector3f max = new Vector3f(0, 0, 1);
        Vector3f min = new Vector3f(0, 0, 1);
        Vector3f answer = new Vector3f(0, 0, 1);

        AABB aabb = AABB.createMinMax(min, max);

        Vector3f result = aabb.centerPointForNormal(normal);

        assertEquals(result, answer);
    }
}