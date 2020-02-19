package org.terasology.AdHoc;

public class AdHocAABB {
    private static int[] adHocCenterPoint = new int[12];
    private static int[] adHocNormalForPlane = new int[16];
    private static double foundCenter = 0;
    private static double foundNormal = 0;

    /**
     * The method addVisitedBranchCenterPoint add a marker to a adHocCenterPoint, which represent that the branch with visitedId has been visited.
     * This method should only be used by the method centerPointForNormal in the class AABB.
     * @param visitedId the id of the visited branch.
     */
    public static void addVisitedBranchCenterPoint(int visitedId) {
        if (visitedId < adHocCenterPoint.length && visitedId >= 0) {
            if(adHocCenterPoint[visitedId] == 0){
                adHocCenterPoint[visitedId] = 1;
                foundCenter++;
            }
        }
    }
    /**
     * The method addVisitedBranchNormalForPlane add a marker to a adHocNormalForPlane, which represent that the branch with visitedId has been visited.
     * This method should only be used by the method normalForPlaneClosestToOrigin in the class AABB.
     * @param visitedId the id of the visited branch.
     */
    public static void addVisitedBranchNormalForPlane(int visitedId) {
        if (visitedId < adHocNormalForPlane.length && visitedId >= 0) {
            if(adHocNormalForPlane[visitedId] == 0) {
                adHocNormalForPlane[visitedId] = 1;
                foundNormal++;
            }
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

    /**
     * Returns the coverage of the function centerPointForNormal or normalForPlaneClosestToOrigin
     * @param centerPoint determines if the coverage from centerPointForNormal should be returned or normalForPlaneClosestToOrigin
     * @return the coverage of the function centerPointForNormal or normalForPlaneClosestToOrigin
     */
    public static Double getCoverage(boolean centerPoint){
        if(centerPoint){
            return foundCenter/((double)adHocCenterPoint.length);
        }else{
            return foundNormal/((double)adHocNormalForPlane.length);
        }
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
