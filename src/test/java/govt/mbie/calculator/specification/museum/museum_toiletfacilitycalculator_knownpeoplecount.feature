Feature: In a Building/	Open Space with known people count
	Calculate How many toilet pans, basins and urinals to include in a Toilet Facility 
	Using Toilet Facility CalCulator

    Acceptance Criteria:
    # Generate Result for toilet facility with below option
       1.   Option 1 - Unisex toilet facilities
       2.   Option 2 - Single sex toilet facilities only
       3.   Option 3 - Single sex toilet facilities with urinals for males
       4.   Option 4 - Single sex toilet facilities only, plus accessible unisex
       5.   Option 5 - Single sex toilet facilities with urinals for males, plus accessible unisex
    # Print and Save Result as pdf for calculated toilet facility in building
    
    Limitations of the calculator
		#The calculator, on its own, will not produce a Building Code compliant solution as it does not cover:
			1.  allocation and distribution of accessible toilet facilities
			2. 	definition of, or the distribution of unisex toilet facilities
			3.	space requirements for toilets or accessible layouts
			4.  the 'floor area' options are extensive and allow for a number of different building configurations
					calculation of showers.
      
  Scenario: Calculate Toilet Facility Result for Museum with capacity of 100 people
      Given Bob The Builder is building toilet facility for Museum with capacity of 100 people
      And Bob wants to calculate Toilet Facility requirement using Toilet Facility Calculator
      When Bob open Toilet Facility Calculator
      And Bob select option to calculate Toilet Facility requirement with known people count
      And Bob enter building use as "Museum" with Occupant Count as "100" 
      And Bob Submit form
      Then Bob should be able to see Toilet Facility Result generated with different options
      And Bob should be able to Print and Save Result as pdf file