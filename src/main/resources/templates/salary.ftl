<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        table {
            width: 600px;
            margin: 150px auto;
            border-collapse: collapse ;
        }
        th,td{
            height: 40px;
            text-align: center;
            border: 2px solid ;
            font-family: 华文行楷;
            font-size: larger;
        }

    </style>
</head>
<body>

<table>
    <tr>
        <th>员工账号</th>
        <th>员工姓名</th>
        <th>员工电话</th>
        <th>本月薪资</th>
    </tr>
    <tr>
        <td>${adminAccount}</td>
        <td>${adminName}</td>
        <td>${adminPhone}</td>
        <td>${adminSalary}</td>
    </tr>
</table>

</body>
</html>