#!/usr/bin/env sh

APP_BASE_NAME=$(basename "$0")
APP_HOME=$(dirname "$0")

# Locate java
if [ -n "$JAVA_HOME" ] ; then
    JAVA="$JAVA_HOME/bin/java"
else
    JAVA="java"
fi

# Run gradle wrapper jar
exec "$JAVA" -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
