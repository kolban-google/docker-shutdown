## Java shutdown
This sample Java application illustrates how to trap and shutdown a
Java application.  It registers a shutdown hook which is called when
the JVM has been requested to end.  This might occur during a docker
container stop when a SIGTERM is sent to PID 1.  The shutdown hook sends
an interrupt request to the main thread which is responsible for handling
its own shutdown.

When the program runs, it logs a ticker every second to its stdout.  If
we terminate the JVM, we will see logging related to the shutdown steps
and the app will end.

We also include a Docker file that can be used to build an image.

Steps:

* mvn package
* docker build

To run the sample, we need to create a Compute Engine instance.  I created a new
service account called `docker-shutdown` and gave that service account the permission
to read the image from Artifact Registry.

When we then run the COS image, we can SSH into and run `docker ps`.  We should see that
the docker container is running.

```text
CONTAINER ID   IMAGE                                                                   COMMAND                  CREATED         STATUS         PORTS     NAMES
05ba37fa44e9   us-central1-docker.pkg.dev/test1-305123/docker-shutdown/java-shutdown   "java -cp /app/* org…"   6 minutes ago   Up 6 minutes             mycloudservice
```

We can view the logs of the container by running

```text
docker logs -f mycloudservice
```

If we now open a second SSH, we can see the effect of a shutdown by executing:

```text
sudo systemctl stop cloudservice
```

and seeing the following records written to the log:

```text
...
Count: 19, now: 2023-02-25T17:08:39.977753
Count: 20, now: 2023-02-25T17:08:40.978216
Count: 21, now: 2023-02-25T17:08:41.978720
Inside termination Shutdown Hook
All done ... The main app thread has terminated
Waiting for the main worker to end
The main worker thread has ended
```

This has proven that when the container is shutdown **on** the local machine, the
shutdown is graceful.  To test that a Compute Engine shutdown does the same,
we will capture the logs to Cloud Logging.  Add a new metadata variable called
`google-logging-enabled` with a value of `true`.  We also need to ensure that the
service account that runs the Compute Engine is granted the Log Writer role.
Restart the Compute Engine.