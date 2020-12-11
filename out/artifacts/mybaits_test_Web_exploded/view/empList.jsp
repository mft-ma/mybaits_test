<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mft
  Date: 2020/9/30
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script  src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">

    /*查询所有部门，放入下拉框*/
    $.ajax({
        type:'post',
        url:'${pageContext.request.contextPath}/mybatis/deptList',
        dataType:"json",
        success:function (data) {
            var options="";
            options="<option value='0'>请选择</option>";
            $.each(data,function (i,n) {
                options+=("<option value='"+n.id+"'>"+n.name+"</option>");
            })
            $("#did").html(options);
        },
        error : function(data) {
            alert("error")
        }
    });

    $(function () {
        /*添加功能  跳转页面*/
        $('#add').click(function () {
            location.href='${pageContext.request.contextPath}/view/addEmp.jsp';
        });

        var i=0;
        //全选
        $('#checkAll').on("click",function(){
            if(i==0){
                //把所有复选框选中
                $('.check_del').prop("checked",true);
                i=1;
            }else{
                $('.check_del').prop("checked",false);
                i=0;
            }
        });

        //反选
        $("#checkReverse").on("click",function(){
            $('.check_del').each(function(){
                //遍历所有复选框，然后取值进行 !非操作
                $(this).prop("checked", !$(this).prop("checked"));
            })
        });

    })
</script>
<html>
<head>
    <title>emp</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui.css">
</head><%--http://www.taoy.icu/student/layui-v2.5.6/layui/css/layui.css--%>
<body>

<center>
    <form action="${pageContext.request.contextPath}/mybatis/empAll" method="get">
        请输入：<input type="text" id="name" name="name"  onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入用户名">
        &nbsp;&nbsp;&nbsp;
        请选择：<select id="did" name="did" class="layui-select"></select>&nbsp;&nbsp;&nbsp;
        请选择：<input type="radio" value="M" name="gender" class="layui-radio-disbaled">男  <input type="radio" value="F" name="gender">女
        &nbsp;&nbsp;&nbsp;
        <input type="submit" value="查询" id="sel" class="layui-btn">
    </form>
</center>
<form action="${pageContext.request.contextPath}/mybatis/login/delBatch" method="post">
<%--    <input type="hidden" name="_method" value="DELETE" />--%>
    <input type="submit" id="delBatch" value="批量删除" class="layui-btn" style="margin-left: 50px;">
    <br><br><table class="layui-table">
    <thead>
    <tr>
        <th>
            <input type="checkbox" name="checkAll" id="checkAll">全选
            <input type="checkbox" id="checkReverse" name="checkReverse">反选
        </th>
        <th>编号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>生日</th>
        <th>工资</th>
        <th>图片</th>
        <th>入职日期</th>
        <th>备注</th>
        <th>部门</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${list}" var="emp" varStatus="empid">
        <tr>
            <td><input type="checkbox" class="check_del" name="check_del" value="${emp.id}"></td>
            <td>${empid.index+1}</td>
            <td>${emp.name }</td>
            <td>${emp.gender=='M'?'男':'女' }</td>
            <td>${emp.birthday }</td>
            <td>${emp.salary }</td>
            <td><img src="/down/${emp.image}" width="50px" height="50px"/></td>
            <td>${emp.entryDate }</td>
            <td>${emp.resume }</td>
            <td>${emp.dept.name }</td>
            <td>
                <a href="${pageContext.request.contextPath}/mybatis/login/empInfo?id=${emp.id}" id="update" >修改</a>|
                <a href="${pageContext.request.contextPath}/mybatis/login/delEmp?id=${emp.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty(list)} ">
        <tr><td colspan="10">暂无数据</td></tr>
    </c:if>
</table><br>
    <input type="button" value="添加" id="add" class="layui-input">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<center>
    <c:choose>
        <c:when test="${!empty list }">
            <a href='${pageContext.request.contextPath }/mybatis/empAll?page=1'><font color="#AD9595">首页</font></a>
            &nbsp;
            <c:if test="${page != 1 }">
                <a href='${pageContext.request.contextPath }/mybatis/empAll?page=${page-1 }'><font color="#AD9595">上一页</font></a>
            </c:if>
            &nbsp;
            <c:if test="${page+1<=totalPage }">
                <a href='${pageContext.request.contextPath }/mybatis/empAll?page=${page+1 }'><font color="#AD9595">下一页</font></a>
            </c:if>
            &nbsp;
            <a href='${pageContext.request.contextPath }/mybatis/empAll?page=${totalPage }'><font color="#AD9595">尾页</font></a>
            &nbsp;
            <font color="red">${page}/${totalPage<=0?1:totalPage }</font>页&nbsp;&nbsp;&nbsp;
        </c:when>
    </c:choose>
</center>
</form>
</body>
</html>

