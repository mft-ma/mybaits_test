<%--
  Created by IntelliJ IDEA.
  User: mft
  Date: 2020/9/30
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>update</title>
</head>
<script  src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
$(function () {

        $.ajax({
            type: 'post',
            url:'${pageContext.request.contextPath}/mybatis/deptList',
            success:function (data) {
                var dept=$('#did').val();
                var options="";
                options="<option value='0'>请选择</option>";
                $.each(data,function (i,n) {
                    options+=("<option value='"+n.id+"'"+(n.id==dept?'selected':'')+">"+n.name+"</option>");
                })
                $("#did").html(options);
            },
            error:function () {
                alert('error');
            }
        });
    });
</script>
<body>
<form action="${pageContext.request.contextPath}/mybatis/login/updEmp" method="post" enctype="multipart/form-data">
<%--    <input type="hidden" name="_method" value="PUT" />--%>
    <center>
        <input type="hidden" id="id" name="id" value="${employee.id}">
        <table>
            <tr><td>
                姓名：<input type="text" id="name" name="name" value="${employee.name}">
            </td></tr>
            <tr><td>
                性别：<%--<input type="text" id="" name="gender" value="${employee.gender=='M'?'男':'女'}">--%>
                <select id="gender" name="gender" style="width:160px;heigth:60px">
                    <option value="M" ${employee.gender=='M'?'selected':''}>男</option>
                    <option value="F" ${employee.gender=='F'?'selected':''}>女</option>
                </select>
            </td></tr>
            <tr><td>
                出生日期：<input type="date" id="birthday" name="birthday" value="${employee.birthday}">
            </td></tr>
            <tr><td>
                工资：<input type="text" id="salary" name="salary" value="${employee.salary}">
            </td></tr>
            <tr><td>
                <c:if test="${!empty(employee) }">
                    原图片：<img src="${pageContext.request.contextPath}/down/${employee.image}" width="50px" height="50px" />
                    <input type='hidden' value='${employee.image }' name='image'><br>
                </c:if>
            </td></tr>
            <tr><td>
                图片：<input type="file" name="file" id="file">
            </td></tr>
            <tr><td>
                入职日期：<input type="date" id="entryDate" name="entryDate" value="${employee.entryDate}">
            </td></tr>
            <tr><td>
                部门：<select id="did" name="did" style="width:160px;heigth:60px" >
                        <option value="${employee.did}">请输入</option>
                     </select>
            </td></tr>
            <tr><td>
                备注：<input type="text" id="resume" name="resume" value="${employee.resume}">
            </td></tr>
            <tr><td>
                <input type="submit" id="upd" value="提交">
            </td></tr>
        </table>
    </center>
</form>
</body>
</html>
