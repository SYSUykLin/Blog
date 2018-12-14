<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>评论审核管理</title>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="gbk"
            src="${pageContext.request.contextPath}/static/ueditor/gbk-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="gbk"
            src="${pageContext.request.contextPath}/static/ueditor/gbk-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="gbk"
            src="${pageContext.request.contextPath}/static/ueditor/gbk-jsp/lang/zh-cn/zh-cn.js"></script>

    <script>
        function formatBlogTitle(val, row) {
            return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/" + val.id + ".do'>" + val.title + "</a>";
        }

        function commentReview(state) {
            var selected = $("#dg").datagrid("getSelections");
            if (selected.length == 0) {
                $.messager.alert("系统提示", "请选择至少一条数据");
                return;
            }
            var id = [];
            for (var i = 0; i < selected.length; i++) {
                id.push(selected[i].id);
            }
            var StrId = id.join(",");
            $.messager.confirm("系统提示", "确定要审核这 <font color = 'red'>" + selected.length + "</font>条数据吗？", function (result) {
                if (result) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/comment/review.do",
                        data: {"ids": StrId, "_method": "PUT", "state": state},
                        type: "post",
                        success: function (result) {
                            if (result.success) {
                                if (state == 0) {
                                    $.messager.alert("系统提示","评论不通过,已删除成功")
                                }
                                if (state == 1) {
                                    $.messager.alert("系统提示","评论已通过审核");
                                }
                                $("#dg").datagrid("reload");
                            }
                            else {
                                window.alert("后台数据错误");
                                $("#dg").datagrid("reload");
                            }
                        },
                        error: function () {
                            alert1("数据传输失败");
                        }
                    })
                }
            });
        }
    </script>

</head>
<!-- 博客管理页面 -->

<body style="margin: 1px">
<table id="dg" title="评论审核管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/comment/list.do?state=0" fit="true"
       toolbar="#bar">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center"> 评论编号</th>
        <th field="blog" width="150" align="center" formatter="formatBlogTitle">博客标题</th>
        <th field="userIp" width="100" align="center">用户IP</th>
        <th field="content" width="200" align="center">评论内容</th>
        <th field="dateString" width="100" align="center">评论日期</th>
    </tr>
    </thead>
</table>

<div id="bar">
    <div>
        <a href="javascript:commentReview(1)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核通过</a>
        <a href="javascript:commentReview(0)" class="easyui-linkbutton" iconCls="icon-no" plain="true">审核不通过</a>
    </div>
</div>

</body>

</html>


