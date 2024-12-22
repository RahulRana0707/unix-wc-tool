#!/bin/bash

JAR_FILE="target/unix-wc-tool-1.0-SNAPSHOT.jar"

if [ ! -f "$JAR_FILE" ]; then
    echo "JAR file not found. Please compile the project first using ./compile.sh"
    exit 1
fi

echo "Running the program..."
java -jar $JAR_FILE "$@"
