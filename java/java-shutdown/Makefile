PROJECT_ID=test1-305123
REPO=us-central1-docker.pkg.dev/test1-305123/docker-shutdown

all:
	@echo "Hello"

create-image:
	mvn package
	docker build -t us-central1-docker.pkg.dev/test1-305123/docker-shutdown/java-shutdown .
	docker push us-central1-docker.pkg.dev/test1-305123/docker-shutdown/java-shutdown

create-image-gcp:
	mvn package
	gcloud builds submit
