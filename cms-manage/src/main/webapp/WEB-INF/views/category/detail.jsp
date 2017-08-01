<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>新增分类</title>
<script src="<%=path%>/ueditor/ueditor.config.js"></script>
<script src="<%=path%>/ueditor/ueditor.all.min.js"></script>
<script src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">

</script>
<style>
table tr  {
	padding:9px 15px;
}
table {
  max-width: 100%;
  background-color: transparent;
}

th {
  text-align: left;
}
td {
    padding:9px 15px;
    border: 0 none;
}
</style>
</head>
<body id="wait" class="body">
	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			<span class="layui-breadcrumb"> <a href="javascript:;">分类信息</a> <a><cite> <c:if test="${category==null}">新增</c:if> <c:if test="${category!=null}">修改</c:if>
				</cite></a>
			</span>
		</legend>
	</fieldset>

	<form class="layui-form" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">Id： </label>
			<div class="layui-input-inline">
				<input type="text" id="id" name="id" value="${category.id}" readonly="true" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">名称： </label>
			<div class="layui-input-block">
				<input type="text" id="name" name="name" value="${category.name}" autocomplete="off" placeholder="请输入名称" lay-verify="required" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">简介： </label>
			<div class="layui-input-block">
			 <textarea placeholder="请输入简介" id="intro"  name="intro" value="${category.intro}" class="layui-textarea">${category.intro}</textarea>
			</div>
		</div>
		

		
		<div class="layui-form-item">
			<label class='layui-form-label'>备注</label>
			<div class="layui-input-block">
			 <textarea placeholder="请输入简介" id="remark"  name="remark" value="${category.remark}" class="layui-textarea">${category.remark}</textarea>
			</div>
		</div>		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
				<c:if test="${category==null}">
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</c:if>
				<button type="reset" onclick="javascript:history.go(-1);" class="layui-btn layui-btn-primary btn-back">返回</button>
			</div>
		</div>
		
	</form>
<script type="text/javascript">
layui.use(['form','element','layer'], function(){
    var $ = layui.jquery,element = layui.element,layer = layui.layer;
    
    $(".layui-form").submit(function(){
   	 $(this).ajaxSubmit({
   		url:'<%=path%>/category/save',
   		type:"POST",
   		dataType:"json",
       	success:function(data){
       		if(data.ok){
       			alert(data.msg);
       			location.href = '<%=path%>/category/list?pindex='+"${pindex}";
       		}else{
       			alert(data.msg);
       			//登录超时
       			if(data.loginStatus!=undefined&&data.loginStatus=="-1"){
       				location.href = '<%=path%>/';
       			}
       		}
       	
       	},
       	error:function(data){
       		alert('保存失败');
       	}
       	});   
        return false;   //防止表单自动提交  
   });  
   
});
   
</script>
</body>
</html>
