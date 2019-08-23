$(function(){
    //使用datagrid绑定数据库
    $('#dg').datagrid({
        url:'getUsers',
        title:"用户信息",
        pagination:true,
        pageSize:3,
        pageList:[3,6,10],
        toolbar:'#ToolBar',
        columns:[[
            {field:'ck',checkbox:true,width:100},
            {field:'id',title:'编号',width:100},
            {field:'name',title:'用户',width:100},
            {field:'telephone',title:'电话',width:100},
            {field:'s',title:'操作',width:200,
                formatter: function(value,row,index){
                    //传统的同步请求
                    /*var str="<a href='delOneDistrict?id="+row.id+"'>删除</a>"
                    return str;*/
                    //异步请求
                    var str="<a href=\"javascript:delete1("+row.id+")\" class=\"easyui-linkbutton\"\n" +
                        "iconCls=\"icon-add\" plain=\"true\">删除</a>|<a href=\" javascript:updateUser("+row.id+")\" class=\"easyui-linkbutton\"\n" +
                        "            iconCls=\"icon-edit\" plain=\"true\" >修改</a>"
                    return str;
                }
            }
        ]]
    });
});
//传条件参数
function userSearch() {
    //取值
   var name= $("#name1").val()
    var telephone=$("#tel1").val()
    $('#dg').datagrid('load',{
        telephone: telephone,
        name: name
    });
}
//添加用户弹框
function AddUsers() {
    $("#AddUsersDialog").window("open").dialog("setTitle","新增用户")

}
//新增用户
function SaveUsersDialog() {
    alert(1111)
    $('#addDialogForm').form('submit', {
        url:"addUser",
        success:function(data){//返回的数据时字符串
            data=$.parseJSON(data)
            if (data.result==1) {
                $("#AddUsersDialog").dialog("close")
                $.messager.alert('提示','添加区域信息成功!','info');


            }else {
                $.messager.alert('提示','添加区域信息失败!','info');


            }

        }
    })

}
//修改用户
function updateUser(did) {
    alert(111)
    $("updateUsersDialog").dialog("open").datagrid("setTitle","修改用户")
   $.post("getOneUsers",{"id":did},function (data) {
       $("#updateDialogForm").form('load',data)
   },"json")
}