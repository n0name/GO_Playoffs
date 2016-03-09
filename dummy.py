import sys
from time import sleep

print("process started", file=sys.stderr)

while True:
    for n in range(20):
        line = input()
        print("received: ", line, file=sys.stderr)

    print("0 0")

