@echo off
echo 使用maven根据pom.xml 复制依赖jar到webapp/WEB-INF/lib

call mvn dependency:copy-dependencies
pause