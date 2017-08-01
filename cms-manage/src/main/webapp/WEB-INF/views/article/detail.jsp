<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>新增文章</title>
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
			<span class="layui-breadcrumb"> <a href="javascript:;">文章信息</a> <a><cite> <c:if test="${article==null}">新增</c:if> <c:if test="${article!=null}">修改</c:if>
				</cite></a>
			</span>
		</legend>
	</fieldset>
	
<div style="padding:9px 15px;">
    <h4 style="padding:9px 15px;">上传封面图</h4>
	<img style="padding:9px 0px 9px 15px;" width="140" height="90" id="img_show1" />&nbsp;
	<img width="140" height="90" id="img_show2" />&nbsp;
	<img width="140" height="90" id="img_show3" />
	
	
</div>	
	<form class="layui-form" method="post">
		<div>
		<table style="padding:9px 15px;">
        <tr>
            <td style=" text-align: right;">图片1：</td>
            <td><input type="text" readonly="readonly" id="input_image1" /></td>
            <td><input type="file" id="input_file1" /></td>
        </tr>
        <tr>
            <td style=" text-align: right;">图片2：</td>
             <td><input type="text" readonly="readonly" id="input_image2" /></td>
            <td><input type="file" id="input_file2" /></td>
        </tr>
        <tr>
            <td style=" text-align: right;">图片3：</td>
             <td><input type="text" readonly="readonly" id="input_image3" /></td>
            <td><input type="file" id="input_file3" /></td>
        </tr>
        <tr>
            <td>封面类型:</td>
            <td>
                <select id="select_ImgShowType" name="select_ImgShowType" class="form-control" style="WIDTH: 150px">
                    ${imgType}
                </select>
            </td>
            <td>
                <input type="button" value="&nbsp;上 &nbsp;&nbsp;传&nbsp;" onclick="updateImg()" class="class="layui-btn layui-btn-primary" id="btn_upload" />
            </td>
        </tr>
    </table>
		</div>
	    <input type="hidden" id="cover_ImgDefault" name="cover_ImgDefault" value="${imgDefault}" />
		<div class="layui-form-item">
			<label class="layui-form-label">Id： </label>
			<div class="layui-input-inline">
				<input type="text" id="id" name="id" value="${article.id}" readonly="true" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">标题： </label>
			<div class="layui-input-block">
				<input type="text" id="title" name="title" value="${article.title}" autocomplete="off" placeholder="请输入标题" lay-verify="required" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class='layui-form-label'>文章类别：</label>
			<div class="layui-input-inline">
			<select id="categoryid" name="categoryid" lay-verify="required" lay-search="">
             ${categoryType}
            </select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">简介： </label>
			<div class="layui-input-block">
			 <textarea placeholder="请输入内容" id="intro" lay-verify="required" name="intro" value="${article.intro}" class="layui-textarea">${article.intro}</textarea>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class='layui-form-label'>正文</label>
			<div class="layui-input-block">
				<script id="container"  style="width:800px;height:700px;" name="content" type="text/plain"></script>
				 <input type="hidden" id="bodyContent" name="bodyContent" value="${fn:escapeXml(article.originalContent)}" />
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class='layui-form-label'>原文链接</label>
			<div class="layui-input-inline">
				<input type="text" name="originalurl" value="${article.originalurl}" autocomplete="off" placeholder="请输入原文链接" lay-verify="required" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class='layui-form-label'>浏览量</label>
			<div class="layui-input-inline">
				<input type="text" name="viewcount" value="${article.viewcount}" autocomplete="off" placeholder="请输入浏览量" lay-verify="number" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
				<c:if test="${article==null}">
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</c:if>
				<button type="reset" onclick="javascript:history.go(-1);" class="layui-btn layui-btn-primary btn-back">返回</button>
			</div>
		</div>
		
	</form>
<script type="text/javascript">
var ue = UE.getEditor('container');
ue.addListener("ready", function () {
	ue.setContent($("#bodyContent").val(), false);
});
$(document).ready(function () {

    var imgstring = '${article.coverimgs}';
    if(imgstring!=''){
	    var imgs = JSON.parse(imgstring);
	    if (imgs!=undefined&&imgs.length == 1) {
	        $('#input_image1').val(imgs[0]);
	        $("#img_show1").attr("src", imgs[0]);
	    } else if (imgs.length == 2) {
	        $('#input_image1').val(imgs[0]);
	        $('#input_image2').val(imgs[1]);
	        $("#img_show1").attr("src", imgs[0]);
	        $("#img_show2").attr("src", imgs[1]);
	    } else if (imgs.length >= 3) {
	        $('#input_image1').val(imgs[0]);
	        $('#input_image2').val(imgs[1]);
	        $('#input_image3').val(imgs[2]);
	        $("#img_show1").attr("src", imgs[0]);
	        $("#img_show2").attr("src", imgs[1]);
	        $("#img_show3").attr("src", imgs[2]);
	    }
    }
});
function updateImg(){
	 var data = undefined;
    var file1 = $("#input_file1").get(0).files;
    var file2 = $("#input_file2").get(0).files;
    var file3 = $("#input_file3").get(0).files;

    if (file1.length > 0 || file2.length > 0 || file3.length > 0) {

        data = new FormData();
        data.append("select_ImgShowType", $("#select_ImgShowType").val());

        if (file1.length > 0) data.append("img1", file1[0]);
        if (file2.length > 0) data.append("img2", file2[0]);
        if (file3.length > 0) data.append("img3", file3[0]);

    } else {
        alert("逗我呢！");
    }

    if (data) {
        _loading('图片上传中', 'wait');
        $.ajax({
            url: '<%=path%>/article/updateImg',
            type: 'POST',
            contentType: false,
            processData: false,
            dataType: "json", 
            data: data,
            success: function (data) {

                $('#input_file1').val("");
                $('#input_file2').val("");
                $('#input_file3').val("");

                if (data.img1) {
                    $('#input_image1').val(data.img1);
                    $("#img_show1").attr("src", data.img1);
                }

                if (data.img2) {
                    $('#input_image2').val(data.img2);
                    $("#img_show2").attr("src", data.img2);
                }
                if (data.img3) {
                    $('#input_image3').val(data.img3);
                    $("#img_show3").attr("src", data.img3);
                }
                _stop();
                alert("上传完成");
            },
            error:function(data){
                _stop();
           	 alert("上传失败");
            }
        });
    }
}


layui.use(['form','element','layer'], function(){
    var $ = layui.jquery,element = layui.element,layer = layui.layer;
    
    $(".layui-form").submit(function(){
     var isSubmitting = false; 		
     if (isSubmitting) {
         alert('正在提交,请不要重复提交!');
         return;
     }
     isSubmitting = true;

     var str = ue.getContent();
     if (str == undefined || str.length == 0) {
         alert("正文不能为空");
         return false; 
     }

       var a = [];
       if ($('#input_image1').val()) a.push($('#input_image1').val());
       if ($('#input_image2').val()) a.push($('#input_image2').val());
       if ($('#input_image3').val()) a.push($('#input_image3').val());
       $("#cover_ImgDefault").val(JSON.stringify(a));
       $("#bodyContent").val(ue.getContent());
   	 $(this).ajaxSubmit({
   		url:'<%=path%>/article/save',
   		type:"POST",
   		dataType:"json",
       	success:function(data){
       		if(data.ok){
       			alert(data.msg);
       			location.href = '<%=path%>/article/list?pindex='+"${pindex}";
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
