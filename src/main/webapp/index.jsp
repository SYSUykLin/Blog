<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
    body {
        padding-top: 10px;
        padding-bottom: 40px;
    }
</style>
<body>
<div class="container">

    <jsp:include page="foreground/commen/head.jsp"></jsp:include>

    <jsp:include page="foreground/commen/menu.jsp"></jsp:include>

    <div class="row">
        <div class="col-md-9">
            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/list_icon.png">最新博客
                </div>
                <div class="datas">
                    <ul>
                        <li>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/user_icon.png" alt="博主信息">博主信息
                </div>
                <div class="user_image">
                    <img src="${pageContext.request.contextPath}/static/images/${blogger.image}"/>
                </div>
                <div class="nickName">${blogger.nickname}</div>
                <div class="userSign">(${blogger.sign})</div>
            </div>
            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/byType_icon.png" alt="日志">日志
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="blogTypeCount" items="${blogTypes }">
                            <li><span><a href="#">${blogTypeCount.typename }(${blogTypeCount.blogcount })</a></span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>
                    按日志日期
                </div>

                <div class="datas">
                    <ul>
                        <c:forEach var="blogCount" items="${blogs }">
                            <li><span><a href="#">${blogCount.releasedateString }(${blogCount.blogcount })</a></span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/link_icon.png"/>
                    友情链接
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach var="link" items="${linklist }">
                            <li><span><a href="${link.linkurl }" target="_blank">${link.linkname }</a></span></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

        <jsp:include page="foreground/commen/foot.jsp"></jsp:include>

    </div>
</body>
</html>


<!-- EL表达式的查询顺序： 首先查找request域，没有查询session，最后就是application域 -->