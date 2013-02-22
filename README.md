Enterprise Application Patterns
=============================

1 Domain Logic Patterns
    
    Overview
    Domain Logic is a group of patterns that are concerned with the complexity of business domain logics

    1.1 Domain Model
        A domain model represents an object in the context of the business domain. It organizes business logic into
        well defined domain entities (Classes) that contain both behavior and data. It creates a hierarchy of inter 
        connected domain objects that interact with each other by invoking methods on each other.
        • Gives a conceptual framework of the things in the problem space
        • Focuses on semantics
        • Implements business rules on domain entities
        • Model business concepts into entities (Classes) with behaviors (Methods)

    1.2 Transaction Script
        Organizes business logic by routines where each routine handles a single request from the presentation layer
        • Make calls directly to the database or through a thin database wrapper
        • Each individual transaction will have its own Transaction Script routine or list of sub routine

    1.3 Organizes business logic around database tables or view with one class per database object. Each class 
        encapsulates all the domain logic for all records stored in a table or view
        • It presents a database-centric approach
