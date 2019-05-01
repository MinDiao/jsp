<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="data_list">
	<div>
		<div class="news_title"><h3>${news.title }</h3></div>
		<div class="news_info">
			发布时间：[<fmt:formatDate value="${news.publishDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>]&nbsp;&nbsp;
			作者：${news.author }
			&nbsp;&nbsp;新闻类别：娱乐新闻&nbsp;&nbsp;阅读次数：${news.click }
		</div>
		<div class="news_content">
			${news.content }
		</div>
		
		
		<div class="upDownPage">
			
		</div>
	</div>
</div>