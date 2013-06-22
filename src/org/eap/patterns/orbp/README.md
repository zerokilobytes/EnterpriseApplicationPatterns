3. Object-Relational Behavioral Patterns
=============================
    1.0 Overview
    Object-relational behavioural patterns are concern with solving design problems concerning how and
	when associated objects are persisted and loaded from a relational data source.

	1.1 Unit of Work
		Maintains an keep track of the objects affected by a business transaction, coordinate writing
		out of changes and resolve concurrency problems.
		• Coordinate the persistence of changes in an efficient, controlled and consistent manner.
		• Faster than persisting each change individually

	1.2 Identity Map
		 Improve performance by providing a context-specific, in-memory cache to ensure that each
		 object gets loaded only once from the data source by keeping every loaded object in a map.
		 If the requested data has already been loaded from the data source, the identity map 
		 returns the same instance of the already instantiated object, but if it has not been loaded
		 yet, it loads it and stores the new object in the map.
		 • Improve read performance by retrieving objects from in-memory cache
		 • Avoids redundant reading of objects

	1.3 Lazy Load
		Load or initialize an object only when needed.
		• Performance boost for application
		• Avoids unnecessary reading of objects