2. Data Source Architectural Patterns
=============================
    1.0 Overview
    Data Source Architectural Patterns are used for encapsulating data access logic in an application
	
	1.1 Table Data Gateway
		A Table Data Gateway has a simple interface for the data source, usually consisting of several find, update, insert, and delete methods to push data back and forth.
		• Holds all the SQL for accessing a single table or view
		• Works well with Table Module

	1.2 Row Data Gateway
		Row Data Gateway is a design pattern in which an object acts as a gateway to a single database row
		• There is one instance per row
		• Contains only database access and no behavior
		• Acts as an object that exactly mimics a single record
		• Works particularly well for Transaction Scripts

	1.3 Active Record
		This pattern is used with the Domain Model and tightly couples the domain logic and data with the data access logic
		• An object that encapsulates both data and behavior. 
		• Insert, update, and delete a particular instance of the object
		• Find and load a particular object and associated objects
		• Build strongly-typed collections of objects retrieved from the database using a static method
		• Perform complex domain logic
	1.4 Data Mapper	
		 A Data Mapper, is a Data Access Layer that performs bidirectional transfer of data between a persistent data store (often a relational database) 
		 and an in memory data representation (the domain layer). The goal of the pattern is to keep the in memory representation and the persistent data 
		 store independent of each other and the data mapper itself
		 • Separation between domain and data source
		 • Useful for NoSQL