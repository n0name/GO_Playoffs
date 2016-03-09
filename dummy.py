import sys
import random


while True:
    grid = []
    possible = False
    for n in range(20):
        line = input()
        row = list(map(int, line.split()))
        if row.count(0) != 0:
            possible = True
        grid.append(row)

    if not possible:
        print("not possible", file=sys.stderr)
        print("pass")
        continue

    #if random.randint(0, 100) < 30:
    if False:
        print("pass")
        continue
    else:

        x = random.randint(0, 19)
        y = random.randint(0, 19)
        while grid[y][x] != 0:
            x = random.randint(0, 19)
            y = random.randint(0, 19)
        
        print("{} {}".format(x, y))

