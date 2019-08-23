﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%
  Object o=session.getAttribute("logininfo");
  if(o==null) {
    out.print("<script>alert('你还没有登入，或者登入时间失效,请登入，不要试图访问');location.href='login.jsp';</script>");
  }
%>--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0050)http://localhost:8080/HouseRent/manage!list.action -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>青鸟租房 - 用户管理</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR >
  <%--  <script language="JavaScript">
       function goRecover(id) {
           $.post("recoverHouse",{"id":id},function (data) {
               if (data.result==1) {
                   alert("已恢复数据")
               }
           },"json")
       } 
    </script>--%>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV>

<DIV class=search>欢迎：${sessionScope.logininfo.name}<LABEL class="ui-green searchs"><a href="fabu.jsp" title="">发布房屋信息</a></LABEL>
<LABEL class=ui-green><INPUT onclick='document.location="index.jsp"' value="退       出" type=button name=search></LABEL>
</DIV></DIV>
<DIV class="main wrap">
<DIV id=houseArea>
<TABLE class=house-list>
  <TBODY>
 <c:forEach items="${pageInfo.list}" var="h">
     <TR>
         <TD class=house-thumb><SPAN><A href="details.htm" target="_blank">
             <img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></A></SPAN></TD>
         <TD>
             <DL>
                 <DT><A href="getSingleHouse?id=${h.id}" target="_blank">${h.title}</A></DT>
                 <DD>${h.dname}=${h.sname},房屋面积：${h.floorage}平方米<BR>联系方式：${h.contact},房屋类型：${h.tname}
                     <br>编号：${h.id}
                   </DD>
             </DL>
             </TD>
              <TD>
                  审核的状态：<c:choose>
                  <c:when test="${h.ispass==1}">已审核</c:when>
                  <c:otherwise>未审核</c:otherwise>
              </c:choose>
              </TD>
         <TD class=house-type><LABEL class=ui-green><INPUT  value="修    改" type=button name=search  onclick="location.href='getOneHouse?id=${h.id}'"></LABEL></TD>

         <TD class=house-price><LABEL class=ui-green><INPUT value="删除" type=button name=search onclick="return confirm('确认删除吗？'),location.href='delHouse?id=${h.id}'"></LABEL></TD>
         <%--<TD class=house-price><LABEL class=ui-green><INPUT value="恢复" type=button name=recover onclick="goRecover(${h.id})"></LABEL></TD>--%>
     </TR>
 </c:forEach>
  </TBODY>
</TABLE>
</DIV>
<DIV class=pager>
<UL>
    <c:forEach var="i" begin="1" end="${pageInfo.pages}" step="1">
        <LI class=current>
            <A id=widget_338868862
               href="getHouseByUid?page=${i}"
               parseContent="true" showError="true" targets="houseArea"
               ajaxAfterValidation="false" validate="false"
               dojoType="struts:BindAnchor">${i}</A>
        </LI>
    </c:forEach>
</UL>
    <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
