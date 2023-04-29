@echo off
javac -d . -encoding utf-8 src/main/*.java
javac -d . -encoding utf-8 src/*.java
java src.cls.MemeInatorTest