#!/bin/bash

echo "Compiling the Maven project..."
mvn clean package

echo "$? The exit code of the previous command"

if [ $? -eq 0 ]; then
    echo "Compilation successful. JAR file created in the 'target' directory."
else
    echo "Compilation failed. Check the error messages above."
    exit 1
fi
