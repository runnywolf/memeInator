@echo off
if not exist bin mkdir bin
cd bin
javac -d . -encoding utf-8 ../src/main/page/*.java
javac -d . -encoding utf-8 ../src/main/*.java
javac -d . -encoding utf-8 ../src/*.java
java MemeInatorTest
