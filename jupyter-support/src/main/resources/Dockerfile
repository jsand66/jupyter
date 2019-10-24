FROM docker-ice-virtual.artifactory.intcx.net/thirdparty/jupyter/datascience-notebook
USER root
RUN groupadd entptools -g 1720
RUN useradd -m -g 1720 -u 1720 entptools
RUN usermod -a -G entptools,users entptools
ADD start.sh /tmp/
WORKDIR /home/entptools
USER entptools
CMD sh /tmp/start.sh