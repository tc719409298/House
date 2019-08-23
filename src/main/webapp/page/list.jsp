<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
  <script language="JavaScript">
    $(function(){  //加载事件
      //1.发送异步请求获取类型，进行显示
      $.post("getAllType",null,function (data) {
        for(var i=0;i<data.length;i++){
          //创建节点
          var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
          //追加节点
          $("#type_id").append(node);
        }

        //设置选中项
        $("#type_id").val(${condition.tid});
      },"json");

      //1.发送异步请求获取区域，进行显示
      $.post("getAllDistrict",null,function (data) {
        for(var i=0;i<data.length;i++){
          //创建节点
          var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
          //追加节点
          $("#district_id").append(node);
        }

        //设置选中项
        $("#district_id").val(${condition.did});

        loadStreet();  //加载街道
      },"json");

      //给区域添加改变事件
      $("#district_id").change(loadStreet);

      //加载街道   代码复用
      function loadStreet(){
        //获取区域编号
        var did=$("#district_id").val();
        if(did!="") {
          //发送异步请求加载街道数据
          //清空原有数据项
          $("#street_id>option:gt(0)").remove();
          $.post("getAllStreetByDid", {"did": did}, function (data) {

            for (var i = 0; i < data.length; i++) {
              //创建节点
              var node = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
              //追加节点
              $("#street_id").append(node);
            }


            //设置选中项
            $("#street_id").val(${condition.sid});
          }, "json");
        }
      }
      function goPage(pageNum) {
          var total=${housePageInfo.pages} //总页数
          if(pageNum>total){
              pageNum=total
          }
          if (pageNum<1){
              pageNum=1
          }
          var hd=$("#hd1").val(pageNum)
          $("#sform").submit()
      }
    })



  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>

  <FORM id=sform method=post action=getSelectHouse>
      <input type="hidden" name="page" value="1" id="hd1">
   标题：<input type="text" name="title" id="title1" size="4" value="${condition.title}">
    区域：<select name="did" id="district_id">
          <option value="">请选择</option>
         </select>
    街道：<select name="sid" id="street_id">
      <option value="">请选择</option>
         </select>
    房屋类型：<select name="tid" id="type_id">
            <option value="">请选择</option>
            </select>
    价格：<input type="text" name="startPrice" size="4" value="${condition.startPrice}">--
    <input type="text" name="endPrice" size="4" value="${condition.endPrice}">
    <input type="submit" value="查询">
  </FORM>
</DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach var="h" items="${housePageInfo.list}">
    <TR>
      <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></a></span></TD>
      <TD>
        <DL>
          <DT><A href="details.htm" target="_blank">${h.title}</A></DT>
          <DD>${h.dname}--${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
      <TD class=house-type>${h.tname}</TD>
      <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
    </TR>
  </c:forEach>



  <TR>无租房信息</TR></TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="#">上一页</A></LI>
  <LI><A href="#">下一页</A></LI>
  <LI><A href="javascript:goPage(${housePageInfo.pages})">末页</A></LI></UL><SPAN

class=total>${housePageInfo.pageNum}/${housePageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
