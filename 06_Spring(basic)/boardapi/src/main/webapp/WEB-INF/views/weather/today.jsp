<%-- openweather api를사용하여서울의날씨를확인하세요.
 ○ 온도는 섭씨로 처리
 ○ 해당 날씨에 대한 아이콘을 얻는url을 작성하고, 확인--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<div>
  <h2>${city}</h2>
  오늘의 날씨 : ${weather.weather[0].description} <img src="${iconUrl}"/>
</div>
<div>
  온도 : ${weather.main.temp}°/ 습도 : ${weather.main.humidity}%
</div>
</body>
</html>
