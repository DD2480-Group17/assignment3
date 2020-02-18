package org.terasology.AdHoc;

public class AdHocAABB {
    private static int[] adHocCenterPoint = new int[7];
    private static int[] adHocNormalForPlane = new int[7];

    /**
     * The method addVisitedBranchCenterPoint add a marker to a adHocCenterPoint, which represent that the branch with visitedId has been visited.
     * This method should only be used by the method centerPointForNormal in the class AABB.
     * @param visitedId the id of the visited branch.
     */
    public static void addVisitedBranchCenterPoint(int visitedId) {
        if (visitedId < adHocCenterPoint.length && visitedId >= 0) {
            adHocCenterPoint[visitedId] = 1;
        }
    }
    /**
     * The method addVisitedBranchNormalForPlane add a marker to a adHocNormalForPlane, which represent that the branch with visitedId has been visited.
     * This method should only be used by the method normalForPlaneClosestToOrigin in the class AABB.
     * @param visitedId the id of the visited branch.
     */
    public static void addVisitedBranchNormalForPlane(int visitedId) {
        if (visitedId < adHocNormalForPlane.length && visitedId >= 0) {
            adHocNormalForPlane[visitedId] = 1;
        }
    }

    /**
     * Returns a string containing the id of all visited branches by the method centerPointForNormal from the class AABB.
     * @return A string conatining the id of all visited branches by the method centerPointForNormal.
     */
    public static String getPathCenterPoint() {
        return getString(adHocCenterPoint, "centerPointForNormal");
    }

    /**
     * Returns a string containing the id of all visited branches by the method normalForPlaneClosestToOrigin from the class AABB.
     * @return A string conatining the id of all visited branches by the method normalForPlaneClosestToOrigin.
     */
    public static String getPathNormalForPlane() {
        return getString(adHocNormalForPlane, "normalForPlaneClosestToOrigin");
    }

    private static String getString(int[] adHoc, String name) {
        StringBuilder builder = new StringBuilder();
        builder.append("Visited pahts in "+name+":\n");
        for (int i = 0; i < adHoc.length; i++) {
            if (adHoc[i] == 1) {
                builder.append("Visited branch ");
                builder.append(i);
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
