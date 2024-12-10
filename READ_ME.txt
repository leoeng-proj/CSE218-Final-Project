StudentContainer:
TreeMap for the dynamic sizing capability of a red-black tree that will easily adjust to the constantly changing size of the student body attending the college. 
Students may share a few features so a Map was chosen so that the students would be placed into the tree according to their UUID.

CourseContainer:
TreeSet since courses can easily be added and removed so having the dynamic sizing of the tree is critical. 
A set was chosen over a map because courses must be completely unique from one another so there was no need for mapping.

SectionContainer:
A HashSet was chosen because the maximum number of sections that can exist at the college depends on the number of classrooms and the length of the shortest possible class time. 
In my project this came out to be a maximum of 7 classrooms, each classroom only being able to maintain a maximum of 11 classes a day (shortest class is an hour and fifteen minutes 
which spread across 14 total hours 8:00-22:00 totals to 11.2 classes => 11 classes possible) as well as the worst case of each section being taught once a week resulting in a 7x7x11 maximum.

ClassroomContainer:
HashSet because the number of classrooms at a college would rarely change. And in the case in which more buildings are created it is rather simple to update the size of the set. No map required because classrooms are
always unique rooms as they are literal physical spaces and there can only be one of each.

ProfessorContainer:
A TreeMap for the same reasoning as the StudentContainer. The decision between TreeMap and PriorityQueue was rather close as it felt appropriate to keep the seniority of the professors constantly enforced in the DS. 
However I felt as though the O(logn) power of the Tree outclassed the savings I would make from maintaining the heap (which results in O(n) for the typical operations) the few moments in which autoAssign 
(in MasterContainer) is called (saving a single extra O(n) operation).


autoAssign Function:
What seemed to be the most important function and so I will briefly explain details. First let n = the number of professors in the MasterContainer and m = the number of sections in the MasterContainer. I am going to disregard the
complexity of the stringbuilder as it is purely optional and does not affect the completion of the algorthim.

(1) The function begins by creating a PriorityQueue for the Professors, resulting in a space and time of O(n) both.
	1.a Let "p" be the professor at the top of the heap according to their seniority (dateOfHire field)
(2) A SectionContainer is instantiated from the MasterContainer, resulting in space complexity of O(m)
(3) The SectionContainer is then trimmed to fit certain requirements, resulting in time complexity of O(m)
(4) A loop begins which will take the trimmed SectionContainer and find the best section for a Professor argument, p, let "best" be this section. Here is a O(k*m) operation where k is a constant about equal to 15 (min credits) 
	divided by the average credit value for sections in the major.
	4.a best is assigned to p in a O(1) operation
(5) Repeat from (2) until there are no more professors in the heap (n times) resulting in a space and time complexity of O(n*m)
 