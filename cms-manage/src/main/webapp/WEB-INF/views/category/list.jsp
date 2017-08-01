<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<title>分类列表</title>
</head>
<body class="body">
	<fieldset class="layui-elem-field layui-field-title">
		<legend>
			<span class="layui-breadcrumb"> <a href="javascript:;">分类列表</a> <a><cite>查看</cite></a>
			</span>
		</legend>
	</fieldset>

	<div class="my-btn-box">
		<span class="f1"> <a class="layui-btn btn-add btn-default" href="<%=path%>/category/detail">新增分类</a>
		</span>
	</div>
	<table id="dateTable" class="layui-table">
		<thead>
			<tr>
				<th>ID</th>
				<th>分类名称</th>
				<th>简介</th>
				<th>备注</th>
				<th>新增时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categorys}" var="info">
			     <c:choose>
						<c:when test="${info.status==1}">
							<tr >
						</c:when>
						<c:otherwise>
							<tr style="background-color: #f9d8d8">
						</c:otherwise>
				 </c:choose>
			
				    <td>${info.id}</td>
					 <td>${info.name}</td>
					<td>${info.intro}</td>
					<td>${info.remark}</td>
					<td>${info.createtime}</td>
					<td>${info.statusname}</td>
					<td>
						<button class="layui-btn layui-btn-small layui-btn-normal" onclick="edit('${info.id}')">编辑</button>
						<c:choose>
						<c:when test="${info.status==1}">
						<button class="layui-btn layui-btn-small layui-btn-danger" onclick="deleteBaseInfo('${info.id}')">删除</button>
						</c:when>
						<c:otherwise>
						<button class="layui-btn layui-btn-small layui-btn-danger" onclick="recover('${info.id}')">恢复</button>
						</c:otherwise>
						</c:choose>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="layui-form">
		<span id="form_page"></span> &nbsp;&nbsp;每页&nbsp;${paging.psize}&nbsp;行,共&nbsp;${paging.ptotalpages}&nbsp;页,共&nbsp;${paging.pcount}&nbsp;条数据
	</div>
	<script type="text/javascript">
//当前页   
var pindex=1;
    layui.use(['element','layer','laypage'], function(){
        var $ = layui.jquery,element = layui.element,layer = layui.layer;
        var laypage = layui.laypage;
        pindex = "${paging.pindex}";// 当前页
        var ptotalpages = "${paging.ptotalpages}";// 总页数
        var pcount = "${paging.pcount}";// 总记录数
        var psize = "${paging.psize}";// 每一页的记录数
        // 分页
        laypage({
            cont : 'form_page', // 页面上的id
            pages : ptotalpages,//总页数
            curr : pindex,//当前页。
            skip : true,
            jump : function(obj, first) {
                $("#pindex").val(obj.curr);//设置当前页
                //防止无限刷新,
                //只有监听到的页面index 和当前页不一样是才出发分页查询
                if (pindex!=""&&pindex!="0"&&(obj.curr != pindex)) {
                	location.href='<%=path%>/category/list?pindex='+obj.curr;
                }
            }
        });
    });

    function edit(id){
    	location.href='<%=path%>/category/detail?id='+id+'&pindex='+pindex;
    }
    function deleteBaseInfo(id){
    	$.ajax({
    		url:'<%=path%>/category/deleteModel?id='+id
    	    ,type:"post"
    	    ,dataType:"json"
    	    ,success:function(data){
    	    	if(data.ok){
        			alert(data.msg);
        			location.href ='<%=path%>/category/list?pindex='+pindex;
        		}else{
        			alert(data.msg);
        			//登录超时
        			if(data.loginStatus!=undefined&&data.loginStatus=="-1"){
        				location.href = '<%=path%>/';
						}
					}
				},
				error : function(e) {
					alert('保存失败');
				}

			});
		}
    function recover(id){
    	$.ajax({
    		url:'<%=path%>/category/recover?id='+id
    	    ,type:"post"
    	    ,dataType:"json"
    	    ,success:function(data){
    	    	if(data.ok){
        			alert(data.msg);
        			location.href ='<%=path%>/category/list?pindex='+pindex;
        		}else{
        			alert(data.msg);
        			//登录超时
        			if(data.loginStatus!=undefined&&data.loginStatus=="-1"){
        				location.href = '<%=path%>/';
						}
					}
				},
				error : function(e) {
					alert('保存失败');
				}

			});
		}
	</script>
</body>
</html>