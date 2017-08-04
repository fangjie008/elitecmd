<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<link rel="stylesheet" href="<%=path%>/static/css/list/index.css?2">
<script type="text/javascript" src="<%=path%>/static/js/index.js"></script>
<title>首页</title>
</head>
<body>
<header class="header">推荐文章</header>
<div id="mainListContainer">

	<c:forEach items="${articles}" var="article">
		<section class="f_section">
			<a href="<%=path%>/home/detail?id=${article.id}">
				<h2>${article.title}</h2> <c:if
					test="${article.coverimglist!=null&&article.coverimglist.size()>0 }">
					<c:choose>
						<c:when test="${article.imgshowtype==1}">

							<div class="list-img-holder-large">
								<img data-src="${article.coverimglist.get(0) }"
									src='http://res.junshi.cn/img/common/imageholder.jpg' />
							</div>
						</c:when>
						<c:when
							test="${article.imgshowtype!=1&&article.coverimglist.size()>=3}">
							<div class="images-list-box">
								<ul>
									<c:forEach items="${article.coverimglist}" var="imgs">
										<li>
											<div class="image_container">
												<img data-src="${imgs}"
													src='http://res.junshi.cn/img/common/imageholder.jpg' />
											</div>
										</li>
									</c:forEach>
								</ul>
								<div class="clear"></div>
							</div>
						</c:when>
						<c:when test="${article.imgshowtype!=1&&article.coverimglist.size()<3}">

							<div class="single-img-box">
                        <img data-src="${article.coverimglist.get(0)}" src='http://res.junshi.cn/img/common/imageholder.jpg'/>
                    </div>
						</c:when>
					</c:choose>
				</c:if>
			</a>
			<div class="clear"></div>
		</section>
	</c:forEach>
</div>


</body>
</html>