# Process-Logger
#### A multi-tier application to log the process statistics onto database. Logging activity was achieved even at an interval of 1 ms between logs. Java SE and SQLite were used for the implementation. UDP was used as the data transfer protocol between the tiers.


* Integrated SQLite In-Memory database to support quicker logging times.
* Developed a reusable, modularised database API to facilitate logging of statistics.
* Process statistics like CPU Utilization, Memory Utilization, Number of threads were logged.
* Developed a multi layered, distributed application. GUI Client, Process Server, DB Server were all different entities.
