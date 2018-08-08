<%@include file="../taglib/indexHeadTag.jsp" %>
<ul class="list-unstyled list-inline right" id="pagination">
	<li><a class="page page-first">${fnc:getFirst()}</a></li>
	<li><a class="page page-pre">${fnc:getPre()}</a></li>
	<li><a class="page page-next">${fnc:getNext()}</a></li>
	<li><a class="page page-last">${fnc:getLast()}</a></li>
</ul>
