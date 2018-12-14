<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
    #profile {
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
        var nickName=$("#nickname").val();
        var sign=$("#sign").val();
        var profile=UE.getEditor('profile').getContent();

        if(nickName==null || nickName==''){
            $.messager.alert("系统提示","请输入昵称！");
        }else if(sign==null || sign==''){
            $.messager.alert("系统提示","请输入个性签名！");
        }else if(profile==null || profile==''){
            $.messager.alert("系统提示","请输入个人简介！");
        }else{
            $("#pF").val(profile);
            $("#form").submit();
        }
    }
</script>

<body style="margin: 10px">
<div id="p" class="easyui-panel" title="修改个人信息" style="padding: 10px">
    <form  method="post" action="${pageContext.request.contextPath}/admin/blogger/save.do" enctype="multipart/form-data" id = "form">
    <table cellspacing="20px">
        <tr>
            <td width="100px">用户名：</td>
            <td>
                <input type="hidden" id="id" name="id" value="${currentUser.id}">
                <input type="text" id="username" name="username" style="width: 200px" value="${currentUser.username}"
                       readonly="readonly"/>
            </td>
        </tr>
        <tr>
            <td>昵称：</td>
            <td>
                <input type="text" id="nickname" name="nickname" style="width: 200px" value="${currentUser.nickname}"/>
            </td>
        </tr>
        <tr>
            <td>个性签名：</td>
            <td>
                <div>
                    <input type="text" id="sign" name="sign" style="width: 400px" value="${currentUser.sign}"/>
                </div>
            </td>
        </tr>
        <tr>
            <td>个人头像：</td>
            <td>
                <div>
                    <input type="file" id = "imageFile" name = "imageFile">
                    <a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a>
                </div>
            </td>
        </tr>

        <tr>
            <td valign="top">个人简介：</td>
            <td>
                <div>
                    <script id="profile" type="text/plain"></script>
                    <input type="hidden" id="pF" name="profile"/>
                </div>
            </td>
        </tr>
    </table>
    </form>
</div>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor("profile");
    ue.addListener("ready",function(){
        UE.ajax.request("${pageContext.request.contextPath}/admin/blogger/find.do",
            {
                method:"post",
                async:false,
                data:{},
                dataType:"json",
                onsuccess:function(result){
                    result=eval("("+result.responseText+")");
                    UE.getEditor("profile").setContent(result.profile);
                },
                onerror:function () {
                    alert("json传输错误");
                }
            });
    });
</script>
</body>