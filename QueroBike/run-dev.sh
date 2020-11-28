#!/bin/sh

# Require https://github.com/inotify-tools/inotify-tools/wiki

while inotifywait -e modify -r .; do mvn tomcat7:redeploy; done