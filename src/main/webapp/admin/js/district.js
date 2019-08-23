$(function(){
    //使用datagrid绑定数据库
    $('#dg').datagrid({
        url:'getDistrictPage',
        title:"区域信息",
        pagination:true,
        pageSize:3,
        pageList:[3,6,10],
        toolbar:'#ToolBar',
        columns:[[
            {field:'ck',checkbox:true,width:100},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'区域名称',width:100},
            {field:'s',title:'操作',width:200,
                formatter: function(value,row,index){
                    //传统的同步请求
                    /*var str="<a href='delOneDistrict?id="+row.id+"'>删除</a>"
                    return str;*/
                    //异步请求
                    var str="<a href=\"javascript:delete1("+row.id+")\" class=\"easyui-linkbutton\"\n" +
                        "iconCls=\"icon-add\" plain=\"true\">删除</a>|<a href=\" javascript:ModifyBySelect()\" class=\"easyui-linkbutton\"\n" +
                        "  iconCls=\"icon-edit\" plain=\"true\" >修改</a>|<a href=\"javascript:showStreet("+row.id+")\" class=\"easyui-linkbutton\"\n" +
                        "  iconCls=\"icon-edit\" plain=\"true\">查看街道</a>"
                    return str;
                }
            }
        ]]
    });
});
/*打开添加的对话框 */
function Add() {
    $("#AddDialog").dialog("open").dialog("setTitle","新增 区域")
}
function CloseDialog() {
    $("#AddDialog").window("close")
}
/* 实现区域添加*/
function SaveDialog() {
    //使用esayui提交表
    $('#addDialogForm').form('submit', {
        url:"addDistrict",
        success:function(data){//返回的数据时字符串
            data=$.parseJSON(data)
            if (data.result==1) {
                $("#AddDialog").dialog("close")
                $.messager.alert('提示','添加区域信息成功!','info');


            }else {
                $.messager.alert('提示','添加区域信息失败!','info');


            }

        }
    });

}
<!--添加修改对话框  -->
/*打开修改的对话框 */
function ModifyBySelect() {
    var selectRows=$("#dg").datagrid("getSelections")
    if (selectRows.length==1) {
        $("#upDialog").dialog("open").dialog("setTitle","修改区域")
        $("#upDialogForm").form("load",selectRows[0])

    }else {
        $.messager.alert('提示','没有选中行数，或者选中了多个行数!','danger');


    }

}
function SaveupdateDialog() {
    //使用easyui提交修改表
    $("#upDialogForm").form('submit',{
        url:"updateDistrict",
        success:function (data) {
            data=$.parseJSON(data)
            if (data.result==1) {
                $("#upDialog").dialog("close")
                $.messager.alert('提示','修改区域信息成功!','info');
                $('#dg').datagrid('reload');

            }else {
                $("#upDialog").dialog("close")
                $.messager.alert('提示','修改区域信息失败!','error');
            }
        }
    })

}
//用异步请求方式删除单条信息
function delete1(obj) {
    alert(obj)
    $.messager.confirm('提示框', '确认删除数据吗?', function(r){
        if (r){
            $.post("delOneDistrict",{"id":obj},function (data) {
                if (data.result==1) {
                    $.messager.alert('提示','删除区域信息成功!','info');
                    $('#dg').datagrid('reload');
                }

            },"json")// exit action;
        }else {
            $.messager.alert('提示','删除区域信息失败!','info');
        }
    });



}
/*批量删除区域信息*/
function DeleteMoreDistrict() {
    //获取datagrid选中的行
    var selectRows=$("#dg").datagrid("getSelections")
    if (selectRows.length>0){
        $.messager.confirm('提示框', '确认删除吗?', function(r){
            if (r){
                var vel="";
                for (var i=0;i<selectRows.length;i++){
                    vel=vel+selectRows[i].id+",";
                }
                vel=vel.substring(0,vel.length-1)
            }
            $.post("delMoreDistrict",{"ids":vel},function (data) {
                if (data.result>0) {
                    $.messager.alert('提示','删除多项区域信息成功!','info');
                    $('#dg').datagrid('reload');
                }else {
                    $.messager.alert('提示','删除多项区域信息失败!','info');
                }

            },"json")
        });



    } else {
        $.messager.alert('提示','请选择你要要删除的行数!','info');
    }

}
//查看街道详情
function showStreet(did) {
    $("#ShowStreetDialog").dialog("open").dialog("setTitle","街道详情")
    $('#dgStreet').datagrid({
        url:'getStreetByDid?did='+did,
        title:"街道信息",
        pagination:true,
        pageSize:3,
        pageList:[3,6,10],
        /* toolbar:'#ToolBar',*/
        columns:[[
            {field:'ck',checkbox:true,width:100},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'街道名称',width:100},
            {field:'s',title:'操作',width:200,
                formatter: function(value,row,index){
                    //传统的同步请求
                    /*var str="<a href='delOneDistrict?id="+row.id+"'>删除</a>"
                    return str;*/
                    //异步请求
                    var str="<a href=\"javascript:delete1("+row.id+")\" class=\"easyui-linkbutton\"\n" +
                        "iconCls=\"icon-add\" plain=\"true\">删除</a>|<a href=\" javascript:ModifyBySelect()\" class=\"easyui-linkbutton\"\n" +
                        "  iconCls=\"icon-edit\" plain=\"true\" >修改</a>"
                    return str;
                }
            }
        ]]
    });

 }
 //关闭查看街道窗口
function CloseStreetDialog() {
    $("#ShowStreetDialog").window("close")
}
/* 实现街道添加*/
function SaveStreetDialog() {

    //使用esayui提交表
    $('#StreetForm').form('submit', {
        url:"addStreet",
        success:function(data){//返回的数据时字符串
            data=$.parseJSON(data)
            if (data.result==1) {
                $("#AddDialog").dialog("close")
                $.messager.alert('提示','添加街道信息成功!','info');
                $('#dgStreet').datagrid("reload")


            }else {
                $.messager.alert('提示','添加街道信息失败!','info');


            }

        }
    });

}