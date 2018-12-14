<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>博客管理</title>
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
    <script type="text/javascript">
        function formerTitle(val, row) {
            return " <a target='_blank' href='${pageContext.request.contextPath}/blog/articles/" + row.id + ".do'>" + val + "</a> ";
        }

        function formerType(val, row) {
            return val.typename;
        }

        function searchBlog() {
            $("#dg").datagrid("load", {"title": $("#title").val()});
        }

        function deleteBlog() {
            var selected = $("#dg").datagrid("getSelections");
            if (selected.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据!");
                return;
            }
            else {
                var id = [];
                for (var i = 0; i < selected.length; i++) {
                    id.push(selected[i].id);
                }
                var ids = id.join(",");
                $.messager.confirm("系统提示", "确定要删除这 <font color = 'red'>" + selected.length + "</font>条数据吗？", function (result) {
                    if (result) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/admin/blog/deleteBlog.do",
                            data: {"ids": ids, "_method": "delete"},
                            type: "post",
                            success: function (result) {
                                if (result.success) {
                                    window.alert("删除成功");
                                    $("#dg").datagrid("reload");
                                }
                                else {
                                    window.alert(result.error);
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
        }

        function openBlogModify() {
            var selected = $("#dg").datagrid("getSelections");
            if (selected.length != 1) {
                $.messager.alert("系统提示", "只能选择一条数据进行更新！");
                return;
            }
            var row = selected[0];
            window.parent.openTab("修改博客", "modifyBlog.jsp?id=" + row.id, "icon-writeblog");
        }
    </script>
</head>
<!-- 博客管理页面 -->

<body style="margin: 1px">
<table id="dg" title="博客管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/blog/list.do" fit="true"
       toolbar="#bar">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center"> 编号</th>
        <th field="title" width="200" align="center" formatter="formerTitle"> 标题</th>
        <th field="releasedateString" width="50" align="center">发布日期</th>
        <th field="blogType" width="50" align="center" formatter="formerType">博客类型</th>
    </tr>
    </thead>
</table>

<div id="bar">

    <div>
        <a href="javascript:openBlogModify()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteBlog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>

    <div>
        &nbsp;标题&nbsp;<input type="text" id="title" size="20" onkeydown="if (event.keyCode == 13) searchBlog()">
        <a href="javascript:searchBlog()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

</body>

</html>

