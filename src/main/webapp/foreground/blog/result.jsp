<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    .data_list .search{
        padding: 5px;
    }
</style>
<script>
    function dopost(p,url) {
        $.ajax({
            url:url,
            type:'post',
            data:{"p":p},
            success:function() {

            }
        })
    }
</script>
<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/search_icon.png"/>
        搜索&nbsp;<font color="red">${p }</font>&nbsp;的结果 &nbsp;(总共搜索到&nbsp;${resultTotal }&nbsp;条记录) </div>
    <div class="datas search">
        <ul>
            <c:choose>
                <c:when test="${blogList.size()==0 }">
                    <div align="center" style="padding-top: 20px">未查询到结果，请换个关键字试试看！</div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="blog" items="${blogList}">
                        <li style="margin-bottom: 20px">
                            <span class="title"><a href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html" target="_blank">${blog.title }</a></span>
                            <span class="summary">摘要:${blog.context }...</span>
                            <span class="link"><a href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html">luence搜索</a>&nbsp;&nbsp;&nbsp;&nbsp;发布日期：${blog.releasedateString }</span>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
        ${pageCode}
</div>