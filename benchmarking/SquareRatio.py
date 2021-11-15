'''
Square Ratio Target Finder

This calculator with find an aproximation of a decimal
as the ratio of two squares.
'''

import time

def run_test (target, iters):
	iters = int(iters)

	eps = 10e-10

	numer = 1
	denom = 1

	calculated = 0

	start_time = time.time()

	for i in range(iters):
		calculated = numer ** 2 / denom ** 2
		# print("%.4f = %d / %d" % (calculated, numer, denom))

		if calculated - eps < target:
			numer += 1
		elif calculated + eps > target:
			denom += 1
		else:
			break

	end_time = time.time()

	print("%4d iterations, got %.4f = %d / %d, difference of %f" % (iters, calculated, numer**2, denom**2, abs(calculated - target)))
	print("(%d / %d)^2" % (numer, denom))
	print("%f seconds" % (end_time - start_time))
	print()



run_test(0.1241342, 10e5) # 0.665 sec

run_test(0.63, 10e5)      # 0.780 sec
run_test(0.63, 10e6)      # 6.577 sec
run_test(0.63, 10e7)      # 67.17 sec


