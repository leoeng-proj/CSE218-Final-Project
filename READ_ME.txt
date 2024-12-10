StudentContainer:
TreeMap for the dynamic sizing capability of a red-black tree that will easily adjust to the constantly changing size of the student body attending the college. Students may share a few features so a Map was chosen so that the students would be placed into the tree according to their UUID.

CourseContainer:
TreeSet for a similar reason as the StudentContainer. Courses can easily be added and removed so having the dynamic sizing of the tree is critical. A set was chosen over a map because courses must be completely unique from one another so there was no need for mapping.

SectionContainer:
A HashSet was chosen because the maximum number of sections that can exist at the college depends on the number of classrooms and the length of the shortest possible class time. In my project this came out to be a maximum of 7 classrooms, each classroom only being able to maintain a maximum of 11 classes a day (shortest class is an hour and fifteen minutes which spread across 14 total hours 8:00-22:00 totals to 11 classes) as well as the worst case of each section being taught once a week resulting in a 7x7x11 maximum.

ClassroomContainer:
HashSet again because the number of classrooms at a college would rarely change. And in the case in which more buildings are created it is rather simple to update the size of the set.

ProfessorContainer:
A TreeMap for the same reasoning as the StudentContainer. The decision between TreeMap and PriorityQueue was rather close as it felt appropriate to keep the seniority of the professors constantly enforced in the DS. However I felt as though the O(logN) power of the Tree outclassed the savings I would make from maintaining the heap (which results in O(N) for the typical operations) the few moments in which autoAssign (in MasterContainer) is called.