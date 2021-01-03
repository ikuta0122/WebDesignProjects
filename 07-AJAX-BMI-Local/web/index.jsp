<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>局部刷新-AJAX</title>
    <script type="text/javascript">
      function doAjax() {
        //1.创建异步对象
        var xmlHttp = new XMLHttpRequest();

        //2.异步对象绑定事件(onreadystatechange)
        xmlHttp.onreadystatechange = function () {
          if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            document.getElementById("cache").innerText = xmlHttp.responseText;//更新dom对象， 更新页面数据
          }
        }

        //3.初始化异步请求对象
        var name = document.getElementById("name").value;
        var weight = document.getElementById("weight").value;
        var height = document.getElementById("height").value;
        var param = "name=" + name + "&weight=" + weight + "&height=" + height;
        xmlHttp.open("get", "bmiAjaxServlet?" + param, true);

        //4.异步请求对象发送请求
        xmlHttp.send();
      }
    </script>
</head>

<body>
<p>局部刷新AJAX-计算BMI</p>
<div>
    <!-- 没有使用form  -->
    姓名：<input type="text" id="name"/> <br/>
    体重（公斤）:<input type="text" id="weight"/> <br/>
    身高（米）：<input type="text" id="height"/> <br/>
    <input type="button" value="计算BMI" onclick="doAjax()"> <br/>
    <br/>
    <div id="cache"></div>
</div>
</body>
</html>
