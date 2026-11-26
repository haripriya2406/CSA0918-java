# Task 03 – A3: Data-Access Object Pattern (5 Marks)

Identify repeated JDBC steps in StudentDB, CourseDB and MarksDB. Design a generic Dao<T,K> interface with insert, findById, findAll, update and delete. Show StudentDao implementing it and map Student fields to student-table columns, including the primary key.

**Alternative:** Compare DAO with SQL embedded in service/GUI classes. Explain how many locations need changes after a column rename. The DAO abstraction does not normally change the underlying time or space complexity of a database operation; SQL, indexes and result size determine those costs.

Evidence: common workflow, interface, StudentDao design and mapping.
