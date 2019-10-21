FROM jupyter/datascience-notebook
USER root
RUN apt-get update
RUN apt-get install -y vim
ADD start.sh /tmp/
RUN usermod -l sandeepj jovyan
RUN usermod -d /home/sandeepj -m sandeepj
WORKDIR /home/sandeepj
USER sandeepj
CMD sh /tmp/start.sh
