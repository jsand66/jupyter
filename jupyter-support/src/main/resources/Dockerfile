FROM jupyter/datascience-notebook
USER root
ADD start.sh /tmp/
RUN useradd -m -g users entptools
WORKDIR /home/entptools
USER sandeepj
CMD sh /tmp/start.sh
