import itertools
import sys
 
def uniqueSubSums(array):
    sums = []
    for x in range(1, len(array)):
        for s in itertools.combinations(array, x):
            s = sum(s)
            if (s in sums):
                return False
            else:
                sums.append(s)
    return True
 
NUM_PEOPLE = int(sys.argv[1])

for s in itertools.combinations(range(1, 61), NUM_PEOPLE):
    if uniqueSubSums(s):
        print(s)
        break
