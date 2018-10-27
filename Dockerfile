FROM enzief/docker-sbt:sbt

RUN apk --no-cache --update add git && \
    git clone https://github.com/enzief/egreen-ci.git && \
    cd egreen-ci && \
    sbt wip check clean && \
    apk del git && \
    cd ..  && \
    rm -rf egreen-ci

WORKDIR /app
