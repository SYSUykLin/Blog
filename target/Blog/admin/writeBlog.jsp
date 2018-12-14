<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
    #editor {
        width: 80%;
        height: 400px;
    }
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>写博客</title>
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
        function submitData() {
            var title = $("#title").val();
            var blogTypeId = $("#blogTypeId").combobox("getValue");
            var content = UE.getEditor("editor").getContent();
            var keyword = $("#keyWord").val();
            var contentNoTag = UE.getEditor("editor").getContentTxt();

            if (title == null || title == "") {
                window.alert("请输入标题");
            }
            else if (blogTypeId == null || blogTypeId == "") {
                window.alert("请选择博客类型");
            }
            else if (content == null || content == "") {
                window.alert("请填写博客内容");
            } else {
                $.ajax({
                    url: "${pageContext.request.contextPath}/admin/blog/save.do",
                    dataType: "json",
                    data: {
                        "title": title,
                        "blogType.id": blogTypeId,
                        "context": content,
                        "keyword": keyword,
                        "summary": UE.getEditor("editor").getContentTxt().substr(0, 155),
                        "contentNoTag":contentNoTag
                    },
                    type: "post",
                    success: function (result) {
                        if (result.success) {
                            alert("博客已成功添加");
                            resetData();
                        }
                        else {
                            alert(result.errorInfo);
                        }
                    },
                    error: function () {
                        alert("数据返回错误");
                    }
                });
            }
        }

        function resetData() {
            var title = $("#title").val("");
            var blogTypeId = $("#blogTypeId").combobox("setValue", "");
            var content = UE.getEditor("editor").setContent("");
            var keyword = $("#keyWord").val("");
        }
    </script>

<body style="margin: 10px">
<div id="p" class="easyui-panel" title="编写博客" style="padding: 10px">
    <table cellspacing="20px">
        <tr>
            <td width="80px">博客标题：</td>
            <td>
                <input type="text" id="title" name="title" style="width: 400px"/>
                关键字：
                <input type="text" id="keyWord" name="keyWord" style="width: 400px"/>&nbsp;(多个关键字中间用空格隔开)

            </td>
        </tr>
        <tr>
            <td>所属类别：</td>
            <td>
                <select class="easyui-combobox" style="width: 154px" id="blogTypeId" name="blogType.id" editable="false"
                        panelHeight="auto">
                    <option value="">请选择博客类别...</option>
                    <c:forEach var="blogType" items="${blogTypes }">
                        <option value="${blogType.id }">${blogType.typename }</option>
                    </c:forEach>
                </select>
                <a href="javascript:submitData()" class="easyui-linkbutton"
                   data-options="iconCls:'icon-submit'">发布博客</a>
            </td>
        </tr>
        <tr>
            <td valign="top">博客内容：</td>
            <td>
                <div>
                    <script id="editor" name="content" type="text/plain"></script>
                </div>
            </td>
        </tr>
    </table>
</div>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('editor');
</script>
</body>