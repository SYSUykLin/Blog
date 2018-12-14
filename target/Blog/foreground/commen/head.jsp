<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .a
    {
        text-align: center;
        font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;
        font-size: large;
    }
</style>
<div class="row">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/static/images/logo2.png" alt="博客系统">
    </div>
    <div class="col-md-8">
        <a class="a" href="${pageContext.request.contextPath}/admin/main.jsp">写博客前请登录</a>
        <iframe style="float: right;" width="420" scrolling="no" height="60" frameborder="0"
                allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5"></iframe>
    </div>
</div>
