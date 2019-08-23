<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>区域管理</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/users.js"></script>
</head>
<body>
<!--显示所有区域-->
<table id="dg"></table>
<!--工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:AddUsers()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteMoreType()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量删除</a>
            姓名：<input type="text" name="name" id="name1">
            电话：<input type="text" name="telephone" id="tel1">
        <a href="javascript:userSearch()" class="easyui-linkbutton"
                iconCls="icon-search" plain="true">查询</a>
    </div>
</div>
<!--添加对话框 -->
<div id="AddUsersDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 500px; height: 350px; padding: 10px 20px;"
     closed="true"  minimizable="true" >
    <form id="addDialogForm" method="post">
        <table>
            <tr>
                <td>姓名:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname" /></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="password" id="psw1" /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="telephone" id="telephone1" /></td>
            </tr>
        </table>
    </form>
</div>
<!-- 添加对话框保存和取消按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveUsersDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<!--添加修改对话框 -->
<div id="updateUsersDialog" class="easyui-dialog" buttons="#updateDialogButtons"
     style="width: 500px; height: 350px; padding: 10px 20px;"
     closed="true"  minimizable="true" >
    <form id="updateDialogForm" method="post">
        <table>
            <tr>
                <td>姓名:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" /></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="password"  /></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="telephone"  /></td>
            </tr>
        </table>
    </form>
</div>
<!-- 添加修改对话框保存和取消按钮-->
<div id="updateDialogButtons">
    <a href="javascript:SaveUsersDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
        class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


</body>
</html>
