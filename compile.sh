#!/bin/bash
mkdir -p bin
find src ../cmiyc_shared/src -name '*.java' -exec javac -d bin {} +
