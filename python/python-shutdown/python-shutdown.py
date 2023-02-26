import time
import signal
from datetime import datetime

terminate = False

def handler(signum, frame):
    print("Application signal handler called, signal=",signum)
    global terminate
    terminate = True

signal.signal(signal.SIGINT, handler)
signal.signal(signal.SIGTERM, handler)
counter = 0
while not terminate:
    counter=counter+1
    print("Counter:", counter, ", now: ", datetime.now())
    time.sleep(1)
print("Application terminated cleanly!!!")