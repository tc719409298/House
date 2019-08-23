$(function(){
    //使用datagrid绑定数据库
    $('#dg').datagrid({
        url:'getNoPassHouse',
        title:"未审核房屋信息",
        pagination:true,
        pageSize:3,
        pageList:[3,6,10],
        toolbar:'#ToolBar',
        columns:[[
            {field:'ck',checkbox:true,width:100},
            {field:'id',title:'编号',width:100},
            {field:'title',title:'标题',width:100},
            {field:'dname',title:'区域',width:100},
            {field:'sname',title:'街道',width:100},
            {field:'tname',title:'房屋类型',width:100},
            {field:'floorage',title:'面积',width:100},
            {field:'contact',title:'联系电话',width:100},
            {field:'s',title:'操作',width:200,
                formatter: function(value,row,index){

                     //异步请求
                    var str="<a href='javascript:goPass("+row.id+")'>未审核</a>"
                    return str;
                }
            }
        ]]
    });

});
//修改出租房未审核状态
function goPass(id) {
    //跳转页面的方法
  /*  location.href="updateNoPassState?id="+id;*/
    //异步请求的方法
    $.post("updateNoPassState",{"id":id},function (data) {
         if (data.result==1) {
             //审核通过
             $("#dg").datagrid("load");
         }else {
             messager.alert("提示","未通过审核，审核失败","danger")
         }

    },"json")
    
}
//批量审核
function updateMoreState() {

    //获取 datagrid 选中的行数
    var selectRows=$("#dg").datagrid("getSelections")

    if (selectRows.length>0) {
        var vel=""
        for (var i=0;i<selectRows.length;i++){
            vel=vel+ selectRows[i].id+","
        }
        vel=vel.substring(0,vel.length-1)
        alert(vel)
        $.post("updateNoPassMoreState",{"ids":vel},function (data) {
            if (data.result==1){
                $('#dg').datagrid('load')
            }

        },"json")
    }else {
        $.messager.alert('提示','请选择你要审核的出租房信息!','info');
    }

}


