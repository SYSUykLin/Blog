<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    function checkDate() {
        var search = $(".form-control").val().trim();
        if (search == null || search == "") {
            window.alert("搜索关键字不能为空");
            return false;
        }
        else {
            return true;
        }
    }
    function lost() {
        window.alert("假功能！");
    }
</script>

<div class="row">
    <div class="col-md-12">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html">首页</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/blogger/aboutme.html"><strong>关于博主</strong></a>
                        </li>
                        <li><a href="javascript:lost()"><strong>本站源码下载</strong></a></li>
                    </ul>
                    <form onsubmit="return checkDate()" class="navbar-form navbar-right" role="search"
                          action="${pageContext.request.contextPath}/blog/search.do" method="get">
                        <div class="form-group">
                            <input type="text" name="p" value="${p}" class="form-control" placeholder="请输入查询关键字">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
        </nav>
    </div>
</div>