PROJECT_ID=test1-305123
REPO=us-central1-docker.pkg.dev/test1-305123/docker-shutdown

all:
	@echo "Hello"

create-image:
	docker build -t $(REPO)/python-shutdown .
	docker push $(REPO)/python-shutdown

create-image-gcp:
	gcloud builds submit
