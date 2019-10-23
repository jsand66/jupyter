FROM docker-ice-virtual.artifactory.intcx.net/thirdparty/jupyter/datascience-notebook
USER root
ADD start.sh /tmp/
RUN useradd -m -g users entptools
WORKDIR /home/entptools
USER entptools
CMD sh /tmp/start.sh