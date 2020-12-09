<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生列表</title>
<script src="js/jquery-3.4.1.js"></script>
<script>
$(function(){
	/*给班级下拉列表赋值  */
	$.getJSON("doinit_Student.do",function(lsca){
		for(var i=0;i<lsca.length;i++){
			var ca=lsca[i];
			$("#classid").append("<option value="+ca.cid+">"+ca.cname+"</option>");
		}
	});
	showAll();
});
var page=1;
var rows=5;
var maxpage=1;
function showAll(){
	$.getJSON("findPageAll_Student.do?page="+page+"&rows="+rows,function(jsonpagebean){
		page=jsonpagebean.page;
		rows=jsonpagebean.rows;
		maxpage=jsonpagebean.maxpage;
		var lsst=jsonpagebean.pagelist;
		var tablehead="<table width='810' border='1' align='center' cellpadding='1' cellspacing='0'>"
		   +"<tr align='center' bgcolor='#FFFFCC'>"
		   +"<td>编号</td>"
		   +"<td>姓名</td>"
		   +"<td>性别</td>"
		   +"<td>地址</td>"
		   +"<td>生日</td>"
		   +"<td>班级</td>"
		   +"<td>操作</td>"
		   +"</tr>";
	var trs="";
		  for(var i=0;i<lsst.length;i++){
			  var st=lsst[i];
			  trs+="<tr align='center'>"
			    +"<td>"+st.stuid+"</td>"
			    +"<td>"+st.sname+"</td>"
			    +"<td>"+st.sex+"</td>"
			    +"<td>"+st.address+"</td>"
			    +"<td>"+st.sdate+"</td>"
			    +"<td>"+st.cname+"</td>"
			    +"<td><input type='button' value='删除' onclick='delById("+st.stuid+")' /><input type='button' value='修改'  onclick='findById("+st.stuid+")'/></td>"
			   +"</tr>"
		  }
		  var enstable=tablehead+trs+"</table>"
		  $("#mytable").html(enstable);
		  /* 给分页组件赋值 */
		  $("#rows").val(rows);
		  $("#page").val(page);
		  $("#pagelable").html(page+"/"+maxpage);
	});
};
/* 给分页组件赋值 */
$(function(){
	$("#btfirst").click(function(){
		page=1;
		showAll();
	});
	$("#btup").click(function(){
		page=page-1;
		if(page<1){
			page=1;
		}
		showAll();
	});
	$("#btnext").click(function(){
		page=page+1;
		if(page>maxpage){
			page=maxpage;
		}
		showAll();
	});
	$("#btlast").click(function(){
		page=maxpage;
		
		showAll();
	});
});
/* 给分页组件赋值end */
/* 学生管理 */
 $(function(){
	 $("#btsave").click(function(){
		 //获取页面数据
		 var sname=$("#sname").val();
		 var sex=$(":radio[name='sex']:checked").val();
		 var address=$("#address").val();
		 var sdate=$("#sdate").val();
		 var classid=$("#classid").val();
		 //将获取数据转换成json
		 var jsondata={
				    'sname':sname,
				    'sex':sex,
				    'address':address,
				    'sdate':sdate,
				    'classid':classid
		           };
		 $.post('save_Student.do',jsondata,function(code){
			 if(code=='1'){
				 alert("添加成功");
				 showAll();
			 }else{
				 alert("添加失败");
			 }
		 });
	 });
 });
/* 学生管理end */
 
function findById(stuid){
	$.getJSON('findById_Student.do?sid='+stuid,function(oldst){
		//给表单元素赋值
		 $("#stuid").val(oldst.stuid);
		 $("#sname").val(oldst.sname);
		 $("#address").val(oldst.address);
		 $("#sdate").val(oldst.sdate);
		 $("#classid").val(oldst.classid);
		 var sex=oldst.sex;
		 if(sex=='男'){
			 $(":radio[value='男']").attr('checked','true');
		 }else{
			 $(":radio[value='女']").attr('checked','true');
		 }
	});
}
</script>
</head>
<body>
<p align="center"><h1 align="center">学生列表</h1>
<hr />
<div id="mytable"></div>
<form id="form1" name="form1" method="post" action="">
  <table width="810" border="1" align="center" cellpadding="1" cellspacing="0">
    <tr align="center" bgcolor="#FFFFCC">
      <td>
      <input type="button" name="btfirst" id="btfirst" value="首页" />
      </td>
       <td>
      <input type="button" name="btup" id="btup" value="上页" />
      </td>
      	<td>
      <input type="button" name="btnext" id="btnext" value="下页" />
      </td>
      		<td>
      <input type="button" name="btlast" id="btlast" value="末页" />
      </td>
      <td>每页
      <input name="rows" type="text" id="rows" size="2" />
      条记录
      <input type="button" name="buttonrows" id="buttonrows" value="确定"/></td>
      <td>跳转到第
        <input name="page" type="text" id="page" size="2" />
        页
      <input type="button" name="buttonpage" id="buttonpage" value="确定"/></td>
      <td><span id="pagelable">1/1</span></td>
    </tr>
  </table>
</form>
<hr />
<p>&nbsp;</p>
<!--添加和修改页面合并成管理页面  -->
<form action="" method="post" name="form1" id="form1">
  <table width="550" border="1" align="center" cellpadding="1" cellspacing="0">
    <tr>
      <td colspan="2" align="center" bgcolor="#FFFF33">学生管理</td>
    </tr>
    <tr>
      <td width="66">姓名</td>
      <td width="330">
      <input type="text" name="sname" id="sname" /></td>
    </tr>
    <tr>
      <td>性别</td>
      <td><input name="sex" type="radio" id="radio" value="男" checked="checked" />男
          <input type="radio" name="sex" id="radio2" value="女" />女
      </td>
    </tr>
    <tr>
      <td>地址</td>
      <td><input type="text" name="address" id="address" /></td>
    </tr>
    <tr>
      <td>生日</td>
      <td><input type="date" value="1990-01-01" name="sdate" id="sdate" /></td>
    </tr>
    <tr>
      <td>班级</td>
      <td>
        <select name="classid" id="classid">
      		
      	</select>
      
      </td>
    </tr>
    <tr>
      <td colspan="3" align="center" bgcolor="#FFFF33">
      <input type="hidden" name="stuid" id="stuid" />
      <input type="button" name="btsave" id="btsave" value="保存" />
      <input type="button" name="btupdate" id="btupdate" value="修改" />
      <input type="reset" name="button2" id="button2" value="重置" /></td>
    </tr>
  </table>
</form>
</body>
</html>