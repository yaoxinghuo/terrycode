@echo off
echo ʹ��maven����pom.xml ��������jar��webapp/WEB-INF/lib

call mvn dependency:copy-dependencies
pause