FROM ubuntu:latest
LABEL authors="javguerra"

ENTRYPOINT ["top", "-b"]