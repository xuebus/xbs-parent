package com.xuebusi.fjf.http;

import com.alibaba.fastjson.JSON;
import com.xuebusi.fjf.constants.Constants;
import com.xuebusi.fjf.lang.FLNumberUtil;
import com.xuebusi.fjf.lang.FLStringUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

// 隔离业务代码
public class RequestUtil {
    private static final Logger log = LoggerFactory.getLogger(RequestUtil.class);
/*
    *//**
     * 从cookie中获得puser id
     *
     * @param request
     * @return
     *//*
    public static String getPuserId(HttpServletRequest request) {
        String userId = CookieUtil.getKeyIdentity(request, CookieUtil.USER_ID);
        return userId;
    }
*/
    /**
     * @param map
     * @param likeFlag 启用模糊查询
     * @return
     */
    public static Map<String, Object> parseRequestParamterMap(Map<String, Object> map, boolean likeFlag) {
        return parseRequestParamterMap(map, likeFlag, false);
    }
    public static Map<String, Object> parseRequestParamterMap_v3(Map<String, String[]> map, boolean likeFlag) {
        return parseRequestParamterMap_v3(map, likeFlag, false);
    }

    /**
     * @param map
     * @param likeFlag 启用模糊查询
     * @param markFlag 启用掩码
     * @return
     */
    public static Map<String, Object> parseRequestParamterMap(Map<String, Object> map, boolean likeFlag, boolean markFlag) {
        Map<String, Object> retMap = Maps.newHashMap();
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = FLStringUtil.toString(it.next(), "");
                String[] values = (String[]) (map.get(key) == null ? "" : map.get(key));
                if (values.length == 1) {
                    String target = "," + key + ",";
                    if (likeFlag && ",name,groupName,regionName,publicsName,buildingName,nick,".contains(target)) {
                        retMap.put(key + "_like", values[0]);
                        log.info(key + "_like" + values[0]);
                    } else {
                        retMap.put(key, values[0]);
                    }
                    if (markFlag && ",pwd,password,phone,mobile,".contains(target.toLowerCase())) {
                        retMap.put(key, "******");
                    }
                } else if (values.length > 1) {
                    retMap.put(key + "Set", Sets.newHashSet(values));
                    log.info(key + "Set", Joiner.on(",").join(values));
                }
            }
        }
        return retMap;
    }
    public static Map<String, Object> parseRequestParamterMap_v3(Map<String, String[]> map, boolean likeFlag, boolean markFlag) {
        Map<String, Object> retMap = Maps.newHashMap();
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = FLStringUtil.toString(it.next(), "");
                String[] values = map.get(key);
//                String[]  = map.get(key) == null ? "" : map.get(key);
                if(values == null){
                    continue;
                }
                if (values.length == 1) {
                    String target = "," + key + ",";
                    if (likeFlag && ",name,groupName,regionName,publicsName,buildingName,nick,".contains(target)) {
                        retMap.put(key + "_like", values[0]);
                        log.info(key + "_like" + values[0]);
                    } else {
                        retMap.put(key, values[0]);
                    }
                    if (markFlag && ",pwd,password,phone,mobile,".contains(target.toLowerCase())) {
                        retMap.put(key, "******");
                    }
                } else if (values.length > 1) {
                    retMap.put(key + "Set", Sets.newHashSet(values));
                    log.info(key + "Set", Joiner.on(",").join(values));
                }
            }
        }
        return retMap;
    }

    public static int getPageNum(HttpServletRequest req) {
        String pageNumber = req.getParameter("page");
        return FLNumberUtil.toInt(pageNumber, 1);
    }

    public static int getPageSize(HttpServletRequest req) {
        String strPageSize = req.getParameter("size");
        return FLNumberUtil.toInt(strPageSize, 10);
    }
/*
    *//**
     * 从cookie中获取用户管理的社区所在的开发商
     *
     * @param request
     * @return
     *//*
    public static String getPuserDeveloperIds(HttpServletRequest request) {
        String projectIds = CookieUtil.getKeyIdentity(request, CookieUtil.DEVELOPERIDS);
        return CookieUtil.cookie2String(projectIds);
    }

    public static Set<Long> getPuserDeveloperIds2Set(HttpServletRequest request) {
        String developerIds = getPuserDeveloperIds(request);
        return CookieUtil.SUPPER_VALUE.equals(developerIds) ? null : QDStringUtil.ids2Set(developerIds);
    }
*/
  /*  *//**
     * 从cookie中获取用户管理的社区所在的城市
     *
     * @param request
     * @return
     *//*
    public static String getPuserRegionIds(HttpServletRequest request) {
        String regionIds = CookieUtil.getKeyIdentity(request, CookieUtil.REGIONIDS);
        return CookieUtil.cookie2String(regionIds);
    }

    public static Set<Long> getPuserRegionIds2Set(HttpServletRequest request) {
        String regionIds = getPuserRegionIds(request);
        return CookieUtil.SUPPER_VALUE.equals(regionIds) ? null : QDStringUtil.ids2Set(regionIds);
    }*/

    /**
     * 从cookie中获取用户管理的社区，返回格式 1,2,3
     *
     * @param request
     * @return
     *//*
    public static String getPuserProjectIds(HttpServletRequest request) {
        String projectIds = CookieUtil.getKeyIdentity(request, CookieUtil.PROJECTIDS);
        return CookieUtil.cookie2String(projectIds);
    }

    public static Set<Long> getProjectIds2Set(HttpServletRequest req) {
        String ids = getPuserProjectIds(req);
        return CookieUtil.SUPPER_VALUE.equals(ids) ? null : QDStringUtil.ids2Set(ids);
    }*/

//    public static void refreshPuserProject(HttpServletRequest request, Project project) {
//        Map<String, String> userMap = CookieUtil.getIdentity(request);
//        String projectIds = userMap.get(CookieUtil.PROJECTIDS);
//        projectIds = projectIds + "|" + project.getId();
//        return;
//    }

    /**
     * 从cookie中获取用户的菜单
     *
     * @param request
     * @return
     *//*
    public static String getPuserMenuIds(HttpServletRequest request) {
        String menuIds = CookieUtil.getKeyIdentity(request, CookieUtil.MENUIDS);
        return CookieUtil.cookie2String(menuIds);
    }

    public static Set<Long> getPuserMenuIds2Set(HttpServletRequest request) {
        String menuIds = getPuserMenuIds(request);
        return CookieUtil.SUPPER_VALUE.equals(menuIds) ? null : QDStringUtil.ids2Set(menuIds);
    }*/

    /**
     * @param response
     * @param cacheAge
     */
    public static void setCacheHeader(HttpServletResponse response, int cacheAge) {
        response.setHeader("Pragma", "Public");
        // HTTP 1.1
        response.setHeader("Cache-Control", "max-age=" + cacheAge);
        response.setDateHeader("Expires", System.currentTimeMillis() + cacheAge * 1000L);
    }

    /**
     * @param response
     */
    public static void setNoCacheHeader(HttpServletResponse response) {
        // HTTP 1.0
        response.setHeader("Pragma", "No-cache");
        // HTTP 1.1
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    /**
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String dump(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();

        Enumeration names = request.getAttributeNames();
        sb.append("\nrequest attributes: \n");
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            sb.append("name = [" + name + "] value = [" + request.getAttribute(name) + "]\n");
        }

        names = request.getParameterNames();
        sb.append("\nrequest parameter: \n");
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            sb.append("name = [" + name + "] value = [" + request.getParameter(name) + "]\n");
        }
        return sb.toString();
    }

    /**
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xReq = request.getHeader("X-Requested-With");
        return (xReq != null);
    }

    /**
     * @param request
     * @return
     */
    public static String getRefer(HttpServletRequest request) {
        return request.getHeader("REFERER");
    }

    /**
     * @param url
     * @return
     */
    public static String encodeURL(String url) {
        try {
            return java.net.URLEncoder.encode(url, Constants.CODE_UTF8);
        } catch (UnsupportedEncodingException e) {
            // do nothing
            return null;
        }
    }

    /**
     * @param url
     * @return
     */
    public static String decodeURL(String url) {
        try {
            return java.net.URLDecoder.decode(url, Constants.CODE_UTF8);
        } catch (UnsupportedEncodingException e) {
            // do nothing
            return null;
        }
    }

    /**
     * @param request
     * @return
     */
    public static String getRequestCompleteURL(HttpServletRequest request) {
        return request.getRequestURL().append("?").append(request.getQueryString()).toString();
    }

    /**
     * @param response
     * @param name
     * @param value
     * @param expiry
     * @param domain
     * @param path     .
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int expiry, String domain, String path) {
        if (log.isDebugEnabled()) {
            log.debug("Setting cookie '" + name + " [ " + value + " ] ' on path '" + path + "'");
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path != null ? path : "/");
        cookie.setMaxAge(expiry); // 30 days
        response.addCookie(cookie);
    }

    /**
     * @param response
     * @param name
     * @param domain
     * @param path     .
     */
    public static void deleteCookie(HttpServletResponse response, String name, String domain, String path) {
        Cookie cookie = new Cookie(name, "");
        deleteCookie(response, cookie, domain, path);
    }

    /**
     * 批量删除某个域名下的cookie
     *
     * @param response
     * @param names
     * @param domain
     * @param path
     */
    public static void deleteCookies(HttpServletResponse response, String[] names, String domain, String path) {
        if (null == names) {
            return;
        }
        for (int i = 0; i < names.length; i++) {
            Cookie cookie = new Cookie(names[i], "");
            deleteCookie(response, cookie, domain, path);
        }

    }

    /**
     * @param response
     * @param cookie
     * @param domain
     * @param path     .
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie, String domain, String path) {
        if (cookie != null) {
            if (log.isDebugEnabled()) {
                log.debug("Deleting cookie '" + cookie.getName() + "' on domain '" + cookie.getDomain() + "' path '" + path + "'");
            }
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            if (domain != null) {
                cookie.setDomain(domain);
            }
            cookie.setPath(path != null ? path : "/");
            response.addCookie(cookie);
        }
    }

    /**
     * @param request
     * @param name
     * @return .
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) {
            return returnCookie;
        }

        for (int i = 0; i < cookies.length; i++) {
            Cookie thisCookie = cookies[i];

            if (thisCookie.getName().equals(name)) {
                // cookies with no value do me no good!
                if (StringUtils.isNotBlank(thisCookie.getValue())) {
                    returnCookie = thisCookie;

                    break;
                }
            }
        }

        return returnCookie;
    }

    /**
     * 获取Client IP : 此方法能够穿透squid 和 proxy
     *
     * @param request
     * @return .
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }

    public static <T> T getEntity4Json(HttpServletRequest req, String key, Class<T> clazz) {
        String str = req.getParameter(key);
        if (FLStringUtil.isNotBlank(str)) {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
        return null;
    }
}
