# Derived from official mysql image (our base image)
# co the lua chon ban moi nhat 
FROM mysql:5.7
# Add a database
ENV MYSQL_DATABASE=hello
ENV	MYSQL_ROOT_PASSWORD=root
	
# Add the content of the sql-scripts/ directory to your image
# All scripts in docker-entrypoint-initdb.d/ are automatically
# executed during container startup
# COPY ./script/ /docker-entrypoint-initdb.d/