FROM enzief/docker-sbt:latest

RUN apk --no-cache --update add git && \
    git clone https://github.com/enzief/egreen-ci.git && \
    cd egreen-ci && \
    sbt wip

WORKDIR /app
