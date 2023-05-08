@echo off
if not exist bin mkdir bin
dir /s /B *.java > bin/paths.txt
javac -encoding utf-8 -sourcepath src -d bin @bin/paths.txt
java -cp bin MemeInatorTest