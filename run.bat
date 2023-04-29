@echo off
javac -d . -encoding utf-8 src/*.java
javac -d . -encoding utf-8 src/main/*.java
javac -d . -encoding utf-8 src/main/page/*.java
java src.cls.MemeInatorTest