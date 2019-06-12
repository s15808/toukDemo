FROM tomcat:8.5.16-jre8
USER root

ADD /tomcat-config/tomcat-users.xml /usr/local/tomcat/conf/
ADD /tomcat-config/context.xml /usr/local/tomcat/webapps/manager/META-INF/
RUN rm -rf /usr/local/tomcat/webapps/ROOT
ADD /install/toukdemo-1.0.war  /usr/local/tomcat/webapps/ROOT.war
