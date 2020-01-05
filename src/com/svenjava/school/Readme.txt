todo:
	- learn junit testing; testing edge cases
	- gray out courses after they have been chosen already.
	- change reading and writing to file in DataHandler and make use of try-with-resources
	
ideas for algorithm:
	first idea:
		- pick randomly 10th grader and put him into first wish course.
		- if course is full, put him into second wish course
		- if course is full, put him into third wish course
		- if course is full, put him into pool wishesNotFullfilled
		- after tenth grader are distributed pick randomly a ninth or eighth grader and do the same like above.
		
	second idea:
		-distribute 10th graders as mentioned in first idea
		-distribute 8/9th graders depending on 1. wish regardless of extending courses sizes
		-create a list of courseStillAvailable
		-find course that is extended the most
		-pick random 8/9th grader and get 2. wish
			METHOD1
			-check if this course is available and put student into it. check if 2. wish course is stillAvailable and remove it from list if not available any more
			-remove student from 1. wish course and check if  1 wish course is still extended
			- if so, get next 8/9th grader and repeat METHOD1
		
		-if 2.wish course is not available, check if course for 3. wish is available repeat METHOD1
		-if not, put student in list wishNotFullfilled and remove from 1. wish list
		-check if 1. wish course is still extended
		-if so get next 
		
		
	
	