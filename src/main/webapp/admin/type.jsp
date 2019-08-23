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
    <script language="JavaScript" src="js/type.js"></script>
</head>
<body>
<!--显示所有区域-->
<table id="dg"></table>
<!--工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteMoreType()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量删除</a>
        <%--<a href="javascript:DeleteByGroupName()" class="easyui-linkbutton"
                iconCls="icon-cancel" plain="true">批量删除</a>--%>
    </div>
</div>
<!--添加对话框 -->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;"
     closed="true"  minimizable="true" >
    <form id="addDialogForm" method="post">
        <table>
            <tr>
                <td>房屋类型:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname" /></td>
            </tr>
        </table>
    </form>
</div>
<!-- 添加对话框保存和取消按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<!--修改对话框 -->
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;"
     closed="true"  minimizable="true" >
    <form id="upDialogForm" method="post">
        <table>
            <tr>
                <td>序号:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="id" id="id1" readonly /></td>
            </tr>
            <tr>
                <td>房屋类型:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="cname" /></td>
            </tr>
        </table>
    </form>
</div>
<!-- 添加修改对话框保存和取消按钮-->
<div id="upDialogButtons">
    <a href="javascript:SaveupdateDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>
