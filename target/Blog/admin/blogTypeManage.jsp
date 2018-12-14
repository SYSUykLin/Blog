<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>博客类别管理</title>
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

        var url;

        function clodeBlogType() {
            $("#dlg").dialog("close");
        }

        function addBlogType() {
            $("#dlg").dialog("open").dialog("setTitle", "添加博客类别信息");
            url = "${pageContext.request.contextPath}/admin/blogType/save.do";
        }

        function openBlogTypeModify() {
            var selected = $("#dg").datagrid("getSelections");
            if (selected.length != 1) {
                $.messager.alert("系统提示", "请选择一条数据！");
                return;
            }
            $("#dlg").dialog("open").dialog("setTitle", "更新博客类别信息");
            var row = selected[0];
            $("#fm").form("load", row);
            url = "${pageContext.request.contextPath}/admin/blogType/save.do?id=" + row.id;
        }

        function saveBlogType() {
            $("#fm").form("submit", {
                url: url,
                dataType: "json",
                onSubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                    result = eval("(" + result + ")");
                    if (result.success) {
                        window.alert("操作成功，请刷新页面");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                    else if (result.success == false) {
                        window.alert("操作失败，数据库错误！");
                        return;
                    }
                }
            });
        }

        function resetValue() {
            $("#typename").val("");
            $("#orderno").val("");
        }
    </script>
</head>
<!-- 博客管理页面 -->

<body style="margin: 1px">
<table id="dg" title="博客管理" class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${pageContext.request.contextPath}/admin/blogType/list.do" fit="true"
       toolbar="#bar">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="20" align="center"> 编号</th>
        <th field="typename" width="100" align="center"> 博客类型名称</th>
        <th field="orderno" width="100" align="center">排序序号</th>
    </tr>
    </thead>
</table>

<div id="bar">
    <div>
        <a href="javascript:addBlogType()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openBlogTypeModify()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    </div>
</div>

<%-- 修改添加弹窗 --%>

<div id="dlg" class="easyui-dialog" style="width: 500px;height: 180px;padding: 10px 20px" closed="true"
     buttons="#button-dlg">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>博客类别名称</td>
                <td>
                    <input type="text" name="typename" id="typename" class="easyui-validatebox" required="true">
                </td>
            </tr>
            <tr>
                <td>博客类别序号</td>
                <td>
                    <input type="text" name="orderno" id="orderno" class="easyui-numberbox" required="true"
                           style="width:60px ">
                </td>
            </tr>
        </table>
    </form>

</div>

<div id="button-dlg">
    <a href="javascript:saveBlogType()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:clodeBlogType()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>

</html>

