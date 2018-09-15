FROM enzief/docker-sbt:docker-jre

RUN apk --no-cache --update add git && \
    git clone https://github.com/enzief/egreen-ci.git && \
    cd egreen-ci && \
    sbt wip && \
    sbt clean && \
    apk del git

WORKDIR /app
