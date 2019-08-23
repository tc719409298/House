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
    <script language="JavaScript" src="js/house.js"></script>
    <script language="JavaScript">
        $(function(){  //加载事件
            //1.发送异步请求获取类型，进行显示
            $.post("getAllType",null,function (data) {
                for(var i=0;i<data.length;i++){
                    //创建节点
                    var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                    //追加节点
                    $("#typeId").append(node);
                }

            },"json");

            //1.发送异步请求获取区域，进行显示
            $.post("page/getAllDistrict",null,function (data) {
                for(var i=0;i<data.length;i++){
                    //创建节点
                    var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                    //追加节点
                    $("#district_id").append(node);
                }

                loadStreet();  //加载街道
            },"json");

            //给区域添加改变事件
            $("#district_id").change(loadStreet);

            //加载街道   代码复用
            function loadStreet(){
                //获取区域编号
                var did=$("#district_id").val();
                //发送异步请求加载街道数据
                //清空原有数据项
                $("#street_id>option").remove();
                $.post("page/getAllStreetByDid",{"did":did},function (data) {
                    for(var i=0;i<data.length;i++){
                        //创建节点
                        var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                        //追加节点
                        $("#street_id").append(node);
                    }

                },"json");
            }

        });
    </script>



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
            href="javascript:updateMoreState()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量审核 </a>
            标题：<input type="text" name="title">
            房屋类型：<select name="typeId" id="typeId">
        <option value="0">--请选择-</option>
    </select>
            区域：<select name="district_id" id="district_id"></select>
             街道：<select name="streetId" id="street_id"></select>

       <a href="javascript:updateMoreState()" class="easyui-linkbutton"
        iconCls="icon-search" plain="true">查询 </a>

</div>
<!--添加对话框 -->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;"
     closed="true"  minimizable="true" >
    <form id="addDialogForm" method="post">
        <table>
            <tr>
                <td>区域名称:</td>
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
                <td>区域名称:</td>
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
<!--查看街道对话框 -->
<div id="ShowStreetDialog" class="easyui-dialog" buttons="#ShowStreetDialogButtons"
     style="width:600px; height: 350px; padding: 10px 20px;"
     closed="true"  minimizable="true" >
    <!--显示所有街道-->
    <table id="dgStreet"></table>
    <br>
    <form method="post" action="addStreet" id="StreetForm" name="form1">
    街道名称:<input type="text" name="name" id="StreetName">
        <input type="hidden" name="districtId" id="dis" >
    <input type="button" value="添加">
</form>

</div>
<!-- 添加查看街道对话框保存和取消按钮-->
<div id="ShowStreetDialogButtons">
    <a href="javascript:SaveStreetDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseStreetDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>
