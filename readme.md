## Setup:
  * Data sources configured:
    * Database-1 (master)
    * Database-2 (slave)

  * URLs to test:
    * http://localhost:8080/users/active/1 ---> Gets the data from master database.
    * http://localhost:8080/users/inactive/1 ---> Gets the data from slave database.