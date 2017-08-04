<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<link rel="stylesheet" href="<%=path%>/static/css/detail/detail.css?3">
</head>
<body>
<article class="article-container">
    <h1>${detail.title}</h1>
    <div class="content-subtitle"><span>${detail.showtime}</span> <span class="fr">${detail.viewcount} 条评论</span></div>
    <div class="box-h20"></div>
    <div class="content-main">${detail.originalcontent}
    </div>
</article>
</body>
</html>