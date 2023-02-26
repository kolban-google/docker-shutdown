PROJECT_ID=test1-305123
REPO=us-central1-docker.pkg.dev/test1-305123/docker-shutdown

all:
	@echo "create-image - Build and push a local image"
	@echo "create-image-gcp - Build a push on Google Cloud"

create-image:
	docker build -t $(REPO)/nodejs-shutdown .
	docker push $(REPO)/nodejs-shutdown

create-image-gcp:
	gcloud builds submit

build:
	docker build -t $(REPO)/nodejs-shutdown .
