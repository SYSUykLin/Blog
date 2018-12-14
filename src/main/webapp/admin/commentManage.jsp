<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>评论管理</title>
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

        function stateForm(val, row) {
            if (val == 1) {
                return "已通过审核";
            }
            else {
                return "未通过审核";
            }
        }

        function deleteComment() {
            var selected = $("#dg").datagrid("getSelections");
            if (selected.length == 0) {
                $.messager.alert("系统提示", "请至少选择一条数据");
                return;
            }

            var strId = [];
            for(var i = 0;i < selected.length;i++)
            {
                strId.push(selected[i].id);
            }
            var StrId = strId.join(",");
            $.messager.confirm("系统提示", "确定要删除这 <font color = 'red'>" + selected.length + "</font>条数据吗？", function (result) {
                if (result) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/comment/review.do",
                        data: {"ids": StrId, "_method": "PUT", "state": 0},
                        type: "post",
                        success: function (result) {
                            if (result.success) {
                               $.messager.alert("系统提示","删除成功")
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

<body style="margin: 1px">
<table id="dg" title="评论管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/comment/list.do" fit="true"
       toolbar="#bar">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="30" align="center"> 评论编号</th>
        <th field="blog" width="150" align="center" formatter="formatBlogTitle">博客标题</th>
        <th field="userIp" width="100" align="center">用户IP</th>
        <th field="content" width="200" align="center">评论内容</th>
        <th field="dateString" width="100" align="center">评论日期</th>
        <th field="state" width="50" align="center" formatter="stateForm">状态</th>
    </tr>
    </thead>
</table>

<div id="bar">
    <div>
        <a href="javascript:deleteComment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>

</body>

</html>


