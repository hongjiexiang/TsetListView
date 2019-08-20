<%@ page import="Dao.ItemsDao" %>
<%@ page import="entity.Items" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static javax.print.attribute.standard.MediaSizeName.B" %><%--
  Created by IntelliJ IDEA.
  User: hjx
  Date: 2019/8/19
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<style type="text/css">
		div{
			float:left;
			margin-left: 30px;
			margin-right:30px;
			margin-top: 5px;
			margin-bottom: 5px;
		}
		div dd{
			margin:0px;
			font-size:10pt;
		}
		div dd.dd_name
		{
			color:blue;
		}
		div dd.dd_city
		{
			color:#000;
		}
	</style>

</head>
<body>
<center>
	<table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<%
				ItemsDao itemDao = new ItemsDao();
				Items item = itemDao.getItemsById(Integer.parseInt(request.getParameter("id")));
				if(item!=null)
				{
			%>

			<td width="70%" valign="top">
				<table>
					<tr>
						<td rowspan="4"><img src="image/<%=item.getPicture()%>" width="200" height="160"/></td>
					</tr>
					<tr>
						<td><B><%=item.getName() %></B></td>
					</tr>
					<tr>
						<td>产地：<%=item.getCity()%></td>
					</tr>
					<tr>
						<td>价格：<%=item.getPrice() %>￥</td>
					</tr>
				</table>
			</td>

			<%
				}
			%>
			<%
				String list ="";
				//从客户端获得Cookies集合
				Cookie[] cookies = request.getCookies();
				//遍历这个Cookies集合
				if(cookies!=null&&cookies.length>0)
				{
					for(Cookie c:cookies)
					{
						if(c.getName().equals("ListViewCookie"))
						{
							list = c.getValue();
						}
					}
				}

				list+=request.getParameter("id")+",";
				//如果浏览记录超过1000条，清零.
				String[] arr = list.split(",");
				if(arr!=null&&arr.length>0)
				{
					if(arr.length>=1000)
					{
						list="";
					}
				}
				Cookie cookie = new Cookie("ListViewCookie",list);
				response.addCookie(cookie);

			%>
			<!-- 浏览过的商品 -->
			<td width="30%" bgcolor="#EEE" align="center">
				<br>
				<b>您浏览过的商品</b><br>
				<!-- 循环开始 -->
					<%
                ArrayList<Items> itemlist = itemDao.getViewList(list);
                if(itemlist!=null&&itemlist.size()>0 )
                {
                   System.out.println("itemlist.size="+itemlist.size());
                   for(Items i:itemlist)
                   {

             %>
				<div>
					<dl>
						<dt>
							<a href="details.jsp?id=<%=i.getId()%>"><img src="image/<%=i.getPicture() %>" width="120" height="90" border="1"/></a>
						</dt>
						<dd class="dd_name"><%=i.getName() %></dd>
						<dd class="dd_city">产地:<%=i.getCity() %>&nbsp;&nbsp;价格:￥<%=i.getPrice() %> </dd>
					</dl>
				</div>
					<%
                   }
                }
			%>

				<!-- 循环结束 -->
			</td>
		</tr>
	</table>
</center>
</body>
</html>
