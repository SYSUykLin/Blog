<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/ueditor/gbk-jsp/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/static/ueditor/gbk-jsp/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
</script>
<script>
    function loadimage() {
        document.getElementById("randImage").src = "${pageContext.request.contextPath}/image.jsp?" + Math.random();
    }

    function submitData() {
        var content = $("#content").val();
        var image = $("#imageCode").val();
        if (content == null || content == "") {
            window.alert("请输入评论内容");
        } else if (image == null || image == "") {
            window.alert("请输入验证码");
        }
        else {
            $.ajax({
                url: "${pageContext.request.contextPath}/comment/save.do",
                dataType:"json",
                data: {"content": content, "imageCode": image, "blogId":${blog.id}},
                type: "post",
                success: function (result) {
                    if (result.success) {
                        alert("评论已提成成功，审核通过后显示！");
                        $("#content").val("");
                        $("#imageCode").val("");
                    } else {
                        alert(result.errorInfo);
                    }
                },
                error:function() {
                    alert("json数据出问题");
                }
            });
        }
    }
</script>
<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/blog_show_icon.png"/>
        博客信息
    </div>
    <div>
        <div class="blog_title"><h3><strong>${blog.title }</strong></h3></div>

        <div class="blog_share">
            <div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博"
                                                                                      class="bshare-sinaminiblog"></a><a
                    title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a
                    title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台"
                                                                    class="bshare-more bshare-more-icon more-style-addthis"></a><span
                    class="BSHARE_COUNT bshare-share-count">0</span></div>
            <script type="text/javascript" charset="utf-8"
                    src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script>
            <script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
        </div>

        <div class="blog_info">
            发布时间：『 <fmt:formatDate value="${blog.releasedate }" type="date" pattern="yyyy-MM-dd HH:mm"/>』&nbsp;&nbsp;博客类别：${blog.blogType.typename }&nbsp;&nbsp;阅读(${blog.clickhie })
            评论(${blog.replyhit })
        </div>
        <div class="blog_content">
            ${blog.context }
        </div>
        <div class="blog_keyWord">
            <font><strong>关键字：</strong></font>
            <c:choose>
                <c:when test="${keywords==null }">
                    &nbsp;&nbsp;无
                </c:when>
                <c:otherwise>
                    <c:forEach var="keyWord" items="${keywords }">
                        &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" target="_blank">${keyWord }</a>&nbsp;&nbsp;

                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="blog_lastAndNextPage">
            ${pageCode }
        </div>
        <div class="blog_title">
            <img src="${pageContext.request.contextPath}/static/images/comment_icon.png">评论信息
        </div>
        <div class="commentDatas" style="text-align: center;">
            <c:choose>
                <c:when test="${commentList.size()==0 }">
                    暂无评论
                </c:when>
                <c:otherwise>
                    <c:forEach var="comment" items="${commentList}" varStatus="status">
                        <c:choose>
                            <c:when test="${status.index < 5 }">
                                <div class="comment" style="text-align: left">
                                    <span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate
                                            value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="otherComment" style="text-align: left">
                                    <span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate
                                            value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <c:if test="${commentList.size() > 5}">
                <button class="btn btn-primary" id="show" style="">显示余下评论</button>
                <script>
                    $("#show").click(function () {
                        if ($(".otherComment").css("display") == "none") {
                            $(".otherComment").css("display", "block");
                        }
                        else {
                            {
                                $(".otherComment").css("display", "none");
                            }
                        }
                    })
                </script>
            </c:if>
        </div>
    </div>

    <div class="data_list">
        <div class="data_list_title">
            <img src="${pageContext.request.contextPath}/static/images/publish_comment_icon.png"/>
            发表评论
        </div>
        <div class="publish_comment">
            <div>
                <textarea style="width: 100%" rows="3" id="content" name="content" placeholder="来说两句吧..."></textarea>
            </div>
            <div class="verCode">
                验证码：<input type="text" value="" name="imageCode" id="imageCode" size="10"
                           onkeydown="if(event.keyCode==13)form1.submit()"/>&nbsp;<img onclick="javascript:loadimage();"
                                                                                       title="换一张试试" name="randImage"
                                                                                       id="randImage" src="/image.jsp"
                                                                                       width="60" height="20" border="1"
                                                                                       align="absmiddle">
            </div>
            <div class="publishButton">
                <button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
            </div>
            </form>
        </div>
    </div>
</div>
