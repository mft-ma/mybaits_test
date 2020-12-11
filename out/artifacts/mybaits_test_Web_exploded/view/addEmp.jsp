<%--
  Created by IntelliJ IDEA.
  User: mft
  Date: 2020/9/30
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>save</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/layui.css">
</head>
<script  src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(function () {

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
    });
</script>
<body>
<form action="${pageContext.request.contextPath}/mybatis/login/addEmp" method="post" enctype="multipart/form-data">
    <center>
        <table>
            <tr><td>
                姓名：<input type="text" name="name" class="layui-form-text"><br>
            </td></tr>
            <tr><td>
                性别：<%--<input type="text"  name="gender" class="layui-form-text">--%>
                <input type="radio" name="gender" value="M">男
                <input type="radio" name="gender" value="F">女<br>
            </td></tr>
            <tr><td>
                出生日期：<input type="date"  name="birthday" ><br>
            </td></tr>
            <tr><td>
                工资：<input type="text"  name="salary" class="layui-form-text"><br>
            </td></tr>
            <tr><td>
                图片：<input type="file"  name="file"><br>
            </td></tr>
            <tr><td>
                入职日期：<input type="date" name="entryDate"><br>
            </td></tr>
            <tr><td>
                部门：<select id="did"  name="did" style="width:160px;heigth:60px" class="layui-form-select"></select><br>
            </td></tr>
            <tr><td>
                备注：<input type="text" name="resume" class="layui-form-text"><br>
            </td></tr>
            <tr><td>
                <input type="submit"  value="添加" class="layui-btn">
            </td></tr>
        </table>
    </center>
</form>
</body>
</html>
