package com.edgar.util.http;

import com.google.common.base.Preconditions;

/**
 * Created by Edgar on 2016/9/7.
 *
 * @author Edgar  Date 2016/9/7
 */
public class UrlMathcer {
    private boolean matchPath(String path, String pathInfo) {
        Preconditions.checkNotNull(path, "path cannot be null");
        Preconditions.checkNotNull(pathInfo, "pathInfo cannot be null");

        //相等
        if (path.equals(pathInfo)) {
            return true;
        }
        return true;

//        //检查path和pathInfo是不是都是/结尾
//        if (path.endsWith("/") && !pathInfo.endsWith("/")){
//            return false;
//        }
//        if (!path.endsWith("/") && pathInfo.endsWith("/")) {
//            return false;
//        }
//
//
//        if (!this.path.endsWith("*") && ((path.endsWith("/") && !this.path.endsWith("/")) // NOSONAR
//                || (this.path.endsWith("/") && !path.endsWith("/")))) {
//            // One and not both ends with slash
//            return false;
//        }
//        if (this.path.equals(path)) {
//            // Paths are the same
//            return true;
//        }
//
//        // check params
//        List<String> thisPathList = SparkUtils.convertRouteToList(this.path);
//        List<String> pathList = SparkUtils.convertRouteToList(path);
//
//        int thisPathSize = thisPathList.size();
//        int pathSize = pathList.size();
//
//        if (thisPathSize == pathSize) {
//            for (int i = 0; i < thisPathSize; i++) {
//                String thisPathPart = thisPathList.get(i);
//                String pathPart = pathList.get(i);
//
//                if ((i == thisPathSize - 1) && (thisPathPart.equals("*") && this.path.endsWith("*"))) {
//                    // wildcard match
//                    return true;
//                }
//
//                if ((!thisPathPart.startsWith(":"))
//                        && !thisPathPart.equals(pathPart)
//                        && !thisPathPart.equals("*")) {
//                    return false;
//                }
//            }
//            // All parts matched
//            return true;
//        } else {
//            // Number of "path parts" not the same
//            // check wild card:
//            if (this.path.endsWith("*")) {
//                if (pathSize == (thisPathSize - 1) && (path.endsWith("/"))) {
//                    // Hack for making wildcards work with trailing slash
//                    pathList.add("");
//                    pathList.add("");
//                    pathSize += 2;
//                }
//
//                if (thisPathSize < pathSize) {
//                    for (int i = 0; i < thisPathSize; i++) {
//                        String thisPathPart = thisPathList.get(i);
//                        String pathPart = pathList.get(i);
//                        if (thisPathPart.equals("*") && (i == thisPathSize - 1) && this.path.endsWith("*")) {
//                            // wildcard match
//                            return true;
//                        }
//                        if (!thisPathPart.startsWith(":")
//                                && !thisPathPart.equals(pathPart)
//                                && !thisPathPart.equals("*")) {
//                            return false;
//                        }
//                    }
//                    // All parts matched
//                    return true;
//                }
//                // End check wild card
//            }
//            return false;
//        }
    }
}
